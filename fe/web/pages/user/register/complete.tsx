import { register, customlogin } from '@/api/userAxois';
import useUserStore from '@/stores/user-store';
import Button from '@/stories/Button';
import { useRouter } from 'next/router';
import styles from '@/styles/user.module.scss';

export default function RegisterEnd() {
  const { userInfo } = useUserStore();
  const router = useRouter();

  const registerInfo = {
    phoneNumber: userInfo.phoneNumber,
    name: userInfo.userName,
    nickName: userInfo.userNickname,
    password: userInfo.userPassword,
    passwordConfirm: userInfo.userPassword,
    paymentPassword: userInfo.paymentPassword,
  };
  const startRegister = async () => {
    if (await register(registerInfo)) {
      (await customlogin(userInfo.phoneNumber, userInfo.userPassword))
        ? router.push('/main')
        : '';
    } else {
      router.push('/user');
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.complete}>
        <img src="/icon-192x192.png" alt="이음페이 로고" />
        <p>회원가입 완료</p>
        <strong>{userInfo.userNickname}님</strong>
        <strong>환영해요!</strong>
        <div className={styles.btnComp}>
          <Button primary onClick={startRegister}>
            시작하기
          </Button>
        </div>
      </div>
    </div>
  );
}
