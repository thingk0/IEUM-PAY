import React from 'react';
import Head from 'next/head';

import Image from 'next/image';
import { Inter } from 'next/font/google';
import styles from '@/styles/Home.module.css';
import mainStyles from './main.module.scss';

import { Main } from 'next/document';
import TabBar from '@/stories/TabBar';
import Money from '@/components/Money';
import HeaderMain from '@/stories/HeaderMain';
import Button from '@/stories/Button';

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
  const print = () => {
    console.log('clicked');
  };
  return (
    <>
      <HeaderMain />
      <main className={mainStyles.main}>
        <div className={mainStyles.cardsContainer}>
          <div className={mainStyles.card}>삼성 카드</div>
        </div>
        <div className={mainStyles.moneyContainer}>
          <Money text={'이음페이머니'} amount={'3,200'} onClick={print} />
          <hr />
          <Money text={'기부총액'} amount={'24,200'} onClick={print} />
        </div>
        <Button size="thick" onClick={print}>
          모금 연동하러 가기
        </Button>

        {/* <Button text={'thickFill'} btnStyle={'thickFill'} btnFunction={print} />
      <Button text={'thickLine'} btnStyle={'thickLine'} btnFunction={print} />
      <Button text={'thinFill'} btnStyle={'thinFill'} btnFunction={print} />
      <Button text={'thinLine'} btnStyle={'thinLine'} btnFunction={print} /> */}
      </main>
      <TabBar selected="payment" />
    </>
  );
}
