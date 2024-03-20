import Button from '@/components/Button';
export default function Main() {
  const print = () => {
    console.log('clicked');
  };
  return (
    <>
      <div className="cardsContainer">
        <div className="card">삼성 카드</div>
      </div>
      <div className="moneyContainer"></div>
      <Button
        text={'모금 연동하러 가기'}
        btnStyle={'thickLine'}
        btnFunction={print}
      />

      {/* <Button text={'thickFill'} btnStyle={'thickFill'} btnFunction={print} />
      <Button text={'thickLine'} btnStyle={'thickLine'} btnFunction={print} />
      <Button text={'thinFill'} btnStyle={'thinFill'} btnFunction={print} />
      <Button text={'thinLine'} btnStyle={'thinLine'} btnFunction={print} /> */}
    </>
  );
}
