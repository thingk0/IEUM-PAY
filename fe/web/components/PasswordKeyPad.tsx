import { shuffle } from '@toss/utils';
import ArrowLeftIcon from './icons/ArrowLeftIcon';
import classes from './PasswordKeyPad.module.css';
import { useEffect, useState } from 'react';
interface KeyPadProps {
  onClickNumber: (e: number) => void;
  onClickDelete: (e: JSX.Element) => void;
}
type KeyElement = number | JSX.Element;

export default function KeyPad({ onClickNumber, onClickDelete }: KeyPadProps) {
  const [buttonList, setButtonList] = useState([1, 2, 3, 4, 5, 6, 7, 8, 9, 0]);
  useEffect(() => {
    setButtonList((prev) => shuffle(prev));
  }, []);
  function handleClick(v: KeyElement) {
    if (typeof v === 'number') {
      onClickNumber(v);
    } else {
      onClickDelete(v);
    }
  }
  return (
    <div className={classes.keypad}>
      <ul className={classes.container}>
        {buttonList.slice(0, 9).map((v) => (
          <li className={classes.item} key={v.toString()}>
            <button className={classes.key} onClick={() => onClickNumber(v)}>
              {v}
            </button>
          </li>
        ))}
        <li className={classes.item}></li>

        <li className={classes.item}>
          <button
            className={classes.key}
            onClick={() => onClickNumber(buttonList[9])}
          >
            {buttonList[9]}
          </button>
        </li>
        <li className={classes.item}>
          <button
            className={classes.key}
            onClick={() => onClickDelete(<ArrowLeftIcon />)}
          >
            <ArrowLeftIcon />
          </button>
        </li>
      </ul>
    </div>
  );
}
