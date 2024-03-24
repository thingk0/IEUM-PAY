import TabBar from '@/stories/TabBar';
import HeaderMain from '@/stories/HeaderMain';
import { badges } from '@/components/icons/LevelBadges';
import { Icons } from '@/components/icons/LevelIcons';
import TearList from '@/components/TearList';
import { useEffect, useState } from 'react';
import { getUserInfo } from '@/api/userAxois';
import { commaizeNumber } from '@toss/utils';
import { useRouter } from 'next/router';
import Button from '@/stories/Button';

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

  const [userInfo, setUserInfo] = useState<userInfoType>({
    name: '이이름은진짜없겠지',
    nickname: '기부니가 좋아',
    gradeCode: 'GR001',
    gradeName: '새싹',
    totalDonationCnt: 5,
    totalDonationAmount: 41241500,
    list: [
      {
        img: 'http:url ---',
        fundingId: 1,
        fundingAmount: 13300,
        ongoing: false, // true : 진행중 fasle : 종료
      },
    ],
    autoFundingId: 0,
    autoFundingTitle: 'btc',
    autoFundingImg: 'http:url ---',
    autoFundingAmount: 2100,
  });

  const setBedge = (level: string) => {
    const BadgeComponent = badges[level];
    return <BadgeComponent />;
  };

  const setIcon = (level: string) => {
    const IconComponent = Icons[level];
    return <IconComponent />;
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
    } else if (userInfo.autoFundingId == 0) {
      return (
        <>
          <p>
            지금까지 <span>{userInfo.totalDonationCnt}개</span>기관에
          </p>
          <p>
            총 <span>{commaizeNumber(userInfo.totalDonationAmount)}원</span>을
            나눴어요
          </p>
          {setIcon(userInfo.gradeCode)}
          <hr />
          <p>연동되어있는 모금이 없어요</p>
          <Button size="thin" onClick={() => router.push('/rundraising')}>
            모금하가기
          </Button>
        </>
      );
    } else {
      return (
        <>
          <div>
            <p>
              지금까지 <span>{userInfo.totalDonationCnt}개</span>기관에
            </p>
            <p>
              총 <span>{commaizeNumber(userInfo.totalDonationAmount)}원</span>을
              나눴어요
            </p>
            {setIcon(userInfo.gradeCode)}
          </div>
          <hr />
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
        </>
      );
    }
  };

  useEffect(() => {
    async function getData() {
      try {
        const userData = await getUserInfo();
        userData != undefined ? setUserInfo(userData) : '';
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
        {setBedge(userInfo.gradeCode)}
        <span>{userInfo.name}</span>
        {userInfo.nickname}
      </div>
      <hr />
      <div>{donateState()}</div>

      <TabBar selected={'myPage'} />
    </>
  );
}
