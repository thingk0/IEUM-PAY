import PageTitleCenter from '@/components/PageTitleCenter';
import PasswordKeyPad from '@/components/PasswordKeyPad';
import useUserStore from '@/stores/user-store';
import classes from '@/styles/PasswordPage.module.scss';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import { GetServerSideProps } from 'next';
import { confirmPassword } from '@/api/paymentAxios';
import useDonateMoneyInfo from '@/hooks/useDirectDonationStore';
import { directDonate } from '@/api/fundAxois';

// interface PasswordPage {
//   title: string;
//   description: string;
//   queryObj: Object;
// }

function PasswordPage({ id, pushUrl }: { id: string; pushUrl?: string }) {
  // const [message, setMessage] = useState('');
  const [password, setPassword] = useState<number[]>([]);
  const { userInfo, setPaymentPassword } = useUserStore();
  const [isTrue, setIsTrue] = useState(true);
  const { donateMoneyInfo, setFundingId } = useDonateMoneyInfo();
  const [hashKey, setKey] = useState('');

  const pageId = [
    ['결제 비밀번호 입력', ''],
    ['결제 비밀번호 생성', '결제/송금 시 이용할 비밀번호를 입력해주세요'],
    ['결제 비밀번호 확인', '결제 비밀번호를 한번 더 입력해주세요'],
  ];
  const router = useRouter();

  const payLogic = async (key: string) => {
    if (pushUrl != undefined) {
      // 여기서 분기점
      if (pushUrl == 'fundraising-complete') {
        const fundingId = await directDonate(
          donateMoneyInfo.기관아이디,
          donateMoneyInfo.송금금액,
          donateMoneyInfo.남은금액,
          key,
        );
        if (fundingId != 0) {
          console.log(fundingId.historyId);
          setFundingId(fundingId.historyId);
          router.push('/fundraising/complete');
        } else {
          console.log('실패ㅐㅐㅐㅐㅐ');
        }
      }
    }
  };

  useEffect(() => {
    const checkPassword = async () => {
      if (password.length == 6) {
        if (id == `1`) {
          setPaymentPassword(password.join(''));
          setPassword([]);
          router.push({
            pathname: '/password',
            query: {
              id: 2,
            },
          });
        } else if (id == `2`) {
          userInfo.paymentPassword == password.join('')
            ? router.push('user/register/complete')
            : (setIsTrue(false), setPassword([]));
        } else if (id == '0') {
          const check = await confirmPassword(password.join(''));
          const key = check.data.authenticationKey;
          check.data.auth ? payLogic(key) : (setIsTrue(false), setPassword([]));
        }
      }
    };
    checkPassword();
  }, [password]);

  return (
    <main className={classes.main}>
      <PageTitleCenter
        title={pageId[parseInt(id)][0]}
        description={pageId[parseInt(id)][1]}
      />
      <ul className={classes.wrapper}>
        {Array.from({ length: 6 }).map((v, i) => (
          <li>
            <div
              className={`${classes.circle} ${i < password.length ? classes.active : ''}`}
            ></div>
          </li>
        ))}
      </ul>
      {isTrue ? <div></div> : <div>다시 입력해 주세요</div>}
      <PasswordKeyPad
        onClickNumber={(n) => setPassword((prev) => [...prev, n].slice(0, 6))}
        onClickDelete={() => setPassword((prev) => prev.slice(0, -1))}
      />
    </main>
  );
}
export default PasswordPage;
export const getServerSideProps: GetServerSideProps = async ({ query }) => {
  // query 객체 사용
  return {
    props: {
      id: query.id,
      pushUrl: query.pushUrl != undefined ? query.pushUrl : '',
    },
  };
};
