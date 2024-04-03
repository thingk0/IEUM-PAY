import PageTitleCenter from '@/components/PageTitleCenter';
import PasswordKeyPad from '@/components/PasswordKeyPad';
import useUserStore from '@/stores/user-store';
import classes from '@/styles/PasswordPage.module.scss';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import { GetServerSideProps } from 'next';
import { confirmPassword } from '@/api/paymentAxios';
import { directDonate } from '@/api/fundAxois';
import { payment } from '@/api/paymentAxios';
import { sendPayMoney } from '@/api/sendMoneyAxios';
import useDonateMoneyInfo from '@/hooks/useDirectDonationStore';
import usePaymentInfo from '@/hooks/usePayStore';
import useSendMoneyInfo from '@/hooks/useSendMoneyStore';
import {
  Modal,
  ModalContent,
  ModalBody,
  ModalFooter,
  useDisclosure,
} from '@nextui-org/react';
import Button from '@/stories/Button';

// interface PasswordPage {
//   title: string;
//   description: string;
//   queryObj: Object;
// }

function PasswordPage({ id, pushUrl }: { id: string; pushUrl?: string }) {
  // const [message, setMessage] = useState('');
  const [password, setPassword] = useState<number[]>([]);
  const [isTrue, setIsTrue] = useState(true);
  const { userInfo, setPaymentPassword } = useUserStore();
  const { donateMoneyInfo, setFundingId } = useDonateMoneyInfo();
  const { paymentInfo } = usePaymentInfo();
  const { sendMoneyInfo } = useSendMoneyInfo();
  const [cnt, setCnt] = useState(1);
  const { isOpen, onOpen, onOpenChange } = useDisclosure();

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
        }
      } else if (pushUrl == 'payment') {
        const paymentRes = await payment(paymentInfo, key);
        if (paymentRes != undefined) {
          console.log(paymentRes);
          console.log(paymentRes.data.historyId);
          router.push({
            pathname: '/payment/PaymentComplete',
            query: {
              historyId: paymentRes.data.historyId,
            },
          });
        }
      } else if (pushUrl === 'send-money') {
        const phoneNumber = sendMoneyInfo.수취계좌;
        const amount = sendMoneyInfo.송금금액;
        try {
          const sendMoneyRes = await sendPayMoney(phoneNumber, amount, key);
          router.push('/send-money/success');
        } catch (e) {}
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
          console.log(check);
          if (check.status == 'SUCCESS') {
            const key = check.data.authenticationKey;
            payLogic(key);
          } else {
            setCnt(cnt + 1);
            setIsTrue(false);
            setPassword([]);
            if (cnt == 5) {
              onOpen();
            }
          }
        }
      }
    };
    checkPassword();
  }, [password]);

  return (
    <>
      <main className={classes.main}>
        <PageTitleCenter
          title={pageId[parseInt(id)][0]}
          description={pageId[parseInt(id)][1]}
        />
        <div className={classes.container}>
          <ul className={classes.wrapper}>
            {Array.from({ length: 6 }).map((v, i) => (
              <li>
                <div
                  className={`${classes.circle} ${i < password.length ? classes.active : ''}`}
                ></div>
              </li>
            ))}
          </ul>
          {isTrue ? <div></div> : <div>다시 입력해 주세요 {cnt} / 5</div>}
        </div>
        <PasswordKeyPad
          onClickNumber={(n) => setPassword((prev) => [...prev, n].slice(0, 6))}
          onClickDelete={() => setPassword((prev) => prev.slice(0, -1))}
        />
      </main>
      <Modal
        className={classes.modalComp}
        isOpen={isOpen}
        onOpenChange={onOpenChange}
      >
        <ModalContent>
          {(onClose) => (
            <>
              <ModalBody className={classes.modalContainer}>
                <div>
                  <p>결제에 실패했습니다</p>
                </div>
              </ModalBody>
              <ModalFooter className={classes.modalFooter}>
                <Button
                  primary
                  size="thin"
                  onClick={() => {
                    router.push('/main');
                    onClose();
                  }}
                >
                  확인
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
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
