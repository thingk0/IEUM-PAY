import Button from '@/stories/Button';
import classes from './receipt.module.scss';
import PageTitleCenter from '@/components/PageTitleCenter';
import CustomDashedBorder from '@/components/CustomDashedBorder';
import { commaizeNumber } from '@toss/utils';
import { useRef } from 'react';
import html2canvas from 'html2canvas';
import { useRouter } from 'next/router';
import { useQuery } from '@tanstack/react-query';
import { getReceipt } from '@/api/historyAxios';
const data = {
  fundingId: 1,
  fundingTitle: 'btc 아동센터 아이들 20명에게',
  facilityName: '인천',
  historyDate: '2023-03-04 01:11:11',
  nickname: '기부니',
  donationAmount: 500,
  fundingSummary: '이것저것 외 14개',
};
function ReceiptElement({ children }: { children: React.ReactNode }) {
  return <div className={classes.element}>{children}</div>;
}

export default function ReceiptPage() {
  const router = useRouter();
  const receiptId = router.query.receiptId;
  const { data } = useQuery({
    queryKey: [receiptId],
    queryFn: () => getReceipt(receiptId),
  });
  const receiptRef = useRef<HTMLDivElement>(null);

  function handleClick() {
    if (receiptRef.current) {
      html2canvas(receiptRef.current).then(function (canvas) {
        const link = document.createElement('a');
        link.download = 'receipt.png';
        link.href = canvas.toDataURL('image/png');
        link.click();
      });
    }
  }

  return (
    <>
      {/* <PageTitleCenter title={''} description={''}></PageTitleCenter>
      <main className={classes.container}>
        <div className={classes.receipt} ref={receiptRef}>
          <h1>이음페이 기부 영수증</h1>
          <CustomDashedBorder />
          <h2>{data.fundingTitle}</h2>
          <CustomDashedBorder />
          <ReceiptElement>
            <b>기부자</b>
            <b>{data.nickname}</b>
          </ReceiptElement>
          <ReceiptElement>
            <b>배송지</b>
            <b>{data.facilityName}</b>
          </ReceiptElement>
          <ReceiptElement>
            <span>기부일시</span>
            <span>{data.historyDate}</span>
          </ReceiptElement>
          <CustomDashedBorder />
          <ReceiptElement>
            <span>배송품목</span>
            <span>{data.fundingSummary}</span>
          </ReceiptElement>
          <CustomDashedBorder />
          <ReceiptElement>
            <span>기부금</span>
            <span>{commaizeNumber(data.donationAmount)}원</span>
          </ReceiptElement>
        </div>
        <Button primary onClick={handleClick}>
          영수증 공유하기
        </Button>
      </main> */}
    </>
  );
}
