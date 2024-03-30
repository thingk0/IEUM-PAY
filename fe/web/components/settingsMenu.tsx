import ChevronRightIcon from './icons/ChevronRightIcon';
import styles from './settingsMenu.module.scss';
import React from 'react';
interface SettingsMenuProps {
  children: string;
  onClick?: (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => unknown;
}
export default function SettingsMenu({ children, onClick }: SettingsMenuProps) {
  return (
    <>
      <button className={styles.container} onClick={onClick}>
        <p>{children}</p>
        <ChevronRightIcon />
      </button>
      <hr />
    </>
  );
}
