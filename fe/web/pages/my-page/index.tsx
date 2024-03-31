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
import GradientSVG from '@/components/gradientSVG';

interface userInfoType {
  autoFundingId: number;
  facilityImage: string;
  facilityName: string;
  fundingTotalAmount: number;
  gradeCode: string;
  gradeName: string;
  name: string;
  nickname: string;
  totalDonationAmount: number;
  totalDonationCnt: number;
}

export default function MyPage() {
  const router = useRouter();
  const [progressValue, setProgressValue] = useState(0);
  const [userInfo, setUserInfo] = useState<userInfoType>({
    autoFundingId: 1,
    facilityImage:
      'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200124_169%2F1579861444105ksyLS_JPEG%2FHR80O6nA89Q9ZWBHq4KZQL_-.jpeg.jpg',
    facilityName: '은혜노인복지센터',
    fundingTotalAmount: 300500,
    gradeCode: 'GR007',
    gradeName: '이음',
    name: '김범수',
    nickname: '김범수11',
    totalDonationAmount: 300500,
    totalDonationCnt: 3,
  });
  const idCSS = 'hello';

  const tearProgress = (value: number, tear: string) => {
    if (tear == 'GR001') return (value / 100) * 100;
    else if (tear == 'GR002') return (value / 5000) * 100;
    else if (tear == 'GR003') return (value / 30000) * 100;
    else if (tear == 'GR004') return (value / 100000) * 100;
    else if (tear == 'GR005') return (value / 1000000) * 100;
    else if (tear == 'GR006') return (value / 10000000) * 100;
    else if (tear == 'GR007') return 100;
  };

  const onclickFunc = () => {
    console.log(userInfo.gradeCode);
    router.push({
      pathname: '/my-page/tear-list',
      query: {
        gradeCode: userInfo.gradeCode,
      },
    });
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
          <div className={styels.progressBar} onClick={onclickFunc}>
            <GradientSVG />
            <CircularProgressbarWithChildren
              value={progressValue}
              styles={{
                root: {},
                path: {
                  stroke: `url(#${idCSS})`,
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
          <Button
            primary
            size="small"
            onClick={() => {
              router.push('/my-page/donation-history');
            }}
          >
            나의 기부 내역 보기
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
              <img src={userInfo.facilityImage} alt="기부중인 모금 사진" />
              <p>{userInfo.facilityName}에 지금까지</p>
              <p>
                <span>{commaizeNumber(userInfo.fundingTotalAmount)}원</span>
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
        userData != undefined ? setUserInfo(userData.data) : '';
        console.log(userData.data);
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
