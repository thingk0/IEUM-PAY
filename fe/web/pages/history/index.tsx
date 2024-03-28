import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';
import Accordion from '@/components/Accordion';
import classes from '@/styles/HistoryPage.module.scss';
import { useEffect } from 'react';
import { getHistory } from '@/api/historyAxios';
function HistoryPage() {
  useEffect(() => {
    async function getData() {
      let data = await getHistory();
      console.log(data);
    }
    getData();
  }, []);
  return (
    <>
      <HeaderMain />
      <main>
        <h1 className={classes.title}>사용내역</h1>
        <h2 className={classes.date}>3월 14일 (목)</h2>
        <ul>
          <li>
            <Accordion price={-6000} transactionType={'결제'} name={'쿠팡'}>
              내용
            </Accordion>
            <section></section>
          </li>
        </ul>
      </main>
      <TabBar selected={'history'} />
    </>
  );
}
export default HistoryPage;
