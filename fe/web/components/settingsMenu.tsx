import ChevronRightIcon from './icons/ChevronRightIcon';
import styles from './settingsMenu.module.scss';

interface SettingsMenuProps {
  title: string;
}
export default function SettingsMenu({ title }: SettingsMenuProps) {
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
