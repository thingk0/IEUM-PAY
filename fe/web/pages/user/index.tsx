import { useState, useMemo } from 'react';
import { useRouter } from 'next/router';
import { Input } from '@nextui-org/react';
import styles from '@/styles/user.module.css';
import useUserStore from '@/stores/user-store';
import Button from '@/stories/Button';
import { IsRegister } from '@/api/userAxois';

export default function User() {
  const { userInfo, setPhoneNumber } = useUserStore();
  const [inputValue, setValue] = useState('');
  const [pushLink, setPushLink] = useState('');
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
  async function checkIsRegister(phoneNumber: string) {
    setPhoneNumber(phoneNumber);
    const check = await IsRegister(phoneNumber);
    console.log(check.data);
    if (check != undefined) {
      check.data ? setPushLink('/login') : setPushLink('/register');
      router.push(`/user${pushLink}`);
    } else {
      console.log('time out or error');
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
        pattern="\d*"
        value={inputValue}
        isInvalid={isInvalid}
        errorMessage={isInvalid && '11자리 이내로 입력해주세요'}
        onChange={handleInputValue}
        className={styles.inputTag}
        classNames={{ label: 'labelTag' }}
      />
      <Button primary onClick={() => checkIsRegister(inputValue)}>
        시작하기
      </Button>
    </div>
  );
}
