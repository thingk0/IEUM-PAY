import React from 'react';
import { useRouter } from 'next/router';
import { Input, Button } from '@nextui-org/react';
import styles from '@/styles/user.module.css';

export default function User() {
  const [inputValue, setValue] = React.useState('');
  const router = useRouter();

  /**
   * 번호가 11자리가 넘어가면 자동으로 사이에 - 를 넣어주는 함수
   * @Bug 지우면 한방에 다 지워짐 마지막 else 때문이긴 한데 어떻게 해결할지 모름
   * @param event
   */
  const handleInputValue = (event: any) => {
    // console.log(event);
    const newValue = event.target.value;
    if (newValue.length <= 10) {
      setValue(newValue);
    } else if (newValue.length === 11) {
      setValue(`010-${newValue.substring(3, 7)}-${newValue.substring(7, 11)}`);
    } else {
      setValue('');
    }
  };

  /**
   * 기존에 이미 가입한 전화번호인지 확인하는 함수
   * @param e 전화번호
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
        label="휴대폰 번호"
        variant="underlined"
        value={inputValue}
        onChange={handleInputValue}
        className={styles.inputTag}
        classNames={{ label: 'labelTag' }}
      />
      <Button
        color="secondary"
        className={styles.btnTag}
        onClick={() => checkIsRegister(inputValue)}
      >
        확인
      </Button>
    </div>
  );
}
