import { requestRandomKey } from '@/api/userAxois';
import Button from '@/components/Button';

export default function register() {
  function startRegister() {
    console.log('test');
    requestRandomKey();
  }

  const btnElements = {
    text: '시작하기',
    btnStyle: 'thickFill',
    btnFunction: startRegister,
  };

  return (
    <div>
      <h1>회원가입</h1>
      <p>등록된 회원이 아니네</p>
      <p>회원가입을 진행하시겠습니까?</p>
      {Button(btnElements)}
    </div>
  );
}
