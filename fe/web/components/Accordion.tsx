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
  let canOpen = history.detail.length > 1;
  function handleClick() {
    if (canOpen) setIsOpen((prev) => !prev);
  }

  function hasDonation() {
    let has = false;
    if (history.type === '기부') return true;
    else if (
      history.detail.filter((e) => e.type === '기부').length > 0 &&
      !isOpen
    )
      return true;
  }

  return (
    <div className={`${classes.accordion} ${isOpen && classes.open}`}>
      <button className={classes.button} onClick={handleClick}>
        <div>
          <p className={classes.title}>
            <span className={classes.price}>
              {isOpen && history.type === '결제'
                ? history.detail
                    .filter((e) => e.type === '결제')
                    .map((e) => <>{commaizeNumber(e.price)}</>)
                : commaizeNumber(history.amount)}
              원
            </span>
            {hasDonation() && (
              <span className={classes.donation}>
                <DonationIcon />
              </span>
            )}
          </p>
          <p className={classes.detail}>
            {history.type} | {history.title}
          </p>
        </div>
        {canOpen && (
          <div className={`${classes.chevron} ${isOpen ? classes.open : ''}`}>
            <ChevronDownIcon />
          </div>
        )}
      </button>
      {isOpen && (
        <section>
          {history.type !== '기부' &&
            history.detail
              .filter((e) => e.type === '기부')
              .map((e) => (
                <>
                  <div>
                    {commaizeNumber(e.price)}원
                    <span className={classes.donation}>
                      <DonationIcon />
                    </span>
                  </div>
                  <div>기부 | {e.name}</div>
                </>
              ))}
          {history.detail
            .filter((e) => e.type === '충전')
            .map((e) => (
              <>
                <hr />
                <div key={history.historyId + e.type}>
                  +{commaizeNumber(e.price)}원 이음페이머니 충전
                </div>
              </>
            ))}
        </section>
      )}
    </div>
  );
}
export default Accordion;
