import ChevronRightIcon from './icons/ChevronRightIcon';
import styles from './money.module.scss';
import React from 'react';
interface MoneyProps {
  text: string;
  amount: string;
  onClick: (e?: React.MouseEvent<SVGSVGElement, MouseEvent>) => unknown;
}
export default function Money({ text, amount, onClick }: MoneyProps) {
  return (
    <div className={styles.container}>
      <p className={styles.text}>{text}</p>
      <div className={styles.amountContainer}>
        <p className={styles.amount}>{amount}원</p>
        <ChevronRightIcon />
      </div>
    </div>
  );
}
