import { ReactNode } from 'react';
import ChevronLeftIcon from './icons/ChevronLeftIcon';
import classes from './Header.module.css';
import { useRouter } from 'next/router';
interface HeaderProps {
  children: ReactNode;
}
function Header({ children }: HeaderProps) {
  const router = useRouter();
  return (
    <header className={classes.header}>
      <button onClick={() => router.back()}>
        <ChevronLeftIcon />
      </button>
      {children}
    </header>
  );
}
export default Header;
