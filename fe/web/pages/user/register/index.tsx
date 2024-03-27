import { requestRandomKey } from '@/api/userAxois';
import useUserStore from '@/stores/user-store';
import Button from '@/stories/Button';

export default function register() {
  const { userInfo } = useUserStore();
  function startRegister() {
    console.log('test');
    requestRandomKey(userInfo.phoneNumber);
    console.log(userInfo.randomKey);
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
