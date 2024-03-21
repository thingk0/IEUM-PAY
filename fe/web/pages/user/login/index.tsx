import { Input } from '@nextui-org/react';
import { useMemo, useState } from 'react';
import {
  EyeFilledIcon,
  EyeSlashFilledIcon,
} from '@/components/icons/PasswordIcon';
import { useUserStore } from '@/stores/user-store';
import Button from '@/components/Button';

export default function login() {
  const [userPassword, setUserPassword] = useState('');
  const [isPasswordValid, setIsPasswordValid] = useState(false);
  const [passwordVisible, setVisible] = useState(false);
  const {
    setPassword: setPassword,
    phoneNumber: phoneNumber,
    login: login,
  } = useUserStore();

  const handlePasswordInput = (event: any) => {
    const newValue = event.target.value;
    setUserPassword(newValue);
    const idRegExp =
      /^(?=.*[a-z])(?=.*\d)(?=.*[@$!%*#?&])[a-z\d@$!%*#?&]{8,20}$/;

    !idRegExp.test(newValue)
      ? setIsPasswordValid(true)
      : setIsPasswordValid(false);
  };

  const toggleVisibility = () => setVisible(!passwordVisible);

  const onclickFunc = () => {
    console.log('Test');
    setPassword(userPassword);
    login();
  };

  return (
    <div>
      <h1>로그인</h1>
      <p>비밀번호를 입력해주세요</p>
      <Input
        label="비밀번호"
        variant="underlined"
        value={userPassword}
        onChange={handlePasswordInput}
        isInvalid={isPasswordValid}
        errorMessage={
          isPasswordValid &&
          '8~20자의 영문, 숫자, 특수문자를 모두 포함한 비밀번호를 입력해주세요'
        }
        endContent={
          <button
            className="focus:outline-none"
            type="button"
            onClick={toggleVisibility}
          >
            {passwordVisible ? (
              <EyeFilledIcon className="text-2xl text-default-400 pointer-events-none" />
            ) : (
              <EyeSlashFilledIcon className="text-2xl text-default-400 pointer-events-none" />
            )}
          </button>
        }
        type={passwordVisible ? 'text' : 'password'}
      />
      <Input
        isDisabled
        type="number"
        label="휴대폰 번호"
        variant="underlined"
        value={phoneNumber}
      />
      {Button({ text: '확인', btnStyle: 'thinFill', btnFunction: onclickFunc })}
    </div>
  );
}
