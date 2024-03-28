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
import Header from '@/components/Header';
import HeaderMain from '@/stories/HeaderMain';

function WherePage() {
  const [account, setAccount] = useState<string>();
  const [bank, setBank] = useState<Selection>();
  const router = useRouter();
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
            />
            <button
              className={classes.send}
              onClick={() => router.push('/send-money/amount')}
            >
              김싸피님에게 송금하기
            </button>
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
