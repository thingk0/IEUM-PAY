import { register, customlogin } from '@/api/userAxois';
import useUserStore from '@/stores/user-store';
import Button from '@/stories/Button';
import { useRouter } from 'next/router';

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
        ? router.push('/')
        : '';
    } else {
      router.push('/user');
    }
  };

  return (
    <div>
      <h1>진짜 끝남</h1>
      <Button primary onClick={startRegister}>
        슈웃
      </Button>
    </div>
  );
}
