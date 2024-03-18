import { useEffect, useState } from 'react';
type Data = {
  historyId: number;
  historyDate: string;
  type: string;
  title: string;
  amount: number;
  detail: {
    type: string;
    name: string;
    price: number;
  }[];
}[];
function HistoryPage() {
  const [data, setData] = useState<Data>([]);
  const [isOpenList, setIsOpenList] = useState<
    { historyId: number; isOpen: boolean }[]
  >([]);
  useEffect(() => {
    async function getData() {
      const data = await fetch('/api/pay/history/1').then((res) => res.json());

      setData(() => data);
      console.log(data);
    }
    getData();
  }, []);
  function handleClick(id: number) {
    setIsOpenList((prev) => (prev[id].isOpen ? false : true));
  }
  return (
    <main>
      <h1>사용 내역</h1>
      {data &&
        data.map((e) => (
          <li key={e.historyId}>
            <button onClick={handleClick(e.historyId)}>
              <div>{e.amount > 0 ? `+${e.amount}` : e.amount}</div>
              <div>
                {e.type}|{e.title}
              </div>
            </button>
            {e.detail.map((el) => (
              <div>{el.price}</div>
            ))}
          </li>
        ))}
    </main>
  );
}
export default HistoryPage;
