import React from 'react';
import Head from 'next/head';

import Image from 'next/image';
import { Inter } from 'next/font/google';
import styles from '@/styles/Home.module.css';
import Button from '@/components/Button';
import { Main } from 'next/document';

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
  return (
    <>
      <Head>
        <title>IEUM PAY</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
      </Head>
      <main>메인</main>
    </>
  );
}
