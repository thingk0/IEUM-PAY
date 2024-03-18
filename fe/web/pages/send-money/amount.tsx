import { useState } from 'react';
import KeyPad from '../_components/KeyPad';

type KeyElement = string | number | JSX.Element;
function AmountPage() {
  const [name,setName] = useState("김싸피");
  const [amount, setAmount] = useState<number>(0);
  const [deposit, setDeposit] = useState<number>(30000);
  function handleClickNumber(v: KeyElement) {
    setAmount((prev) => prev * 10 + Number(v));
  }
  function handleClickDelete(v: KeyElement) {
    setAmount((prev) => Math.trunc(prev / 10));
  }
  function handleClickConfirm(v: KeyElement) {
    alert(`입력 금액은 ${amount}입니다.`);
  }
  return (
    <main>
      <div>
        <p>{name}</p>
        <p>토스뱅크 123-123-123</p>
      </div>
      <p>{amount > 0 ? amount + '원' : '얼마를 보낼까요?'}</p>
      {amount > deposit && <p>초과</p>}
      <div>
        <span>이음페이</span>
        <span>30,000원</span>
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
