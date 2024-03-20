import Button from '@/components/Button';
import { useRouter } from 'next/router';
import { useUserStore } from '@/stores/user-store';

export default function Mms() {
  const router = useRouter();
  const { randomKey: randomKey } = useUserStore();

  function onClickFunc() {
    const msgLink = `sms:b103ieumpay@gmail.com?body=${randomKey}`;
    window.location.href = msgLink;
    router.push('/user/register/input-info');
  }

  const BtnElements = {
    text: '확인',
    btnStyle: 'thickFill',
    btnFunction: onClickFunc,
  };

  return (
    <div>
      <h1>사용자 인증</h1>
      <p>문자 메시지를 통해 휴대폰 본인확인을</p>
      <p>진행합니다</p>
      <p>문자의 내용을 수정없이 보내주세요</p>
      <p>이미지 위치</p>
      {Button(BtnElements)}
    </div>
  );
}
