import useSendMoneyInfo from '@/hooks/useSendMoneyStore';
import { Button } from '@nextui-org/react';
import classes from '@/styles/ConfirmPage.module.css';

function ConfirmPage() {
  const { sendMoneyInfo, pushNumber, popNumber } = useSendMoneyInfo();
  return (
    <main>
      <p>{sendMoneyInfo.수취인}님에게</p>
      <p>{sendMoneyInfo.송금금액}원을</p>
      <p>보낼까요?</p>
      <Button>확인</Button>
    </main>
  );
}
export default ConfirmPage;
