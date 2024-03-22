import useSendMoneyInfo from '@/hooks/useSendMoneyStore';
import classes from '@/styles/ConfirmPage.module.scss';
import SendMoneyGradientIcon from '@/components/icons/SendMoneyGradientIcon';
import Button from '@/components/Button';
import { MouseEvent } from 'react';
import Header from '@/components/Header';

function ConfirmPage() {
  const { sendMoneyInfo, pushNumber, popNumber } = useSendMoneyInfo();
  return (
    <>
      <Header>이음페이 송금하기</Header>
      <main>
        <SendMoneyGradientIcon />
        <div className={classes.msg}>
          <p>
            <strong className={classes.name}>{sendMoneyInfo.수취인}</strong>
            님에게
          </p>
          <p>{sendMoneyInfo.송금금액}원을</p>
          <p>보낼까요?</p>
          <Button btnStyle="thickFill">확인</Button>
        </div>
      </main>
    </>
  );
}
export default ConfirmPage;
