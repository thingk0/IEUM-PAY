import styles from './pageTitleLeft.module.scss';

interface PageTitleLeftProps {
  title: string;
  description?: string | null;
  description2?: string | null;
}
export default function PageTitleLeft({
  title,
  description,
  description2,
}: PageTitleLeftProps) {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>{title}</h1>
      <p className={styles.description}>{description}</p>
      <p className={styles.description}>{description2}</p>
    </div>
  );
}
