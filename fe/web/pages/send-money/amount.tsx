import { useState } from 'react';
import KeyPad from '../_components/KeyPad';
import useSendMoneyInfo from '@/hooks/useSendMoneyStore';
import classes from '@/styles/AmountPage.module.css';

type KeyElement = string | number | JSX.Element;
function AmountPage() {
  const { sendMoneyInfo, pushNumber, popNumber } = useSendMoneyInfo();
  function handleClickNumber(v: KeyElement) {
    pushNumber(Number(v));
  }
  function handleClickDelete(v: KeyElement) {
    popNumber();
  }
  function handleClickConfirm(v: KeyElement) {
    alert(`입력 금액은 ${sendMoneyInfo.송금금액}입니다.`);
  }
  function 송금금액() {
    return (
      <p
        className={`${classes.amount} ${sendMoneyInfo.송금금액 <= 0 ? classes.empty : ''}`}
      >
        {sendMoneyInfo.송금금액 > 0
          ? sendMoneyInfo.송금금액 + '원'
          : '얼마를 보낼까요?'}
      </p>
    );
  }
  function 송금금액_설명() {
    if (sendMoneyInfo.송금금액 === 0) {
      return;
    }
    if (sendMoneyInfo.송금금액 > sendMoneyInfo.잔액) {
      return <p className={classes.invalid}>출금가능금액 부족</p>;
    } else {
      return <>{sendMoneyInfo.송금금액}</>;
    }
  }
  return (
    <main className={classes.main}>
      <div>
        <p className={classes.receiver}>{sendMoneyInfo.수취인}</p>
        <p className={classes.receiverAccount}>
          {sendMoneyInfo.수취은행} {sendMoneyInfo.수취계좌}
        </p>
      </div>
      <송금금액 />
      <송금금액_설명 />
      <div className={classes.senderInfo}>
        <span className="bank">{sendMoneyInfo.송금은행}</span>
        <span className="deposit">{sendMoneyInfo.잔액}원</span>
      </div>
      <KeyPad
        onClickNumber={handleClickNumber}
        onClickDelete={handleClickDelete}
        onClickConfirm={handleClickConfirm}
      />
    </main>
  );
}
export default AmountPage;
