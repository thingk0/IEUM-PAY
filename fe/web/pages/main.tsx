import React, { useEffect, useState } from 'react';

import { Inter } from 'next/font/google';
import mainStyles from './main.module.scss';
import bank from './bank.module.scss';

import TabBar from '@/stories/TabBar';
import Money from '@/components/Money';
import HeaderMain from '@/stories/HeaderMain';
import Button from '@/stories/Button';
import { useRouter } from 'next/router';
import MainPageDropdown from '@/components/MainPageDropdown';
// import { getBalance } from '@/api/paymentAxios';
// import useUserStore from '@/stores/user-store';
import { getMainData } from '@/api/userAxois';
import { useQuery } from '@tanstack/react-query';
import Card from '@/components/Card';

const inter = Inter({ subsets: ['latin'] });

interface cardType {
  cardId: number;
  cardIssuer: string;
  cardNickname: string;
  mainCard: boolean;
  registeredCardId: number;
}
interface infoType {
  cardList: cardType[];
  linked: boolean;
  paymentAmount: number;
  totalDonation: number;
}
interface logoUrlType {
  신한카드: string;
  KB국민카드: string;
  광주은행: string;
  삼성카드: string;
  수협은행: string;
  NH농협카드: string;
  BC카드: string;
  우리카드: string;
  롯데카드: string;
  현대카드: string;
  하나카드: string;
  전북은행: string;
  제주은행: string;
  씨티카드: string;
  이음페이: string;
}

export default function Home() {
  const router = useRouter();
  const [cardState, setCardState] = useState<number[]>([]);
  const [prevCardState, setPrevCardState] = useState<number[]>([]);
  const [info, setInfo] = useState<infoType>({
    cardList: [
      {
        cardId: 1318,
        cardIssuer: 'KB국민카드',
        cardNickname: 'KB국민카드',
        mainCard: false,
        registeredCardId: 12,
      },
    ],
    linked: true,
    paymentAmount: 8000,
    totalDonation: 1843490,
  });

  const logoUrl: logoUrlType = {
    신한카드: 'https://www.shinhancard.com/pconts/images/shcard/ci-shinhan.png',
    KB국민카드: 'https://img1.kbcard.com/LT/images_r/common/kbcard_Logo_v3.png',
    광주은행:
      'https://i.namu.wiki/i/nvBUwz04V7Nku4grdLmnwCPlpM_Feo5k7TZeL_ulRUT6Ic1qIn_R8WuFkyO9xA5BVWzL8iF2tJXxLtOgnaiLHg.svg',
    삼성카드: 'https://www.inicis.com/apple/popup/img/card_ss.png',
    수협은행:
      'https://i.namu.wiki/i/aGBgqIBemO3l45_2-CL6CMxF8DNXNZHRwkchw_b-byNVPmitL1UC6Ydv-9-6MVQdJWCttJvFwGdiIWEaBwp1ww.svg',
    NH농협카드: 'https://card.nonghyup.com/images/IP/cc/layout/n_logo.png',
    BC카드:
      'https://i.namu.wiki/i/8C2vbjyHD6CuR-QXhE5rFxRUDbANfr5OrSNxW_24bihqOYM1VUxR84cwTtXZ_3RE7x4qqb2Ald_YDBUiiF9Wag.svg',
    우리카드:
      'https://biz.chosun.com/resizer/YR43ZY7Vjg3tSGXvdOD4T0SLayM=/616x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/QVXYKQKAHJEGJFI2SNI3HZTKZM.png',
    롯데카드:
      'https://i.namu.wiki/i/KPgfJH2zZxOvGgO_6QU-XUZptxNfiZXyCMoeMu_p1PKM2WT_XG2j-C84wft7fE02rQYzsadt1dh8GdobWwq7dw.svg',
    현대카드:
      'https://upload.wikimedia.org/wikipedia/commons/f/f0/Hyundai_card_CI_new.png',
    하나카드:
      'https://i.namu.wiki/i/8JEi0JqCKkxGgCNptgwW8idqtPlZzc7RnuD4cbojlt1D45iybb0qQwQtXnJECnCG0ZUdPqK_pog0DQjNYVv1Mg.svg',
    전북은행:
      'https://i.namu.wiki/i/cxJG39QcJ8i5P4dznfK-ELik8uaNIWnvTrLv40_xe9JT2ENcSFfjJ1PGutPPU0JyYnxW5LVoZJYjUtFfjgHF6g.svg',
    제주은행:
      'https://www.shinhangroup.com/kr/asset/images/introduce/shinhan_CI_kr_2022-08.png',
    씨티카드: 'https://rasta.co.kr/web/upload/img/card/card_ct.png',
    이음페이: '/longLogo.svg',
  };

  const goFund = () => {
    router.push('/fundraising');
  };

  useEffect(() => {
    async function fetchBalance() {
      try {
        const mainData = await getMainData();
        setInfo(mainData.data);
      } catch (e) {
        console.log(e);
      }
    }
    fetchBalance();
  }, []);

  useEffect(() => {
    const initializeCards = () => {
      const initialCardState = [1, 2, 3, 4];
      setCardState(initialCardState);
      setPrevCardState([...initialCardState]);
    };

    initializeCards();
  }, []);

  const nextCard = () => {
    const updatedCardState = [...cardState];
    updatedCardState.unshift(updatedCardState.pop()!);
    setPrevCardState([...cardState]);
    setCardState(updatedCardState);
  };

  const { data, error, isError, isLoading, refetch } = useQuery({
    queryKey: ['get-main'],
    queryFn: getMainData,
  });

  return (
    <>
      <HeaderMain />
      <main className={mainStyles.main}>
        <MainPageDropdown></MainPageDropdown>
        <div className={mainStyles.cardsContainer}>
          <div className={mainStyles.cardStack} onClick={nextCard}>
            {isLoading
              ? null
              : cardState.map((card, index) => (
                  <Card
                    index={index}
                    card={card}
                    bank={info.cardList[index]?.cardIssuer}
                    nickname={info.cardList[index]?.cardNickname}
                  />
                ))}
          </div>
        </div>
        <div className={mainStyles.moneyContainer}>
          <Money
            text={'이음페이머니'}
            amount={info.paymentAmount}
            onClick={goFund}
          />
          <hr />
          <Money
            text={'기부총액'}
            amount={info.totalDonation}
            onClick={goFund}
          />
        </div>
      </main>
      <div className={mainStyles.btnContainer}>
        <Button size="thick" onClick={goFund}>
          모금 연동하러 가기
        </Button>
      </div>
      <TabBar selected="payment" />
    </>
  );
}
