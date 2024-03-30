import useDonateMoneyInfo from '@/hooks/useDirectDonationStore';
import styles from '@/styles/DirectDonationConfirmPage.module.scss';
import Header from '@/components/Header';
import Button from '@/stories/Button';
import { commaizeNumber } from '@toss/utils';
import DirectDonationIcon from '@/components/icons/DirectDonationIcon';
import { useRouter } from 'next/router';
import useUserStore from '@/stores/user-store';
import { directDonate } from '@/api/fundAxois';

function ConfirmPage() {
  const { donateMoneyInfo, setFundingId } = useDonateMoneyInfo();
  const { userInfo } = useUserStore();
  const router = useRouter();
  const onClickFunc = async () => {
    const fundingId = await directDonate(
      donateMoneyInfo.기관아이디,
      donateMoneyInfo.송금금액,
      donateMoneyInfo.남은금액,
    );
    if (fundingId != 0) {
      setFundingId(fundingId);
      router.push('/fundraising/complete');
    } else {
      console.log('실패ㅐㅐㅐㅐㅐ');
    }
  };
  return (
    <>
      <Header>직접 기부하기</Header>
      <main className={styles.main}>
        <div className={styles.icon}>
          <DirectDonationIcon />
        </div>
        <div className={styles.msg}>
          <p>
            <strong className={styles.name}>{donateMoneyInfo.기관명}</strong>
            에
            <br />
            {commaizeNumber(donateMoneyInfo.송금금액)}원 기부하기
          </p>
        </div>
        <Button primary size="thick" onClick={onClickFunc}>
          기부하기
        </Button>
      </main>
    </>
  );
}
export default ConfirmPage;
