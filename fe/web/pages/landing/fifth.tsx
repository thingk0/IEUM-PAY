import styles from '@/styles/LandingPage.module.scss';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';

export default function LandingPageFourth() {
  const router = useRouter();

  useEffect(() => {
    const timeout1 = setTimeout(() => {
      router.push('/landing/start');
    }, 6000);
    return () => {
      console.log('test');
      // clearTimeout(timeout1);
    };
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.fifth}>
        <div className={styles.imgContainer}>
          <div className={styles.img6}>
            <img
              className={styles.appear2}
              src="/landing/logoEllipse5.svg"
              alt="이음페이 로고"
            />
          </div>

          <div className={styles.img3}>
            <img
              className={styles.imgMove1}
              src="/landing/logoEllipse2.svg"
              alt="원"
            />
          </div>
          <div className={styles.img4}>
            <img
              className={styles.imgMove2}
              src="/landing/logoEllipse3.svg"
              alt="원"
            />
          </div>

          <div className={styles.img1}>
            <img
              className={styles.disapear1}
              src="/landing/logoEllipse.svg"
              alt="원"
            />
          </div>
          <div className={styles.img2}>
            <img
              className={styles.disapear1}
              src="/landing/logoEllipse.svg"
              alt="원"
            />
          </div>
          <div className={styles.img5}>
            <img
              className={styles.appear2}
              src="/landing/logoEllipse4.svg"
              alt="로고 뼈대"
            />
          </div>
        </div>
      </div>
    </div>
  );
}
