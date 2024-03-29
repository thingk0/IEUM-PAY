import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';
import Accordion from '@/components/Accordion';
import classes from '@/styles/HistoryPage.module.scss';
import { getHistory } from '@/api/historyAxios';
import { useQuery } from '@tanstack/react-query';

import FetchError from '@/components/layouts/FetchError';
import dayjs from 'dayjs';
interface Detail {
  type: string;
  name: string;
  price: number;
}
interface History {
  historyId: number;
  historyDate: string;
  type: string;
  title: string;
  amount: number;
  detail: Detail[];
}
function HistoryPage() {
  const { data, error, isError, isLoading, refetch } = useQuery({
    queryKey: ['fund-ongoing'],
    queryFn: getHistory,
  });
  function Content() {
    if (isLoading) {
      return <>로딩중...</>;
    } else if (isError) {
      return <FetchError onClick={() => refetch()}></FetchError>;
    }
    return (
      <>
        <ul>
          {data.map((e: History, i: number) => {
            console.log(i);
            let isNewDay = false;
            if (
              i === 0 ||
              (i > 0 &&
                !dayjs(e.historyDate).isSame(
                  dayjs(data[i - 1].historyDate),
                  'day',
                ))
            ) {
              isNewDay = true;
            }
            return (
              <>
                {isNewDay && <h2>{dayjs(e.historyDate).format('M월 D일')}</h2>}
                <li>
                  <Accordion history={e} key={e.historyId}></Accordion>
                </li>
              </>
            );
          })}
          <section></section>
        </ul>
      </>
    );
  }
  return (
    <>
      <HeaderMain />
      <main>
        <h1 className={classes.title}>사용내역</h1>
        <Content />
      </main>
      <TabBar selected={'history'} />
    </>
  );
}
export default HistoryPage;
