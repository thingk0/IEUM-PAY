import styles from './button.module.css';

interface ButtonProps {
  text: string;
  btnStyle: string;
  btnFunction: Function;
}
export default function Button({ text, btnStyle, btnFunction }: ButtonProps) {
  return (
    <div className={styles.container}>
      <button className={`${styles[btnStyle]}`} onClick={btnFunction}>
        {text}
      </button>
    </div>
  );
}
