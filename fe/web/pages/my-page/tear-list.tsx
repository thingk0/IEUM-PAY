import { badges } from '@/components/icons/LevelBadges';
import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';
import styles from '@/styles/tearListPage.module.scss';

export default function TearList() {
  const setBedge = (level: string) => {
    const BadgeComponent = badges[level];
    return <BadgeComponent />;
  };
  const levels = [
    'GR001',
    'GR002',
    'GR003',
    'GR004',
    'GR005',
    'GR006',
    'GR007',
  ];

  return (
    <>
      <HeaderMain />
      <div className={styles.table}>
        <div className={styles.header}>
          <div>등급</div>
          <div>누적 기부 액</div>
          <div>기부 횟수</div>
        </div>
        <hr />
        <ol>
          {levels.map((level) => (
            <li>{setBedge(level)}</li>
          ))}
        </ol>
      </div>
      <TabBar selected={'myPage'} />
    </>
  );
}
