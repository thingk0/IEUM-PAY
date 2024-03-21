import React from 'react';
import Head from 'next/head';

import Image from 'next/image';
import { Inter } from 'next/font/google';
import styles from '@/styles/Home.module.css';
import mainStyles from './main.module.scss';

import Button from '@/components/Button';
import { Main } from 'next/document';
import TabBar from '@/stories/TabBar';
import Money from '@/components/_Money';

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
  const print = () => {
    console.log('clicked');
  };
  return (
    <>
      <Head>
        <title>IEUM PAY</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
      </Head>
      <main className={mainStyles.main}>
        <div className={mainStyles.cardsContainer}>
          <div className={mainStyles.card}>삼성 카드</div>
        </div>
        <div className={mainStyles.moneyContainer}>
          <Money text={'이음페이머니'} amount={'3,200'} />
          <hr />
          <Money text={'기부총액'} amount={'24,200'} />
        </div>
        <Button
          text={'모금 연동하러 가기'}
          btnStyle={'thickLine'}
          btnFunction={print}
        />

        {/* <Button text={'thickFill'} btnStyle={'thickFill'} btnFunction={print} />
      <Button text={'thickLine'} btnStyle={'thickLine'} btnFunction={print} />
      <Button text={'thinFill'} btnStyle={'thinFill'} btnFunction={print} />
      <Button text={'thinLine'} btnStyle={'thinLine'} btnFunction={print} /> */}
      </main>
      <TabBar selected="payment" />
    </>
  );
}
