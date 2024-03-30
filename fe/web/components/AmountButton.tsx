import React from 'react';
import classes from './AmountButton.module.css';
import Vibrate from '@/utils/vibrate';
interface AmountButtonProps {
  text: string;
  onClick: (e?: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
}
function AmountButton({ text, onClick }: AmountButtonProps) {
  function handleClick(e: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
    Vibrate(10);
    onClick(e);
  }
  return (
    <button onClick={handleClick} className={classes.button}>
      {text}
    </button>
  );
}
export default AmountButton;
