import ChevronRightIcon from './icons/ChevronRightIcon';
import styles from './settingsMenu.module.scss';

interface SettingsMenu {
  title: string;
}
export default function SettingsMenu({ title }) {
  return (
    <>
      <div className={styles.container}>
        <p>{title}</p>
        <ChevronRightIcon />
      </div>
      <hr />
    </>
  );
}
