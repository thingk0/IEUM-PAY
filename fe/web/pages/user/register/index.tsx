import Button from '@/components/Button';
import { useRouter } from 'next/router';

export default function register() {
  const router = useRouter();
  
  function startRegister() {
    console.log('test');
    router.push('/user/register/mms');
  }
  
  const btnElements = {
    text: "시작하기",
    btnStyle: "thickFill",
    btnFunction: startRegister,
  }

  return (
    <div>
      <h1>회원가입</h1>
      <p>등록된 회원이 아니네</p>
      <p>회원가입을 진행하시겠습니까?</p>
      {Button(btnElements)}
    </div>
  );
}
