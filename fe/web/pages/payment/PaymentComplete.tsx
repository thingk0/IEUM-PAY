import PageTitleCenter from '@/components/PageTitleCenter';
import styles from './paymentComplete.module.scss';
import LineBox from '@/components/LineBox';
import Button from '@/stories/Button';
import { commaizeNumber } from '@toss/utils';
import CompleteBadge from '@/public/pageBadges/CompleteBadge';
import { useRouter } from 'next/router';

interface PaymentComplete {
  response: Object;
}
export default function CompletePage({}) {
  const router = useRouter();
  const goMain = () => {
    router.push({
      pathname: '/',
    });
  };
  return (
    <div className={styles.container}>
      <PageTitleCenter title={'결제 완료'} description={''} />
      <div className={styles.badge}>
        <CompleteBadge />
      </div>
      <div className={styles.LineBoxes}>
        <LineBox main={`구매 결제 : ${commaizeNumber(5600)}원`} sub={'쿠팡'} />
        <LineBox main={'잔돈 기부 : 400원'} sub={'BTC 아동센터'} />
      </div>
      <div className={styles.btnContainer}>
        <Button primary size="thick" onClick={goMain}>
          확인
        </Button>
      </div>
    </div>
  );
}
