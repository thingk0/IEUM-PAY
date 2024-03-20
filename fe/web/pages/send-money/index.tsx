import TabBar from '@/stories/TabBar';
import { Tabs, Tab, Input, Select, SelectItem } from '@nextui-org/react';
import { useState } from 'react';

function WherePage() {
  const [account, setAccount] = useState<string>();
  const [bank, setBank] = useState<Selection>();
  return (
    <>
      <main>
        <h1>어디로 돈을 보낼까요?</h1>
        <div className="flex w-full flex-col">
          <Tabs aria-label="Options">
            <Tab key="ieum" title="이음">
              <Input variant="underlined" label="휴대폰 번호" />
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
              <Select label="은행 선택" variant="underlined" isOpen={false}>
                <SelectItem key="NH">NH농협</SelectItem>
              </Select>
            </Tab>
          </Tabs>
        </div>
      </main>
      <TabBar selected="sendMoney" />
    </>
  );
}
export default WherePage;
