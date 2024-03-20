import Link from 'next/link';
import classes from './tabbar.module.css';
import CreditCardIcon from './icons/CreditCardIcon';
import HistoryIcon from './icons/HistoryIcon';
import UserIcon from './icons/UserIcon';
import SendMoneyIcon from './icons/SendMoneyIcon';
import FundraisingIcon from './icons/FundraisingIcon';
import HistoryIconActive from './icons/HistoryIconActive';
import FundraisingIconActive from './icons/FundraisingIconActive';
import UserIconActive from './icons/UserIconActive';
import CreditCardIconActive from './icons/CreditCardIconActive';
import SendMoneyIconActive from './icons/SendMoneyIconActive';

//object literal
export const tabBarElementCode = {
  history: 'history',
  fundraising: 'fundraising',
  payment: 'payment',
  sendMoney: 'sendMoney',
  myPage: 'myPage',
} as const;

export type TabBarElementCodeValue =
  (typeof tabBarElementCode)[keyof typeof tabBarElementCode];

interface TabBarProps {
  selected: TabBarElementCodeValue;
}

function TabBar({ selected = tabBarElementCode.history }: TabBarProps) {
  return (
    <nav className={classes.container}>
      <ul className={classes.ul}>
        <li>
          <Link href="history" className={classes.link}>
            <div
              className={`${classes.wrapper} ${selected === tabBarElementCode.history ? classes.active : ''}`}
            >
              {selected === tabBarElementCode.history ? (
                <HistoryIconActive />
              ) : (
                <HistoryIcon />
              )}
              내역
            </div>
          </Link>
        </li>
        <li>
          <Link href="fundraising" className={classes.link}>
            <div
              className={`${classes.wrapper} ${selected === tabBarElementCode.fundraising ? classes.active : ''}`}
            >
              {selected === tabBarElementCode.fundraising ? (
                <FundraisingIconActive />
              ) : (
                <FundraisingIcon />
              )}
              모금
            </div>
          </Link>
        </li>
        <li>
          <Link href="/" className={classes.link}>
            <div
              className={`${classes.wrapper} ${selected === tabBarElementCode.payment ? classes.active : ''}`}
            >
              {selected === tabBarElementCode.payment ? (
                <CreditCardIconActive />
              ) : (
                <CreditCardIcon />
              )}
              결제
            </div>
          </Link>
        </li>
        <li>
          <Link href="send-money" className={classes.link}>
            <div
              className={`${classes.wrapper} ${selected === tabBarElementCode.sendMoney ? classes.active : ''}`}
            >
              {selected === tabBarElementCode.sendMoney ? (
                <SendMoneyIconActive />
              ) : (
                <SendMoneyIcon />
              )}
              송금
            </div>
          </Link>
        </li>
        <li>
          <Link href="my-page" className={classes.link}>
            <div
              className={`${classes.wrapper} ${selected === tabBarElementCode.myPage ? classes.active : ''}`}
            >
              {selected === tabBarElementCode.myPage ? (
                <UserIconActive />
              ) : (
                <UserIcon />
              )}
              정보
            </div>
          </Link>
        </li>
      </ul>
    </nav>
  );
}
export default TabBar;
