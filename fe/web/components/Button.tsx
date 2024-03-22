import styles from './button.module.scss';

interface ButtonProps {
  text?: string;
  btnStyle: 'thickFill' | 'thinFill' | 'thinLine' | 'thickLine';
  children?: React.ReactNode;
  btnFunction?: (
    e?: React.MouseEvent<HTMLButtonElement, MouseEvent>,
  ) => unknown;
}
export default function Button({
  text,
  btnStyle,
  btnFunction,
  children,
}: ButtonProps) {
  return (
    <div className={styles.container}>
      <button className={`${styles[btnStyle]}`} onClick={btnFunction}>
        {text?.length ? text : children}
      </button>
    </div>
  );
}
