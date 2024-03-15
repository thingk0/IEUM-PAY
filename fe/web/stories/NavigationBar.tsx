import Link from 'next/link';

interface NavigationBarProps {
  selected: 'history' | 'fundraising' | 'payment' | 'send-money' | 'my-page';
}

function NavigationBar({ selected = 'send-money' }: NavigationBarProps) {
  return (
    <footer>
      <ul>
        <li>
          <Link href="history">내역</Link>
        </li>
        <li>
          <Link href="">모금</Link>
        </li>
        <li>
          <Link href="">결제</Link>
        </li>
        <li>
          <Link href="">송금</Link>
        </li>
        <li>
          <Link href="">정보</Link>
        </li>
      </ul>
    </footer>
  );
}
export default NavigationBar;
