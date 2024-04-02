import styles from '@/styles/LandingPage.module.scss';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';

export default function LandingPageFourth() {
  const router = useRouter();

  const [changeCircle, setChangeCircle] = useState(false);

  useEffect(() => {
    const timeout = setTimeout(() => {
      setChangeCircle(true);
    }, 1000);
    return () => clearTimeout(timeout);
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.fifth}>
        <div className={styles.imgContainer}>
          <div className={styles.img}>
            {changeCircle ? (
              <img
                className={styles.imgMove1}
                src="/landing/logoEllipse2.svg"
                alt="원"
              />
            ) : (
              <img
                className={styles.imgMove1}
                src="/landing/logoEllipse.svg"
                alt="원"
              />
            )}
          </div>
          <div className={styles.img}>
            {changeCircle ? (
              <img
                className={styles.imgMove1}
                src="/landing/logoEllipse3.svg"
                alt="원"
              />
            ) : (
              <img
                className={styles.imgMove1}
                src="/landing/logoEllipse.svg"
                alt="원"
              />
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
