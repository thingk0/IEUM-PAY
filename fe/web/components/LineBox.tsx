import styles from './lineBox.module.scss';
interface LineBox {
  main: string;
  sub: string;
}
export default function LineBox({ main, sub }: LineBox) {
  return (
    <div className={styles.container}>
      <p className={styles.main}>{main}</p>
      <p className={styles.sub}>{sub}</p>
    </div>
  );
}
