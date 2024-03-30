import { badges } from '@/components/icons/LevelBadges';
import useUserStore from '@/stores/user-store';
import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';
import styles from '@/styles/tearListPage.module.scss';
import { commaizeNumber } from '@toss/utils';
import { GetServerSideProps } from 'next';

function TearList({ gradeCode }: { gradeCode: string }) {
  const setBedge = (level: string) => {
    const BadgeComponent = badges[level];
    return <BadgeComponent />;
  };
  const levels = [
    { grade: 'GR001', name: '씨앗', amount: 0, cnt: 0 },
    { grade: 'GR002', name: '새싹', amount: 100, cnt: 1 },
    { grade: 'GR003', name: '떡잎', amount: 5000, cnt: 10 },
    { grade: 'GR004', name: '묘목', amount: 30000, cnt: 30 },
    { grade: 'GR005', name: '나무', amount: 100000, cnt: 50 },
    { grade: 'GR006', name: '열매', amount: 1000000, cnt: 70 },
    { grade: 'GR007', name: '이음', amount: 10000000, cnt: 100 },
  ];

  return (
    <>
      <HeaderMain />
      <div className={styles.table}>
        <div className={styles.header}>
          <div className={styles.lavel}>등급</div>
          <div className={styles.lavel}>누적 기부 액</div>
          <div className={styles.lavel}>기부 횟수</div>
        </div>
        <hr />
        <ol>
          {levels.map((level) => (
            <>
              <li className={gradeCode == level.grade ? styles.box : styles.li}>
                <div className={styles.grade}>
                  {setBedge(level.grade)}
                  <p>{level.name}</p>
                </div>
                <div className={styles.amount}>
                  {commaizeNumber(level.amount)}원
                </div>
                <div className={styles.cnt}>{level.cnt}</div>
              </li>
              <hr />
            </>
          ))}
        </ol>
      </div>
      <TabBar selected={'myPage'} />
    </>
  );
}
export default TearList;
export const getServerSideProps: GetServerSideProps = async ({ query }) => {
  // query 객체 사용
  return {
    props: { gradeCode: query.gradeCode },
  };
};
