import Button from '@/components/Button';
import { useRouter } from 'next/router';

export default function Mms() {

  const router = useRouter();

  function onClickFunc() {
    const msgLink = "sms:b103ieumpay@gmail.com?body=인증 메시지";
    window.location.href = msgLink;
  }

  const BtnElements = {
    text: "확인",
    btnStyle: "thickFill",
    btnFunction: onClickFunc,
  }

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
