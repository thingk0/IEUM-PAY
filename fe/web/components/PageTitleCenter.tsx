import CloseIcon from './icons/CloseIcon';
import styles from './pageTitleCenter.module.scss';
interface PageTitleCenterProps {
  children: React.ReactNode;
}
export default function PageTitleCenter({ children }: PageTitleCenterProps) {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>{children}</h1>
      <div className={styles.close}>
        <CloseIcon />
      </div>
    </div>
  );
}
