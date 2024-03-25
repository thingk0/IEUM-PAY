import { getFundListComplete, getFundListOnGoing } from '@/api/fundAxois';
import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';
import { Progress } from '@nextui-org/react';
import { useEffect, useState } from 'react';
import styles from '@/styles/FundPage.module.scss';

interface onGoingListType {
  fundingId: number;
  facilityName: string;
  fundingTitle: string;
  fundingOpenDate: string;
  facilityImage: string;
  fundingPeopleCnt: number;
  goalAmount: number;
  currentAmount: number;
}

interface completeListType {
  fundingId: number;
  facilityName: string;
  fundingTitle: string;
  fundingOpenDate: string;
  facilityImage: string;
  fundingPeopleCnt: number;
  goalAmount: number;
}

export default function Funding() {
  const [onGoingList, setonGoingList] = useState<onGoingListType[]>([
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
  const [completeList, setcompleteList] = useState<completeListType[]>([
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

  const showTab = (val: boolean) => {
    if (val) {
      return (
        <div>
          <div>진행중인 모금 {onGoingList.length}</div>
          <div className={styles.cardContainer}>
            {onGoingList.map((unit) => (
              <div className={styles.card}>
                <img
                  className={styles.cardImage}
                  src={unit.facilityImage}
                  alt={`${unit.facilityName} 이미지`}
                />
                <div className={styles.cardTextContiner}>
                  <div>{unit.facilityName}</div>
                  <div>{unit.fundingOpenDate}</div>
                  <div>
                    <div className={styles.cntProgressText}>
                      <div>{unit.fundingPeopleCnt}명 참여중</div>
                      <div>
                        {unit.currentAmount} / {unit.goalAmount}
                      </div>
                    </div>
                    <Progress
                      size="sm"
                      aria-label="기부 모금 정도 표기"
                      classNames={{
                        indicator: `${styles.progressBar}`,
                      }}
                      value={Math.floor(
                        (unit.currentAmount / unit.goalAmount) * 100,
                      )}
                    />
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      );
    } else {
      return (
        <div>
          <div>완료된 모금 {onGoingList.length}</div>
          <div className={styles.cardContainer}>
            {completeList.map((unit) => (
              <div className={styles.card}>
                <img
                  className={styles.cardImage}
                  src={unit.facilityImage}
                  alt={`${unit.facilityName} 이미지`}
                />
                <div className={styles.cardTextContiner}>
                  <div>{unit.facilityName}</div>
                  <div>{unit.fundingOpenDate}</div>
                  <div>
                    <div className={styles.cntProgressText}>
                      <div>{unit.fundingPeopleCnt}명 참여중</div>
                      <div>
                        {unit.goalAmount} / {unit.goalAmount}
                      </div>
                    </div>
                    <Progress
                      size="sm"
                      aria-label="기부 모금 정도 표기"
                      classNames={{
                        indicator: `${styles.progressBar}`,
                      }}
                      value={100}
                    />
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      );
    }
  };

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
        fundonGoingData != undefined ? setonGoingList(fundonGoingData) : '';
        fundcompleteData != undefined ? setcompleteList(fundcompleteData) : '';
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
        <div className={styles.cardContainer}>{showTab(selectedTab)}</div>
      </div>

      <TabBar selected={'fundraising'} />
    </>
  );
}
