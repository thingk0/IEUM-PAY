import { useState } from 'react';
import classes from './Accordion.module.scss';
import ChevronDownIcon from './icons/ChevronDownIcon';
import DonationIcon from './icons/DonationIcon';
interface AccordionProps {
  price: number;
  transactionType: string;
  name: string;
  children?: React.ReactNode;
}
function Accordion({ price, transactionType, name, children }: AccordionProps) {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <div className={`${classes.accordion} ${isOpen && classes.open}`}>
      <button
        className={classes.button}
        onClick={() => setIsOpen((prev) => !prev)}
      >
        <div>
          <p className={classes.title}>
            <span className={classes.price}>{price}</span>
            <span className={classes.donation}>
              <DonationIcon />
            </span>
          </p>
          <p className={classes.detail}>
            {transactionType} | {name}
          </p>
        </div>
        <div className={`${classes.chevron} ${isOpen ? classes.open : ''}`}>
          <ChevronDownIcon />
        </div>
      </button>
      {isOpen && <section>{children}</section>}
    </div>
  );
}
export default Accordion;
