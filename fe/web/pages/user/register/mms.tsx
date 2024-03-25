import { useRouter } from 'next/router';
import useUserStore from '@/stores/user-store';
import Button from '@/stories/Button';

export default function Mms() {
  const router = useRouter();
  const { userInfo } = useUserStore();

  function handleClick() {
    const msgLink = `sms:b103ieumpay@gmail.com?body=${userInfo.randomKey}`;
    window.location.href = msgLink;
    router.push('/user/register/input-info');
  }

  return (
    <div>
      <h1>사용자 인증</h1>
      <p>문자 메시지를 통해 휴대폰 본인확인을</p>
      <p>진행합니다</p>
      <p>문자의 내용을 수정없이 보내주세요</p>
      <p>이미지 위치</p>
      <Button primary size="thick" onClick={handleClick}>
        확인
      </Button>
    </div>
  );
}
