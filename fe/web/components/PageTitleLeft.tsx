import styles from './pageTitleLeft.module.scss';

interface PageTitleLeftProps {
  title: string;
  description: string | null;
}
export default function PageTitleLeft({
  title,
  description,
}: PageTitleLeftProps) {
  return (
    <div className={styles.container}>
      <h1 className={styles.title}>{title}</h1>
      <p className={styles.description}>{description}</p>
    </div>
  );
}
