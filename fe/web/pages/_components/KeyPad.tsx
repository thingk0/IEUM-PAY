import DeleteIcon from '../_icons/DeleteIcon';
import classes from './KeyPad.module.scss';
interface KeyPadProps {
  onClickNumber: (e: KeyElement) => void;
  onClickDelete: (e: KeyElement) => void;
  onClickConfirm: (e: KeyElement) => void;
  isValid: boolean;
}
type KeyElement = string | number | JSX.Element;

export default function KeyPad({
  onClickNumber,
  onClickDelete,
  onClickConfirm,
  isValid,
}: KeyPadProps) {
  const buttonList: KeyElement[] = [
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    <DeleteIcon />,
    0,
    '완료',
  ];
  function handleClick(v: KeyElement) {
    navigator?.vibrate(10);
    if (!isNaN(Number(v))) {
      onClickNumber(v);
    } else if (v === '완료') {
      onClickConfirm(v);
    } else {
      onClickDelete(v);
    }
  }
  return (
    <div className={classes.keypad}>
      <ul className={classes.container}>
        {buttonList
          .filter((e) => (isValid ? true : e !== '완료'))
          .map((v) => (
            <li className={classes.item} key={v.toString()}>
              <button className={classes.btn} onClick={() => handleClick(v)}>
                {v}
              </button>
            </li>
          ))}
      </ul>
    </div>
  );
}
