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
import PageTitleLeft from '@/components/PageTitleLeft';
import styles from '@/styles/loginPage.module.scss';
import { initializeApp } from 'firebase/app';
import { getMessaging, getToken, onMessage } from 'firebase/messaging';
import { postFCMToken } from '@/api/notificationAxios';

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

    if (await customlogin(userInfo.phoneNumber, userPassword)) {
      await onMessageFCM();
      router.push('/main');
    } else {
      setIsPasswordValid(true);
    }
  };

  const onMessageFCM = async () => {
    // 이곳에도 아까 위에서 앱 등록할때 받은 'firebaseConfig' 값을 넣어주세요.
    const firebaseApp = initializeApp({
      apiKey: 'AIzaSyAnHEyqUnFoxudUMWnFvntVMDKZTymOLxw',
      authDomain: 'webpush-test-f3072.firebaseapp.com',
      projectId: 'webpush-test-f3072',
      storageBucket: 'webpush-test-f3072.appspot.com',
      messagingSenderId: '5581773458',
      appId: '1:5581773458:web:a0a8db6b8d8a7311d4644b',
    });

    const messaging = getMessaging(firebaseApp);

    // 이곳 vapidKey 값으로 아까 토큰에서 사용한다고 했던 인증서 키 값을 넣어주세요.
    getToken(messaging, {
      vapidKey:
        'BM8z7PYWYKIPxbpVckZ-rUqgRNoWV36rgdSLhqefvAHnNjeW_ZRBC87SLDdwz9DcX1w6xtGPR233v3BzgxYkNnQ',
    })
      .then((currentToken) => {
        if (currentToken) {
          // 정상적으로 토큰이 발급되면 콘솔에 출력합니다.
          console.log(currentToken);
          localStorage.setItem('fcm_token', currentToken);
          postFCMToken(currentToken);
        } else {
          console.log(
            'No registration token available. Request permission to generate one.',
          );
        }
      })
      .catch((err) => {
        console.log('An error occurred while retrieving token. ', err);
      });

    // 메세지가 수신되면 역시 콘솔에 출력합니다.
    onMessage(messaging, (payload) => {
      console.log('Message received. ', payload);
    });
  };

  return (
    <div className={styles.container}>
      <PageTitleLeft
        title="로그인"
        description={'로그인 비밀번호를 입력해주세요'}
      />
      <Input
        label="비밀번호"
        variant="underlined"
        value={userPassword}
        onChange={handlePasswordInput}
        isInvalid={isPasswordValid}
        classNames={{
          label: styles.label,
          inputWrapper: styles.inputWrapper,
        }}
        autoFocus
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
        type="tel"
        label="휴대폰 번호"
        variant="underlined"
        value={userInfo.phoneNumber}
        classNames={{
          label: styles.label,
          inputWrapper: styles.inputWrapper,
        }}
      />
      <Button primary size="thin" onClick={handleClick}>
        확인
      </Button>
    </div>
  );
}
