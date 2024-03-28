import React from 'react';

import { Inter } from 'next/font/google';
import mainStyles from './main.module.scss';

import TabBar from '@/stories/TabBar';
import Money from '@/components/Money';
import HeaderMain from '@/stories/HeaderMain';
import Button from '@/stories/Button';
import { useRouter } from 'next/router';
import MainPageDropdown from '@/components/MainPageDropdown';

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
  const router = useRouter();
  const goFund = () => {
    router.push('/fundraising');
  };
  return (
    <>
      <HeaderMain />
      <main className={mainStyles.main}>
        <MainPageDropdown></MainPageDropdown>
        <div className={mainStyles.cardsContainer}>
          <div className={mainStyles.card}>삼성 카드</div>
        </div>
        <div className={mainStyles.moneyContainer}>
          <Money text={'이음페이머니'} amount={'3,200'} onClick={goFund} />
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
