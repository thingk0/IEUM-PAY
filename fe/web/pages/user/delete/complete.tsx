import Button from '@/stories/Button';
import { useRouter } from 'next/router';

function ByePage() {
  const router = useRouter();
  return (
    <main>
      <h1>나중에 또 만나요!</h1>
      <p>지금까지 이음과 함께해주셔서 감사해요.</p>
      <p>더 나은 이음이 될 수 있도록 노력할게요.</p>
      <Button primary onClick={() => router.push('/user')}>
        확인
      </Button>
    </main>
  );
}
export default ByePage;
