import { requestRandomKey } from '@/api/userAxois';
import Button from '@/stories/Button';

export default function register() {
  function startRegister() {
    console.log('test');
    requestRandomKey();
  }

  return (
    <div>
      <h1>회원가입</h1>
      <p>등록된 회원이 아니네</p>
      <p>회원가입을 진행하시겠습니까?</p>
      <Button primary size='thick' onClick={startRegister}>
        시작하기
      </Button>
    </div>
  );
}
