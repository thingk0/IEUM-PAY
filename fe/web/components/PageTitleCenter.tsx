import CloseIcon from './icons/CloseIcon';
import styles from './pageTitleCenter.module.scss';
interface PageTitleCenterProps {
  title: 'string';
}
export default function PageTitleCenter({ title }: PageTitleCenterProps) {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>{title}</h1>
      <div className={styles.close}>
        <CloseIcon />
      </div>
    </div>
  );
}
