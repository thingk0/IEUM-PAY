import { useState } from 'react';
import classes from './Accordion.module.scss';
import ChevronDownIcon from './icons/ChevronDownIcon';
import DonationIcon from './icons/DonationIcon';
import { commaizeNumber } from '@toss/utils';
interface Detail {
  type: string;
  name: string;
  price: number;
}
interface History {
  historyId: number;
  historyDate: string;
  type: string;
  title: string;
  amount: number;
  detail: Detail[];
}
interface AccordionProps {
  history: History;
  children?: React.ReactNode;
}
function Accordion({ history }: AccordionProps) {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <div className={`${classes.accordion} ${isOpen && classes.open}`}>
      <button
        className={classes.button}
        onClick={() => setIsOpen((prev) => !prev)}
      >
        <div>
          <p className={classes.title}>
            <span className={classes.price}>
              {commaizeNumber(history.amount)}원
            </span>
            <span className={classes.donation}>
              <DonationIcon />
            </span>
          </p>
          <p className={classes.detail}>
            {history.type} | {history.title}
          </p>
        </div>
        <div className={`${classes.chevron} ${isOpen ? classes.open : ''}`}>
          <ChevronDownIcon />
        </div>
      </button>
      {isOpen && (
        <section>
          {history.detail
            .filter((e) => e.type === '충전')
            .map((e) => (
              <div>+{commaizeNumber(e.price)}원 이음페이머니 충전</div>
            ))}
        </section>
      )}
    </div>
  );
}
export default Accordion;
