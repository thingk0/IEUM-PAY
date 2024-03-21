import PasswordKeyPad from '@/components/PasswordKeyPad';
import classes from '@/styles/PasswordPage.module.scss';
import { useState } from 'react';

function PasswordPage() {
  const [password, setPassword] = useState<number[]>([]);
  return (
    <main className={classes.main}>
      <div>
        <h1 className={classes.title}>결제 비밀번호 생성</h1>
        <h2 className={classes.subTitle}>
          결제/송금 시 이용할 비밀번호를 입력해주세요
        </h2>
      </div>

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
