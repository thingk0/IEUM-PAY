import Button from '@/stories/Button';

export default function MemberDeletePage() {
  return (
    <>
      <h1>김싸피님, 탈퇴하기 전에 확인해주세요</h1>
      <ul>
        <li>이음에서 관리했던 김싸피님의 모든 개인정보를 다시 볼 수 없어요.</li>
        <li>다양한 혜택과 이벤트 기회, 상품권 내역, 할인권이 모두 사라져요.</li>
        <li>
          포인트를 다른 계좌로 옮겨주세요.100원보다 적은 포인트는 사라져요.
        </li>
      </ul>
      <p>0000 원이 잔고에 남아있습니다</p>
      <Button primary>탈퇴하기</Button>
    </>
  );
}
