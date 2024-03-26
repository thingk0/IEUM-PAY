import CloseIcon from './icons/CloseIcon';
import styles from './pageTitleCenter.module.scss';
interface PageTitleCenterProps {
  title: string;
  description: string;
}
export default function PageTitleCenter({
  title,
  description,
}: PageTitleCenterProps) {
  return (
    <div className={styles.container}>
      <div className={styles.close}>
        <CloseIcon />
      </div>
      <h1 className={styles.title}>{title}</h1>
      <p className={styles.description}>{description}</p>
    </div>
  );
}
