import AmountButtonList from '@/components/AmountButtonList';
import KeyPad from '../_components/KeyPad';
import useSendMoneyInfo from '@/hooks/useSendMoneyStore';
import classes from '@/styles/AmountPage.module.css';
import { useRouter } from 'next/router';
import { commaizeNumber, formatToKRW } from '@toss/utils';
import Header from '@/components/Header';

type KeyElement = string | number | JSX.Element;
function AmountPage() {
  const router = useRouter();
  const { sendMoneyInfo, pushNumber, popNumber } = useSendMoneyInfo();
  function handleClickNumber(v: KeyElement) {
    pushNumber(Number(v));
  }
  function handleClickDelete(v: KeyElement) {
    popNumber();
  }
  function handleClickConfirm(v: KeyElement) {
    router.push('/send-money/confirm');
  }
  function 송금금액() {
    return (
      <p
        className={`${classes.amount} ${sendMoneyInfo.송금금액 <= 0 ? classes.empty : ''}`}
      >
        {sendMoneyInfo.송금금액 > 0
          ? commaizeNumber(sendMoneyInfo.송금금액) + '원'
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
      return <>{formatToKRW(sendMoneyInfo.송금금액)}</>;
    }
  }
  return (
    <div className={classes.container}>
      <Header>이음페이 송금하기</Header>
      <main className={classes.main}>
        <div className={classes.wrapper}>
          <div>
            <p className={classes.receiver}>{sendMoneyInfo.수취인}</p>
            <p className={classes.receiverAccount}>
              {sendMoneyInfo.수취은행} {sendMoneyInfo.수취계좌}
            </p>
          </div>
          <div>
            <송금금액 />
            <송금금액_설명 />
          </div>

          <div>
            <div className={classes.senderInfo}>
              <span className="bank">{sendMoneyInfo.송금은행}</span>
              <span className="deposit">
                {commaizeNumber(sendMoneyInfo.잔액)}원
              </span>
            </div>
            <AmountButtonList />
          </div>
        </div>
      </main>
      <KeyPad
        onClickNumber={handleClickNumber}
        onClickDelete={handleClickDelete}
        onClickConfirm={handleClickConfirm}
      />
    </div>
  );
}
export default AmountPage;
