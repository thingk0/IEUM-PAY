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
import Link from 'next/link';
import { PlusIcon } from '@/components/icons/PlusIcon';
import useUserStore from '@/stores/user-store';

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
  const [focused, setFocused] = useState<number>();
  const [mainCard, setMainCard] = useState<number>();
  const { setBalance } = useUserStore();
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

  const goFund = () => {
    router.push('/fundraising');
  };

  useEffect(() => {
    async function fetchBalance() {
      try {
        const mainData = await getMainData();
        setInfo(mainData.data);
        setBalance(mainData.data.paymentAmount);
      } catch (e) {
        console.log(e);
      }
    }
    fetchBalance();
  }, []);

  const nextCard = () => {
    const updatedCardState = [...cardState];
    // console.log(cardState);
    updatedCardState.unshift(updatedCardState.pop()!);
    setPrevCardState([...cardState]);
    setCardState(updatedCardState);
  };

  const { data, error, isError, isLoading, refetch } = useQuery({
    queryKey: ['get-main'],
    queryFn: getMainData,
  });

  useEffect(() => {
    // setFocused(info.cardList[cardState.length - 1].registeredCardId);
    // console.log(prevCardState[cardState.length - 1]);
    // console.log(focused);
    console.log(info.cardList[0]);
    console.log(cardState[0]);
  }, [cardState]);

  useEffect(() => {
    if (!data) return;
    const initializeCards = () => {
      const initialCardState = Array.from(
        { length: Math.min(4, data.data.cardList.length) },
        (v, k) => k + 1,
      );
      setCardState(initialCardState);
      setPrevCardState([...initialCardState]);
    };

    initializeCards();
  }, [data]);
  if (!data) return null;

  return (
    <>
      <HeaderMain />
      <main className={mainStyles.main}>
        <div className={mainStyles.top}>
          <MainPageDropdown></MainPageDropdown>
        </div>
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
          {data.data.cardList.length === 0 && (
            <Link href="/card">
              <div className={mainStyles.empty}>
                <PlusIcon />
                카드 등록하기
              </div>
            </Link>
          )}
        </div>

        <div className={mainStyles.moneyContainer}>
          <Money
            text={'이음페이머니'}
            amount={data.data.paymentAmount}
            onClick={goFund}
          />
          <hr />
          <Money
            text={'기부총액'}
            amount={data.data.totalDonation}
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
