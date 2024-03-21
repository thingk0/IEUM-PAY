import useUserStore from '@/stores/user-store';
import { Input } from '@nextui-org/react';
import { useState } from 'react';
import Button from '@/components/Button';
import {
  EyeFilledIcon,
  EyeSlashFilledIcon,
} from '@/components/icons/PasswordIcon';
import styes from '@/styles/input-info.module.css';
import { useRouter } from 'next/router';

export default function inputInfo() {
  const [userPassword, setPasswordValue] = useState('');
  const [userNickName, setNickNameValue] = useState('');
  const [userName, setNameValue] = useState('');
  const [isVisible, setIsVisible] = useState(false);
  const [isInValid, setIsInValid] = useState(false);
  const [isValid, setIsValid] = useState(false);
  const [nicknameVisible, setNicknameVisible] = useState(false);
  const [nicknameIsValid, setNicknameIsValid] = useState(false);
  const [nameVisible, setNameVisible] = useState(false);
  const [nameIsValid, setNameIsValid] = useState(false);
  const [cnt, setCnt] = useState(0);
  const { userInfo, setPassword, setUserName, setUserNickname } =
    useUserStore();
  const router = useRouter();

  const toggleVisibility = () => setIsVisible(!isVisible);

  const handlNameValue = (evnet: any) => {
    const newValue = evnet.target.value;
    setNameValue(newValue);

    // 한글만 허용
    const idRegExp = /^(?=.*[ㄱ-ㅎ|ㅏ-ㅣ|가-힣])[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,50}$/;
    if (newValue.length > 1 && !idRegExp.test(newValue)) {
      setNameIsValid(true);
    } else {
      setNameIsValid(false);
    }
  };

  const handleNicknameValue = (event: any) => {
    const newValue = event.target.value;
    setNickNameValue(newValue);
    // 특수문자 허용 안함
    const idRegExp = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/;
    if (idRegExp.test(newValue)) {
      setNicknameIsValid(true);
    } else {
      setNicknameIsValid(false);
    }
  };

  const handlePasswordInput = (event: any) => {
    // console.log(event);
    const newValue = event.target.value;
    // 영어소문자, 숫자, 특수문자 포함 여부 확인 8 자 이상 20자 이하
    const idRegExp =
      /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*#?&])[a-zA-Z\d@$!%*#?&]{8,20}$/;

    setPasswordValue(newValue);

    if (!idRegExp.test(newValue)) {
      setIsInValid(true);
      setIsValid(true);
    } else {
      setIsInValid(false);
    }
  };

  const onClickFunction = () => {
    if (cnt == 0 && isValid) {
      setCnt(1);
      setNicknameVisible(true);
    } else if (cnt == 1) {
      setCnt(2);
      setNameVisible(true);
    } else {
      setPassword(userPassword);
      setUserName(userName);
      setUserNickname(userNickName);
      router.push('/user/register/');
    }
  };

  const btnElements = {
    text: cnt == 2 ? '확인' : '다음',
    btnStyle: 'thinFill',
    btnFunction: onClickFunction,
  };

  return (
    <div>
      <h1>회원가입</h1>
      <p>로그인 비밀번호를 입력해주세요</p>
      <div className={nameVisible ? '' : styes.isVisible}>
        <Input
          label="이름"
          variant="underlined"
          onChange={handlNameValue}
          value={userName}
          isInvalid={nameIsValid}
          errorMessage={nameIsValid && '한글로 작성해 주세요'}
        ></Input>
      </div>
      <div className={nicknameVisible ? '' : styes.isVisible}>
        <Input
          label="닉네임"
          variant="underlined"
          onChange={handleNicknameValue}
          value={userNickName}
          isInvalid={nicknameIsValid}
          errorMessage={nicknameIsValid && '특수문자 넣으시면 안돼요'}
        ></Input>
      </div>
      <Input
        label="비밀번호"
        variant="underlined"
        value={userPassword}
        onChange={handlePasswordInput}
        isInvalid={isInValid}
        description={isValid && '사용 가능 합니다'}
        errorMessage={
          isInValid &&
          '8~20자의 영문, 숫자, 특수문자를 모두 포함한 비밀번호를 입력해주세요'
        }
        endContent={
          <button
            className="focus:outline-none"
            type="button"
            onClick={toggleVisibility}
          >
            {isVisible ? (
              <EyeFilledIcon className="text-2xl text-default-400 pointer-events-none" />
            ) : (
              <EyeSlashFilledIcon className="text-2xl text-default-400 pointer-events-none" />
            )}
          </button>
        }
        type={isVisible ? 'text' : 'password'}
      />
      <Input
        isDisabled
        type="text"
        label="휴대폰 번호"
        variant="underlined"
        value={userInfo.phoneNumber}
      />
      {Button(btnElements)}
    </div>
  );
}
