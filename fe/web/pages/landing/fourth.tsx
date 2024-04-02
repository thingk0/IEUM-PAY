import styles from '@/styles/LandingPage.module.scss';
import { useRouter } from 'next/router';
import { useState } from 'react';

export default function LandingPageThird() {
  const router = useRouter();

  return (
    <div className={styles.container}>
      4
      <img
        className={styles.nav}
        src="/landing/navigation2.svg"
        alt="네비게이션 아이콘 두번째"
      />
    </div>
  );
}
