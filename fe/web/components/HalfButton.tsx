import styles from './halfButton.module.scss';

interface HalfButtonProps {
  text: string;
  text2: string;
  btnStyle: string;
  btnStyle2: string;
  btnFunction: (e?: React.MouseEvent<HTMLButtonElement, MouseEvent>) => unknown;
  btnFunction2: (
    e?: React.MouseEvent<HTMLButtonElement, MouseEvent>,
  ) => unknown;
}
export default function HalfButton({
  text,
  text2,
  btnStyle,
  btnStyle2,
  btnFunction,
  btnFunction2,
}: HalfButtonProps) {
  return (
    <div className={styles.container}>
      <div className={styles.buttonContainer}>
        <button className={`${styles[btnStyle]}`} onClick={btnFunction}>
          {text}
        </button>
      </div>
      <div className={styles.buttonContainer}>
        <button className={`${styles[btnStyle2]}`} onClick={btnFunction2}>
          {text2}
        </button>
      </div>
    </div>
  );
}
