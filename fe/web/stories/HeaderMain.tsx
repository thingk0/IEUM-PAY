import SettingIcon from './icons/SettingIcon';
import classes from './HeaderMain.module.scss';
import Link from 'next/link';
import Image from 'next/image';

function HeaderMain() {
  return (
    <header className={classes.container}>
      <Link href="/">
        <Image src="/logo.svg" alt="이음페이 로고" width={138} height={21}></Image>
      </Link>
      <Link href="/settings">
        <SettingIcon />
      </Link>
    </header>
  );
}
export default HeaderMain;
