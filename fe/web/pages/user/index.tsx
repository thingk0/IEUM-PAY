import { useState, useMemo } from 'react';
import { useRouter } from 'next/router';
import { Input, Button } from '@nextui-org/react';
import styles from '@/styles/user.module.css';
import { useStore } from 'zustand';

export default function User() {
  const userStore = useStore;
  const [inputValue, setValue] = useState('');
  const router = useRouter();

  const isInvalid = useMemo(() => {
    if (inputValue == '') return false;
    return inputValue.length === 11 ? false : true;
  }, [inputValue]);

  /**
   * 입력된 항목이랑 input feild 값 동기화 하는 함수
   * @param event
   */
  const handleInputValue = (event: any) => {
    // console.log(event);
    const newValue = event.target.value;
    setValue(newValue);
  };

  /**
   * 기존에 이미 가입한 회원 여부 판단 후 로그인으로 보낼지 회원가입으로 보낼지 판단하는 함수
   * @param phoneNumber 전화번호
   */
  function checkIsRegister(phoneNumber: string) {
    // 대충 요청하는 코드
    const url = '/api/member/exist';
    if (phoneNumber.length > 10) {
      const numberData = `010-${phoneNumber.substring(3, 7)}-${phoneNumber.substring(7, 11)}`;
      console.log(numberData);
      // router.push('/');
    } else {
      // router.push('/');
    }
  }

  return (
    <div className={styles.container}>
      <h1 className={styles.head}>시작하기</h1>
      <Input
        isRequired
        type="number"
        label="휴대폰 번호"
        variant="underlined"
        value={inputValue}
        isInvalid={isInvalid}
        errorMessage={isInvalid && '올바른 전화번호를 입력해주세요'}
        onChange={handleInputValue}
        className={styles.inputTag}
        classNames={{ label: 'labelTag' }}
      />
      <Button
        color="secondary"
        className={styles.btnTag}
        onClick={() => checkIsRegister(inputValue)}
      >
        시작하기
      </Button>
    </div>
  );
}
