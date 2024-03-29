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
    name: '김범수',
    nickname: '기부니가 좋아',
    gradeCode: 'GR003',
    gradeName: '새싹',
    // totalDonationCnt: 0,
    totalDonationCnt: 3,
    // totalDonationAmount: 0,
    totalDonationAmount: 3000,
    list: [
      {
        img: 'https://nextui-docs-v2.vercel.app/images/album-cover.png',
        fundingId: 1,
        fundingAmount: 13300,
        ongoing: false, // true : 진행중 fasle : 종료
      },
    ],
    autoFundingId: 1,
    // autoFundingId: 0,
    autoFundingTitle: 'btc아동센터',
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
    return (
      <>
        <div className={styels.donateHistory}>
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
          <Button primary size="small" onClick={() => {}}>
            지난 기부 내역 보기
          </Button>
        </div>
        <hr />
        <div className={styels.connectedState}>
          {userInfo.autoFundingId == 0 ? (
            <>
              <p>연동되어있는 모금이 없어요</p>
              <Button size="thin" onClick={() => router.push('/fundraising')}>
                모금하러가기
              </Button>
            </>
          ) : (
            <div className={styels.curConnected}>
              <p className={styels.head}>기부중인 모금</p>
              <img src={userInfo.autoFundingImg} alt="기부중인 모금 사진" />
              <p>{userInfo.autoFundingTitle}에 지금까지</p>
              <p>
                <span>{commaizeNumber(userInfo.autoFundingAmount)}원</span>
                나눴어요
              </p>
              <Button
                primary
                size="small"
                onClick={() =>
                  router.push(`/fundraising/${userInfo.autoFundingId}`)
                }
              >
                모금 상세 보기
              </Button>
            </div>
          )}
        </div>
      </>
    );
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

      <div className={styels.container}>
        <div className={styels.nameContainer}>
          <img
            src={`levelBadges/${userInfo.gradeCode}.svg`}
            className={styels.badgeImage}
            alt={`${userInfo.gradeName} 등급 뱃지`}
          />
          <span>{userInfo.name}</span>
          <p>{userInfo.nickname}</p>
        </div>
        <hr />
        <div>{donateState()}</div>
      </div>
      <TabBar selected={'myPage'} />
    </>
  );
}
