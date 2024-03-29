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

interface Member {
  memberId: number;
  name: string;
  phoneNumber: string;
}
function WherePage() {
  const [account, setAccount] = useState<string>();
  const [bank, setBank] = useState<Selection>();
  const router = useRouter();
  const [query, setQuery] = useState('');
  const [searchResult, setSearchResult] = useState<Member>();
  // 입력 값이 변경될 때마다 타이머 설정
  useEffect(() => {
    const delayDebounceTimer = setTimeout(() => {
      // 여기에 API 요청 코드 넣으면 됨
      getMemberByPhoneNumber(query)
        .then((data) => setSearchResult(data))
        .catch();
      // 받아온 값을 setSearchResults에 저장
    }, 1000); // 1s 디바운스 지연 시간
    // 이전에 설정한 타이머를 클리어하여 디바운스 취소
    return () => clearTimeout(delayDebounceTimer);
  }, [query]);
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
            {searchResult?.name ? (
              <button
                className={classes.send}
                onClick={() => router.push('/send-money/amount')}
              >
                {searchResult?.name}님에게 송금하기
              </button>
            ) : (
              <p>해당 번호는 이음페이에 가입한 적이 없네요 ㅠㅠ</p>
            )}
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
