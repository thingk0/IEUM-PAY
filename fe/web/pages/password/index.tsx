import PageTitleCenter from '@/components/PageTitleCenter';
import PasswordKeyPad from '@/components/PasswordKeyPad';
import classes from '@/styles/PasswordPage.module.scss';
import { useRouter } from 'next/router';
import { useState } from 'react';

// interface PasswordPage {
//   title: string;
//   description: string;
//   queryObj: Object;
// }

function PasswordPage() {
  // const [message, setMessage] = useState('');
  const [password, setPassword] = useState<number[]>([]);

  const pageId = [
    ['결제 비밀번호 입력', ''],
    ['결제 비밀번호 생성', '결제/송금 시 이용할 비밀번호를 입력해주세요'],
  ];
  const router = useRouter();
  const id = router.query.id as string;
  return (
    <main className={classes.main}>
      {/* <PageTitleCenter
        title={pageId[parseInt(id)][0]}
        description={pageId[parseInt(id)][1]}
      /> */}
      <ul className={classes.wrapper}>
        {Array.from({ length: 6 }).map((v, i) => (
          <li>
            <div
              className={`${classes.circle} ${i < password.length ? classes.active : ''}`}
            ></div>
          </li>
        ))}
      </ul>
      <PasswordKeyPad
        onClickNumber={(n) => setPassword((prev) => [...prev, n].slice(0, 6))}
        onClickDelete={() => setPassword((prev) => prev.slice(0, -1))}
      />
    </main>
  );
}
export default PasswordPage;
