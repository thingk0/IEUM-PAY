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
        <svg
          width="8"
          height="20"
          viewBox="0 0 12 20"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
          onClick={onClick}
        >
          <path
            fill-rule="evenodd"
            clip-rule="evenodd"
            d="M1.05719 18.9428C0.53649 18.4221 0.53649 17.5779 1.05719 17.0572L8.11438 10L1.05719 2.94282C0.536489 2.42212 0.536489 1.5779 1.05719 1.0572C1.57789 0.536504 2.42211 0.536503 2.94281 1.0572L10.9428 9.0572C11.4635 9.5779 11.4635 10.4221 10.9428 10.9428L2.94281 18.9428C2.42211 19.4635 1.57789 19.4635 1.05719 18.9428Z"
            fill="#2b2b2b"
          />
        </svg>
      </div>
    </div>
  );
}
