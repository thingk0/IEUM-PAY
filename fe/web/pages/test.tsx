// pages/index.tsx

import { useEffect, useState } from 'react';
import styles from './test.module.scss';

const Home = () => {
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

    // const cards = document.querySelectorAll(`.${styles.card}`);
    // cards.forEach((card, index) => {
    //   const prevCardClass = `.${styles['card' + prevCardState[index]]}`;
    //   const newCardClass = `.${styles['card' + updatedCardState[index]]}`;

    //   card.classList.remove(prevCardClass);
    //   card.classList.add(newCardClass);

    //   // 이동하는 텍스트에 대한 추가 처리
    //   const text = card.textContent;
    //   if (text) {
    //     const newText = String.fromCharCode(
    //       text.charCodeAt(0) + (updatedCardState[index] - prevCardState[index]),
    //     );
    //     card.textContent = newText;
    //   }
    // });
  };

  return (
    <div className={styles.cardStack} onClick={nextCard}>
      {cardState.map((card, index) => (
        <div key={index} className={`${styles.card} ${styles['card' + card]}`}>
          {/* {String.fromCharCode(64 + card)} */}
          {index}
        </div>
      ))}
    </div>
  );
};

export default Home;
