import useSendMoneyInfo from '@/hooks/useSendMoneyStore';
import AmountButton from './AmountButton';
import classes from './AmountButtonList.module.css';
function AmountButtonList() {
  const { sendMoneyInfo, addAmount, setFullAmount } = useSendMoneyInfo();
  const buttons = [
    {
      text: '+1천',
      onClick: () => {
        addAmount(1000);
      },
    },
    {
      text: '+5천',
      onClick: () => {
        addAmount(5000);
      },
    },
    {
      text: '+1만',
      onClick: () => {
        addAmount(10000);
      },
    },
    {
      text: '+5만',
      onClick: () => {
        addAmount(50000);
      },
    },
    {
      text: '+10만',
      onClick: () => {
        addAmount(100000);
      },
    },
    {
      text: '전액',
      onClick: () => {
        setFullAmount();
      },
    },
  ];
  return (
    <ul className={classes.ul}>
      {buttons.map((button) => (
        <li>
          <AmountButton text={button.text} onClick={button.onClick} />
        </li>
      ))}
    </ul>
  );
}
export default AmountButtonList;
