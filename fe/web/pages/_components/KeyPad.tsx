import DeleteIcon from '../_icons/DeleteIcon';
import classes from './KeyPad.module.css';
interface KeyPadProps {
  onClickNumber: (e: KeyElement) => void;
  onClickDelete: (e: KeyElement) => void;
  onClickConfirm: (e: KeyElement) => void;
}
type KeyElement = string | number | JSX.Element;

export default function KeyPad({
  onClickNumber,
  onClickDelete,
  onClickConfirm,
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
    console.log(typeof v);
    console.log(v);
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
        {buttonList.map((v) => (
          <li className={classes.item}>
            <button onClick={() => handleClick(v)}>{v}</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
