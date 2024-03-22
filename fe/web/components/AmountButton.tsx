import React from 'react';
import classes from './AmountButton.module.css';
interface AmountButtonProps {
  text: string;
  onClick: (e?: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
}
function AmountButton({ text, onClick }: AmountButtonProps) {
  return (
    <button
      onClick={(e) => {
        onClick(e);
        navigator?.vibrate(10);
      }}
      className={classes.button}
    >
      {text}
    </button>
  );
}
export default AmountButton;
