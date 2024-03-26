import { useEffect } from 'react';

export default function CompleteDonation() {
  useEffect(() => {
    history.pushState(null, '', location.href);
    window.addEventListener('popstate', () => {
      browserPreventEvent();
    });
    return () => {
      window.removeEventListener('popstate', () => {
        browserPreventEvent();
      });
    };
  }, []);

  return (
    <>
      <h1>기부 완료</h1>
      <div>
        <div>아이콘</div>
      </div>
      <div>확인버튼</div>
    </>
  );
}

/**
 * 뒤로가기 막는거
 */
export const browserPreventEvent = () => {
  history.pushState(null, '', location.href);
};
