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

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
  const router = useRouter();
  const [balance, setMainBalance] = useState<number>();
  const { setBalance } = useUserStore();
  const goFund = () => {
    router.push('/fundraising');
  };

  useEffect(() => {
    async function fetchBalance() {
      let { data } = await getBalance();
      let balance = data.data;
      setMainBalance(balance);
      setBalance(balance);
    }
    fetchBalance();
  }, []);

  // 카드 관련
  const [cardState, setCardState] = useState<number[]>([]);
  const [prevCardState, setPrevCardState] = useState<number[]>([]);

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
  return (
    <>
      <HeaderMain />
      <main className={mainStyles.main}>
        <MainPageDropdown></MainPageDropdown>
        <div className={mainStyles.cardsContainer}>
          <div className={mainStyles.cardStack} onClick={nextCard}>
            {cardState.map((card, index) => (
              <div
                key={index}
                className={`${mainStyles.card} ${mainStyles['card' + card]} ${mainStyles['bank' + index]}`}
              >
                {/* {String.fromCharCode(64 + card)} */}
                {index}
              </div>
            ))}
          </div>
        </div>
        <div className={mainStyles.moneyContainer}>
          <Money text={'이음페이머니'} amount={balance} onClick={goFund} />
          <hr />
          <Money text={'기부총액'} amount={'24,200'} onClick={goFund} />
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
