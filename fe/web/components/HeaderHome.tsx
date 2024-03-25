import ChevronLeftIcon from './icons/ChevronLeftIcon';
import classes from './HeaderHome.module.scss';
import { useRouter } from 'next/router';
import HomeIcon from './icons/HomeIcon';
import Link from 'next/link';
interface HeaderHomeProps {
  children: React.ReactNode;
}

export default function HeaderHome({ children }: HeaderHomeProps) {
  const router = useRouter();
  return (
    <header className={classes.header}>
      <div className={classes.wrapper}>
        <button className={classes.button} onClick={() => router.back()}>
          <ChevronLeftIcon />
        </button>
        <span className={classes.text}>{children}</span>
      </div>
      <Link href="/">
        <HomeIcon />
      </Link>
    </header>
  );
}
