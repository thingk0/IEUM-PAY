import Header from '@/components/Header';
import Button from '@/stories/Button';
import classes from './index.module.scss';
import { useRef } from 'react';
import CameraIcon from '@/stories/icons/CameraIcon';

function CardPage() {
  const inputRefs = [
    useRef<HTMLInputElement>(null),
    useRef<HTMLInputElement>(null),
    useRef<HTMLInputElement>(null),
    useRef<HTMLInputElement>(null),
  ];

  // 입력 값이 변경될 때 호출되는 함수
  const handleChange = (
    index: number,
    e: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const currentValue = e.target.value;
    if (currentValue.length === 0 && index > 0) {
      inputRefs[index - 1].current?.focus();
    } else if (currentValue.length >= 4 && index < inputRefs.length - 1) {
      inputRefs[index + 1].current?.focus(); // 다음 input 요소로 포커스 이동
    }
  };
  return (
    <>
      <Header>카드 등록</Header>
      <div>카드</div>
      <form>
        <label>카드번호*</label>
        <div className={classes['card-number']}>
          {inputRefs.map((ref, index) => (
            <>
              <input
                key={index}
                type="number"
                ref={ref}
                maxLength={8}
                onChange={(e) => handleChange(index, e)}
              />
              {index < 3 && <span>-</span>}
            </>
          ))}
        </div>
        <hr />
        <div className={classes.camera}>
          <CameraIcon />
        </div>
      </form>
      <p className={classes.subtext}>
        가상 서비스이므로 CVC와 비밀번호는 입력하지 않아도 됩니다.
      </p>
      <Button primary>등록</Button>
    </>
  );
}
export default CardPage;
