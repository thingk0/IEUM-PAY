import { requestRandomKey } from '@/api/userAxois';
import useUserStore from '@/stores/user-store';
import Button from '@/stories/Button';
import { useRouter } from 'next/router';

export default function register() {
  const { userInfo, setRandomKey } = useUserStore();
  const router = useRouter();

  async function startRegister() {
    console.log(userInfo.phoneNumber);
    const res = await requestRandomKey(userInfo.phoneNumber);
    if (res.data != undefined) {
      setRandomKey(res.data);
      router.push('register/mms');
    }
  }

  return (
    <div>
      <h1>회원가입</h1>
      <p>등록된 회원이 아니네</p>
      <p>회원가입을 진행하시겠습니까?</p>
      <Button primary size="thick" onClick={startRegister}>
        시작하기
      </Button>
    </div>
  );
}
