import Link from 'next/link';
import './tabbar.css';
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
    <footer>
      <ul>
        <li>
          <Link href="history">
            <div
              className={`wrapper ${selected === tabBarElementCode.history ? 'active' : ''}`}
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
          <Link href="fundraising">
            <div
              className={`wrapper ${selected === tabBarElementCode.fundraising ? 'active' : ''}`}
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
          <Link href="payment">
            <div
              className={`wrapper ${selected === tabBarElementCode.payment ? 'active' : ''}`}
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
          <Link href="send-money">
            <div
              className={`wrapper ${selected === tabBarElementCode.sendMoney ? 'active' : ''}`}
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
          <Link href="my-page">
            <div
              className={`wrapper ${selected === tabBarElementCode.myPage ? 'active' : ''}`}
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
    </footer>
  );
}
export default TabBar;
