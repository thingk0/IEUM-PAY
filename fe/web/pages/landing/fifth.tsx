import styles from '@/styles/LandingPage.module.scss';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';

export default function LandingPageFourth() {
  const router = useRouter();

  const [changeCircle, setChangeCircle] = useState(false);
  const [changeToLogoWire, setChangeToLogoWire] = useState(false);
  const [changeToLogo, setChangeToLogo] = useState(false);

  useEffect(() => {
    const timeout1 = setTimeout(() => {
      setChangeCircle(true);
    }, 1000);
    const timeout2 = setTimeout(() => {
      setChangeToLogoWire(true);
    }, 2800);
    const timeout3 = setTimeout(() => {
      setChangeToLogo(true);
    }, 3700);
    const timeout4 = setTimeout(() => {
      router.push('/landing/start');
    }, 4700);
    return () => {
      clearTimeout(timeout1);
      clearTimeout(timeout2);
      clearTimeout(timeout3);
    };
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.fifth}>
        <div className={styles.imgContainer}>
          {changeToLogo ? (
            <div className={styles.img}>
              <img src="/landing/logoEllipse5.svg" alt="이음페이 로고" />
            </div>
          ) : changeToLogoWire ? (
            <div className={styles.img}>
              <img src="/landing/logoEllipse4.svg" alt="로고 뼈대" />
            </div>
          ) : (
            <>
              <div className={styles.img}>
                {changeCircle ? (
                  <img
                    className={styles.imgMove1}
                    src="/landing/logoEllipse2.svg"
                    alt="원"
                  />
                ) : (
                  <img
                    className={styles.disapear1}
                    src="/landing/logoEllipse.svg"
                    alt="원"
                  />
                )}
              </div>
              <div className={styles.img}>
                {changeCircle ? (
                  <img
                    className={styles.imgMove2}
                    src="/landing/logoEllipse3.svg"
                    alt="원"
                  />
                ) : (
                  <img
                    className={styles.disapear1}
                    src="/landing/logoEllipse.svg"
                    alt="원"
                  />
                )}
              </div>
            </>
          )}
        </div>
      </div>
    </div>
  );
}
