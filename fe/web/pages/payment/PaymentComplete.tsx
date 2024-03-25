import PageTitleCenter from '@/components/PageTitleCenter';
import styles from './paymentComplete.module.scss';

export default function PaymentComplete() {
  return (
    <div className={styles.container}>
      <PageTitleCenter title={'결제 완료'} description={''} />
    </div>
  );
}
