import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';
import Accordion from '@/components/Accordion';
import classes from '@/styles/HistoryPage.module.scss';
import { getHistory } from '@/api/historyAxios';
import { useQuery } from '@tanstack/react-query';
import FetchError from '@/components/layouts/FetchError';
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
        <h2 className={classes.date}>3월 14일 (목)</h2>
        <ul>
          <li>
            {data.map((e: History) => (
              <Accordion history={e}></Accordion>
            ))}
            <section></section>
          </li>
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
