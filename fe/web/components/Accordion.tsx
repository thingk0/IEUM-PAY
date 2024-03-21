import { useState } from 'react';
import classes from './Accordion.module.scss';
interface AccordionProps {
  price: number;
  transactionType: string;
  name: string;
  children?: React.ReactNode;
}
function Accordion({ price, transactionType, name, children }: AccordionProps) {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <div className={classes.accordion}>
      <button
        className={classes.button}
        onClick={() => setIsOpen((prev) => !prev)}
      >
        <div>
          <p className={classes.price}>
            {price}
            <span>(기부)</span>
          </p>
          <p>
            {transactionType} | {name}
          </p>
        </div>
        <div>아이콘</div>
      </button>
      {isOpen && <section>{children}</section>}
    </div>
  );
}
export default Accordion;
