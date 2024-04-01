import React, { useEffect, useState } from 'react';

import { Inter } from 'next/font/google';
import mainStyles from './main.module.scss';

import TabBar from '@/stories/TabBar';
import Money from '@/components/Money';
import HeaderMain from '@/stories/HeaderMain';
import Button from '@/stories/Button';
import { useRouter } from 'next/router';
import MainPageDropdown from '@/components/MainPageDropdown';
import { getBalance } from '@/api/paymentAxios';
import useUserStore from '@/stores/user-store';
import { getMainData } from '@/api/userAxois';

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

export default function Home() {
  const router = useRouter();
  const [balance, setMainBalance] = useState<number>();
  const { setBalance } = useUserStore();
  const goFund = () => {
    router.push('/fundraising');
  };
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

  useEffect(() => {
    async function fetchBalance() {
      let { data } = await getBalance();
      let balance = data.data;
      setMainBalance(balance);
      setBalance(balance);
      const mainData = await getMainData();
      mainData != undefined ? setInfo(mainData.data) : '';
      console.log(mainData.data);
    }
    fetchBalance();
  }, []);
  return (
    <>
      <HeaderMain />
      <main className={mainStyles.main}>
        <MainPageDropdown></MainPageDropdown>
        <div className={mainStyles.cardsContainer}>
          <div className={mainStyles.card}>삼성 카드</div>
        </div>
        <div className={mainStyles.moneyContainer}>
          <Money text={'이음페이머니'} amount={balance} onClick={goFund} />
          <hr />
          <Money text={'기부총액'} amount={'24,200'} onClick={goFund} />
        </div>

        {/* <Button text={'thickFill'} btnStyle={'thickFill'} btnFunction={print} />
      <Button text={'thickLine'} btnStyle={'thickLine'} btnFunction={print} />
      <Button text={'thinFill'} btnStyle={'thinFill'} btnFunction={print} />
    <Button text={'thinLine'} btnStyle={'thinLine'} btnFunction={print} /> */}
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
