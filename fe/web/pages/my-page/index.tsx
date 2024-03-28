import TabBar from '@/stories/TabBar';
import HeaderMain from '@/stories/HeaderMain';
import { useEffect, useState } from 'react';
import { getUserInfo } from '@/api/userAxois';
import { commaizeNumber } from '@toss/utils';
import { useRouter } from 'next/router';
import Button from '@/stories/Button';
import styels from '@/styles/myPage.module.scss';
import { CircularProgressbarWithChildren } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';

interface fundingType {
  img: string;
  fundingId: number;
  fundingAmount: number;
  ongoing: boolean;
}
interface userInfoType {
  name: string;
  nickname: string;
  gradeCode: string;
  gradeName: string;
  totalDonationCnt: number;
  totalDonationAmount: number;
  list: fundingType[];
  autoFundingId: number;
  autoFundingTitle: string;
  autoFundingImg: string;
  autoFundingAmount: number;
}

export default function MyPage() {
  const router = useRouter();
  const [progressValue, setProgressValue] = useState(0);
  const [userInfo, setUserInfo] = useState<userInfoType>({
    name: '이이름은진짜없겠지',
    nickname: '기부니가 좋아',
    gradeCode: 'GR005',
    gradeName: '새싹',
    totalDonationCnt: 5,
    totalDonationAmount: 241500,
    list: [
      {
        img: 'https://nextui-docs-v2.vercel.app/images/album-cover.png',
        fundingId: 1,
        fundingAmount: 13300,
        ongoing: false, // true : 진행중 fasle : 종료
      },
    ],
    autoFundingId: 0,
    autoFundingTitle: 'btc',
    autoFundingImg: 'https://nextui-docs-v2.vercel.app/images/album-cover.png',
    autoFundingAmount: 2100,
  });

  const tearProgress = (value: number, tear: string) => {
    if (tear == 'GR001') return (value / 100) * 100;
    else if (tear == 'GR002') return (value / 5000) * 100;
    else if (tear == 'GR003') return (value / 30000) * 100;
    else if (tear == 'GR004') return (value / 100000) * 100;
    else if (tear == 'GR005') return (value / 1000000) * 100;
    else if (tear == 'GR006') return (value / 10000000) * 100;
  };

  const donateState = () => {
    if (
      userInfo.totalDonationAmount == 0 &&
      userInfo.totalDonationCnt == 0 &&
      userInfo.autoFundingId == 0
    ) {
      return (
        <>
          <p>아직 기부해본적이 없네요</p>
          <p>기부를 시작하러 가볼까요 ~ ? </p>
          <Button size="thin" onClick={() => router.push('/rundraising')}>
            모금하가기
          </Button>
        </>
      );
    } else {
      return (
        <>
          <p>
            지금까지 <span>{userInfo.totalDonationCnt}개</span>기관에
          </p>
          <p>
            총 <span>{commaizeNumber(userInfo.totalDonationAmount)}원</span>을
            나눴어요
          </p>
          <div className={styels.progressBar}>
            <CircularProgressbarWithChildren
              value={progressValue}
              styles={{
                root: {},
                path: {
                  stroke: `rgba(128, 53, 249, ${progressValue})`,
                  strokeLinecap: 'butt',
                  transition: 'stroke-dashoffset 0.5s ease 0s',
                  transformOrigin: 'center center',
                },
              }}
            >
              <img
                src={`levelIcons/${userInfo.gradeCode}.svg`}
                className={styels.iconImage}
              />
            </CircularProgressbarWithChildren>
          </div>
          <hr />
          {userInfo.autoFundingId == 0 ? (
            <>
              <p>연동되어있는 모금이 없어요</p>
              <Button size="thin" onClick={() => router.push('/fundraising')}>
                모금하러가기
              </Button>
            </>
          ) : (
            <div>
              <p>기부중인 모금</p>
              <img src={userInfo.autoFundingImg} alt="기부중인 모금 사진" />
              <p>기부단체 설명</p>
              <p>
                <span>{commaizeNumber(userInfo.autoFundingAmount)}원</span>
                나눴어요
              </p>
              <a href="">버튼자리</a>
            </div>
          )}
        </>
      );
    }
  };

  useEffect(() => {
    async function getData() {
      try {
        const userData = await getUserInfo();
        userData != undefined ? setUserInfo(userData) : '';
        setProgressValue(
          Math.floor(
            Number(
              tearProgress(userInfo.totalDonationAmount, userInfo.gradeCode),
            ),
          ),
        );
      } catch (e) {
        console.log(e);
      }
    }
    getData();
  }, []);

  return (
    <>
      <HeaderMain />

      <div>
        <img
          src={`levelBadges/${userInfo.gradeCode}.svg`}
          className={styels.badgeImage}
          alt={`${userInfo.gradeName} 등급 뱃지`}
        />
        <span>{userInfo.name}</span>
        {userInfo.nickname}
      </div>
      <hr />
      <div>{donateState()}</div>

      <TabBar selected={'myPage'} />
    </>
  );
}
