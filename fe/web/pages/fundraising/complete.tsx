import { useEffect, useState } from 'react';
import styles from '@/styles/DirectDonationComplete.module.scss';
import useDonateMoneyInfo from '@/hooks/useDirectDonationStore';
import { commaizeNumber } from '@toss/utils';
import { getDirectDonateRes } from '@/api/fundAxois';
import Button from '@/stories/Button';
import { useRouter } from 'next/router';

interface dataType {
  fundingId: number;
  fundingTitle: string;
  facilityName: string;
  facilityImage: string;
}

export default function CompleteDonation() {
  const { donateMoneyInfo } = useDonateMoneyInfo();
  const router = useRouter();
  const [data, setData] = useState<dataType>({
    fundingId: 1,
    fundingTitle: '펀딩 제목',
    facilityName: '은혜노인복지센터',
    facilityImage:
      'https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200124_169%2F1579861444105ksyLS_JPEG%2FHR80O6nA89Q9ZWBHq4KZQL_-.jpeg.jpg',
  });

  const onClickFunc = () => {
    router.push('/main');
  };

  useEffect(() => {
    async function dataLoad() {
      try {
        const getData = await getDirectDonateRes(donateMoneyInfo.펀딩아이디);
        getData != undefined ? setData(getData) : '';
      } catch (e) {
        console.log(e);
      }
    }
    dataLoad();

    history.pushState(null, '', location.href);
    window.addEventListener('popstate', () => {
      browserPreventEvent();
    });
    return () => {
      window.removeEventListener('popstate', () => {
        browserPreventEvent();
      });
    };
  }, []);

  return (
    <>
      <div className={styles.container}>
        <h1>기부 완료</h1>
        <div className={styles.imageBox}>
          <img src="/icon-256x256.png" alt="이음페이 로고" />
        </div>
        <div className={styles.textBox}>
          <p>{donateMoneyInfo.기관명}에</p>
          <p>{commaizeNumber(donateMoneyInfo.송금금액)}원을 기부 했어요!</p>
        </div>
        <div className={styles.facilityContainer}>
          <div className={styles.imageBox}>
            <img src={data.facilityImage} alt="기관 이미지" />
          </div>
          <div className={styles.textContainer}>
            <p>{donateMoneyInfo.기관명}</p>
            <p>{data.fundingTitle}</p>
          </div>
        </div>
        <Button primary size="thick" onClick={() => {}}>
          완료
        </Button>
      </div>
    </>
  );
}

/**
 * 뒤로가기 막는거
 */
export const browserPreventEvent = () => {
  history.pushState(null, '', location.href);
};
