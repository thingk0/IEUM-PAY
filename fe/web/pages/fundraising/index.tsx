import { getFundListComplete, getFundListOnGoing } from '@/api/fundAxois';
import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';
import { useEffect, useState } from 'react';
import styles from '@/styles/FundPage.module.scss';
import CardList from '@/components/funding/CardList';

interface fundingList {
  fundingId: number;
  facilityName: string;
  fundingTitle: string;
  fundingOpenDate: string;
  facilityImage: string;
  fundingPeopleCnt: number;
  goalAmount: number;
  currentAmount?: number;
}

export default function Funding() {
  const [onGoingList, setonGoingList] = useState<fundingList[]>([
    {
      fundingId: 1,
      facilityName: 'btc',
      fundingTitle: '과자를 사주세요',
      fundingOpenDate: '2023-02-02',
      facilityImage: 'https://nextui-docs-v2.vercel.app/images/album-cover.png',
      fundingPeopleCnt: 10,
      goalAmount: 500000,
      currentAmount: 100000,
    },
    {
      fundingId: 2,
      facilityName: 'bhc',
      fundingTitle: '치킨을 사주새오',
      fundingOpenDate: '2023-02-02',
      facilityImage: 'https://nextui-docs-v2.vercel.app/images/album-cover.png',
      fundingPeopleCnt: 10,
      goalAmount: 500000,
      currentAmount: 1000,
    },
  ]);
  const [completeList, setcompleteList] = useState<fundingList[]>([
    {
      fundingId: 1,
      facilityName: 'btc',
      fundingTitle: '과자를 사주세요',
      fundingOpenDate: '2023-02-02',
      facilityImage: 'https://nextui-docs-v2.vercel.app/images/album-cover.png',
      fundingPeopleCnt: 10,
      goalAmount: 500000,
    },
    {
      fundingId: 2,
      facilityName: 'bhc',
      fundingTitle: '치킨을 사주세요',
      fundingOpenDate: '2023-02-02',
      facilityImage: 'https://nextui-docs-v2.vercel.app/images/album-cover.png',
      fundingPeopleCnt: 10,
      goalAmount: 500000,
    },
  ]);
  const [selectedTab, setSelectedTab] = useState(true);

  function Tab() {
    return selectedTab ? (
      <CardList fundingList={onGoingList} isOngoing={true} />
    ) : (
      <CardList fundingList={completeList} isOngoing={false} />
    );
  }

  const changeToOnGoing = () => {
    setSelectedTab(true);
  };
  const changeToComplete = () => {
    setSelectedTab(false);
  };

  useEffect(() => {
    async function getData() {
      try {
        const fundonGoingData = await getFundListOnGoing();
        const fundcompleteData = await getFundListComplete();
        fundonGoingData != undefined
          ? setonGoingList(fundonGoingData.data)
          : '';
        fundcompleteData != undefined
          ? setcompleteList(fundcompleteData.data)
          : '';
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
        <nav className={styles.tabContainer}>
          <ul className={styles.ul}>
            <li onClick={changeToOnGoing}>
              <div>진행 중</div>
            </li>
            <li onClick={changeToComplete}>
              <div>완료</div>
            </li>
          </ul>
        </nav>
        <div className={styles.cardContainer}>
          <Tab />
        </div>
      </div>

      <TabBar selected={'fundraising'} />
    </>
  );
}
function cardList(onGoingList: fundingList[], val: boolean) {
  throw new Error('Function not implemented.');
}
