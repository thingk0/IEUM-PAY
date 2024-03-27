import Button from '@/stories/Button';
import classes from './receipt.module.scss';
import PageTitleCenter from '@/components/PageTitleCenter';
function ReceiptElement({
  title,
  value,
}: {
  title: React.ReactNode;
  value: React.ReactNode;
}) {
  return (
    <div className={classes.element}>
      <span className={classes.title}>{title}</span>
      <span className={classes.value}>{value}</span>
    </div>
  );
}
export default function ReceiptPage() {
  const data = {
    fundingId: 1,
    fundingTitle: 'btc 아동센터 아이들 20명에게',
    facilityName: '인천',
    historyDate: '2023-03-04 01:11:11',
    nickname: '기부니',
    donationAmount: 500,
    fundingSummary: '이것저것 외 14개',
  };
  return (
    <>
      <PageTitleCenter title={''} description={''}></PageTitleCenter>
      <main>
        <div className={classes.receipt}>
          <h1>{data.fundingId}</h1>
          <hr />
          <h2>{data.fundingTitle}</h2>
          <hr />
          <ReceiptElement title="기부자" value={data.nickname}></ReceiptElement>
          <ReceiptElement
            title="배송지"
            value={data.facilityName}
          ></ReceiptElement>
          <ReceiptElement
            title="기부일시"
            value={data.historyDate}
          ></ReceiptElement>
          <hr />
          <ReceiptElement
            title="배송 품목"
            value={data.fundingSummary}
          ></ReceiptElement>
          <hr />
          <ReceiptElement
            title="기부금"
            value={data.donationAmount}
          ></ReceiptElement>
        </div>
        <Button primary>영수증 공유하기</Button>
      </main>
    </>
  );
}
