import { Input } from '@nextui-org/react';
import { useState } from 'react';
import {
  EyeFilledIcon,
  EyeSlashFilledIcon,
} from '@/components/icons/PasswordIcon';
import useUserStore from '@/stores/user-store';
import { customlogin } from '@/api/userAxois';
import Button from '@/stories/Button';
import { useRouter } from 'next/router';

export default function Login() {
  const [userPassword, setUserPassword] = useState('');
  const [isPasswordValid, setIsPasswordValid] = useState(false);
  const [passwordVisible, setVisible] = useState(false);
  const { userInfo, setPassword } = useUserStore();
  const router = useRouter();

  const handlePasswordInput = (event: any) => {
    const newValue = event.target.value;
    setUserPassword(newValue);
    newValue.length == 0 ? setIsPasswordValid(true) : setIsPasswordValid(false);
  };

  const toggleVisibility = () => setVisible(!passwordVisible);

  console.log(userInfo.phoneNumber);

  const handleClick = async () => {
    setPassword(userPassword);

    (await customlogin(userInfo.phoneNumber, userPassword))
      ? router.push('/')
      : setIsPasswordValid(true);
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
        errorMessage={isPasswordValid && '올바른 비밀번호를 입력해주세요'}
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
        value={userInfo.phoneNumber}
      />
      <Button primary size="thin" onClick={handleClick}>
        확인
      </Button>
    </div>
  );
}
