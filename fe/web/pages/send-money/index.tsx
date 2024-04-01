import TabBar from '@/stories/TabBar';
import {
  Tabs,
  Tab,
  Input,
  Select,
  SelectItem,
  Button,
} from '@nextui-org/react';
import { useRouter } from 'next/router';
import { useState } from 'react';
import classes from './index.module.scss';
import HeaderMain from '@/stories/HeaderMain';
import { getMemberByPhoneNumber } from '@/api/sendMoneyAxios';
import useSendMoneyInfo from '@/hooks/useSendMoneyStore';
import { useQueries } from '@tanstack/react-query';
import { getMainData } from '@/api/userAxois';
import ChevronRightIcon from '@/components/icons/ChevronRightIcon';

interface Member {
  memberId: number;
  name: string;
  phoneNumber: string;
}

function WherePage() {
  const [account, setAccount] = useState<string>();
  const router = useRouter();
  const [query, setQuery] = useState('');
  const { sendMoneyInfo, setReceiverInfo } = useSendMoneyInfo();

  const results = useQueries({
    queries: [
      {
        queryKey: [query],
        queryFn: () => getMemberByPhoneNumber(query),
        enabled: query.length >= 11,
      },
      {
        queryKey: ['main-data'],
        queryFn: getMainData,
      },
    ],
  });
  function handleClick() {
    setReceiverInfo(
      results[0].data.name,
      results[0].data.phoneNumber,
      '이음페이',
    );
    router.push('/send-money/amount');
  }
  function SearchResult() {
    if (results[0].isError)
      return <p>해당 번호는 이음페이에 가입한 적이 없네요 ㅠㅠ</p>;
    else if (results[0].data) {
      return (
        <button className={classes.send} onClick={handleClick}>
          {results[0].data?.name}님에게 송금하기
          <ChevronRightIcon />
        </button>
      );
    } else null;
  }

  if (results[1].isLoading) return null;
  if (results[1].data && results[1].data.data.cardList.length === 0)
    return (
      <>
        <HeaderMain />
        <main className={classes.main}>
          <h1>송금하려면 카드를 먼저 등록 해주세요</h1>
        </main>
        <TabBar selected="sendMoney" />
      </>
    );

  return (
    <>
      <HeaderMain />
      <main className={classes.main}>
        <h1>어디로 돈을 보낼까요?</h1>

        <Input
          variant="underlined"
          label="휴대폰 번호"
          type="tel"
          inputMode="decimal"
          pattern="[0-9]*"
          className="w-100"
          onValueChange={setQuery}
        />

        <SearchResult />
      </main>
      <TabBar selected="sendMoney" />
    </>
  );
}
export default WherePage;
