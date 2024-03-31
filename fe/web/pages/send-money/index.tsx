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
import { useEffect, useState } from 'react';
import classes from './index.module.scss';
import Header from '@/components/Header';
import HeaderMain from '@/stories/HeaderMain';
import { getMemberByPhoneNumber } from '@/api/sendMoneyAxios';
import useSendMoneyInfo from '@/hooks/useSendMoneyStore';
import { useQuery } from '@tanstack/react-query';

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

  const { data, isError } = useQuery({
    queryKey: [query],
    queryFn: () => getMemberByPhoneNumber(query),
    enabled: query.length >= 11,
  });
  function handleClick() {
    setReceiverInfo(data.name, data.phoneNumber, '이음페이');
    router.push('/send-money/amount');
  }
  function SearchResult() {
    if (isError) return <p>해당 번호는 이음페이에 가입한 적이 없네요 ㅠㅠ</p>;
    else if (data) {
      return (
        <button className={classes.send} onClick={handleClick}>
          {data?.name}님에게 송금하기
        </button>
      );
    } else null;
  }

  return (
    <>
      <HeaderMain />
      <main className={classes.main}>
        <h1>어디로 돈을 보낼까요?</h1>

        <Tabs aria-label="Options" disabledKeys={['account']} fullWidth>
          <Tab key="ieum" title="이음">
            <Input
              variant="underlined"
              label="휴대폰 번호"
              type="number"
              inputMode="decimal"
              pattern="[0-9]*"
              className="w-100"
              onValueChange={setQuery}
            />

            <SearchResult />
          </Tab>
          <Tab key="account" title="계좌">
            <Input
              variant="underlined"
              label="계좌번호"
              type="number"
              pattern="\d*"
              value={account}
              onValueChange={(account) => setAccount(account)}
            />
            <Select
              label="은행 선택"
              variant="underlined"
              onSelectionChange={(e) => {
                console.log(e);
              }}
            >
              <SelectItem key="NH농협">NH농협</SelectItem>
              <SelectItem key="하나">하나</SelectItem>
              <SelectItem key="국민">국민</SelectItem>
            </Select>
          </Tab>
        </Tabs>
      </main>
      <TabBar selected="sendMoney" />
    </>
  );
}
export default WherePage;
