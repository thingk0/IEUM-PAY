import { getFundDetail, setConnectState } from '@/api/fundAxois';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import styles from '@/styles/FundDetailPage.module.scss';
import { commaizeNumber } from '@toss/utils';
import {
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  useDisclosure,
} from '@nextui-org/modal';
import HalfButton from '@/components/HalfButton';
import HeaderHome from '@/components/HeaderHome';
import useDonateMoneyInfo from '@/hooks/useDirectDonationStore';
import useUserStore from '@/stores/user-store';
import Button from '@/stories/Button';
import { Progress } from '@nextui-org/react';
import { josa } from '@toss/hangul';
import { GetServerSideProps } from 'next/types';
import { useQueries, useQuery } from '@tanstack/react-query';
import { getBalance } from '@/api/paymentAxios';
import FetchError from '@/components/layouts/FetchError';

interface peopleType {
  nickname: string;
  memberId: number;
  gradeCode: string;
  fundingTotalAmount: number;
}

interface productType {
  productName: string;
  sponsorName: string;
  fundingProductQuantity: number;
  fundingProductPrice: number;
  productImage: string;
  price: number;
}

interface dataType {
  fundingId: number;
  facilityName: string;
  facilityAddress: string;
  facilityPhoneNumber: string;
  facilityRepresentativeName: string;
  facilityRepresentativePhoneNumber: string;
  facilityCapacity: number;
  facilityImage: string;
  fundingOpenDate: string;
  fundingPeopleCnt: number;
  fundingTitle: string;
  goalAmount: number;
  currentAmount: number;
  currentLink: boolean;
  people: peopleType[];
  // 일단 임의 지정
  content: string;
  products: productType[];
  fundingFinishDate: string | null;
}

export default function Detail({ fundId }: { fundId: string }) {
  const router = useRouter();
  const donateInfo = useDonateMoneyInfo();
  const { userInfo, setBalance } = useUserStore();
  const [donateState, setDonateState] = useState(false);

  const results = useQueries({
    queries: [
      {
        queryKey: ['balance-donate'],
        queryFn: getBalance,
      },
      {
        queryKey: ['donation-detail-info'],
        queryFn: () => getFundDetail(fundId),
      },
    ],
  });

  // results[0].isLoading ? '' : setBalance(results[0].data);
  // !results[1].isLoading ? console.log(results[1].data) : '';
  // !results[1].isLoading ? setDonateState(results[1].data.currentLink) : '';

  const productList = () => {
    if (!results[1].isLoading) {
      return (
        <div className={styles.fundingState}>
          <div className={styles.title}>제품 목록</div>
          <hr className={styles.hr} />
          <div className={styles.info}>
            <div className={styles.cardContainer}>
              {results[1].data.products.map((unit: productType) => (
                <>
                  <div className={styles.card}>
                    <div className={styles.imageBox}>
                      <img
                        className={styles.image}
                        src={unit.productImage}
                        alt="물품 이미지"
                      />
                    </div>
                    <div className={styles.productInfo}>
                      <div>{unit.sponsorName}</div>
                      <div className={styles.sponsorName}>
                        {unit.productName}
                      </div>
                      <div className={styles.unit}>
                        개당 {commaizeNumber(unit.price)}
                      </div>
                      <div className={styles.products}>
                        <div>{unit.fundingProductQuantity}개</div>
                        <div>{commaizeNumber(unit.fundingProductPrice)}원</div>
                      </div>
                    </div>
                  </div>
                  <hr className={styles.cardHr} />
                </>
              ))}
            </div>
          </div>
        </div>
      );
    }
  };
  const donatorList = () => {
    if (!results[1].isLoading) {
      return (
        <div className={styles.fundingState}>
          <div className={styles.title}>기부자 목록</div>
          <div>
            {results[1].data.people.map((unit: peopleType) => (
              <div className={styles.donatorContainer}>
                <div className={styles.donatorInfo}>
                  <img
                    className={styles.donatorBadge}
                    src={`/levelBadges/${unit.gradeCode}.svg`}
                    alt="도네이터 등급"
                  />
                  <div className={styles.donatorName}>{unit.nickname}</div>
                </div>
                <div className={styles.donateAmount}>
                  {commaizeNumber(unit.fundingTotalAmount)}원
                </div>
              </div>
            ))}
          </div>
        </div>
      );
    }
  };

  const setdirectDonateInfo = () => {
    donateInfo.setDonateMoneyInfo({
      기관아이디: results[1].data.fundingId,
      기관명: results[1].data.facilityName,
      남은금액: results[1].data.goalAmount - results[1].data.currentAmount,
      송금금액: 0,
      송금은행: '이음페이',
      잔액: results[0].data,
    });
    router.push('/fundraising/direct-donation');
  };

  const mamgeLinking = async () => {
    if (
      await setConnectState(
        results[1].data.currentLink,
        results[1].data.fundingId,
      )
    ) {
      setDonateState(!donateState);
      onOpen();
    }
  };

  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  const btnProps = {
    text: results[1].data?.currentLink ? '기부 연동하기' : '기부 그만하기',
    text2: '직접 후원하기',
    btnStyle: 'recThinFill',
    btnStyle2: 'recThinFill',
    btnFunction: mamgeLinking,
    btnFunction2: setdirectDonateInfo,
  };

  if (!results[1].isLoading) {
    return (
      <>
        <HeaderHome>모금 상세</HeaderHome>
        <div className={styles.detailContainer}>
          <div className={styles.imageTag}>
            <img
              src={results[1].data.facilityImage}
              className={styles.facilityImage}
              alt="기관 이미지"
            />
          </div>
          <div className={styles.fundingState}>
            <div className={styles.title}>{results[1].data.facilityName}</div>
            <div className={styles.info}>
              <div>
                마감까지{' '}
                {commaizeNumber(
                  results[1].data.goalAmount - results[1].data.currentAmount,
                )}
              </div>
              <div>
                {(
                  (results[1].data.currentAmount / results[1].data.goalAmount) *
                  100
                ).toFixed(2)}
                %
              </div>
            </div>
            <Progress
              size="sm"
              aria-label="기부 모금 정도 표기"
              classNames={{
                indicator: `${styles.progressBar}`,
              }}
              value={Math.floor(
                (results[1].data.currentAmount / results[1].data.goalAmount) *
                  100,
              )}
            />
          </div>
          {productList()}
          {donatorList()}
          <div className={styles.content}>
            <p>기관으로부터의 편지</p>
            {results[1].data.content}
          </div>
        </div>
        {results[1].data.fundingFinishDate == null ? (
          <div className={styles.footer}>{HalfButton(btnProps)}</div>
        ) : (
          <div></div>
        )}

        <Modal
          className={styles.modalComp}
          isOpen={isOpen}
          onOpenChange={onOpenChange}
        >
          <ModalContent>
            {(onClose) => (
              <>
                <ModalBody>
                  <div className={styles.modalContainer}>
                    {results[1].data.currentLink ? (
                      <>
                        <img
                          className={styles.modalImage}
                          src="/icon-256x256.png"
                          alt="이어졌다는 뜻의 이미지"
                        />
                        <div className={styles.modalMessage}>
                          {josa(results[1].data.facilityName, '와/과')}{' '}
                          이어졌어요!
                        </div>
                      </>
                    ) : (
                      <>
                        <img
                          className={styles.modalImage}
                          src="/disconnect-icon.svg"
                          alt="끊어졌다는 뜻의 로고 이미지"
                        />
                        <div className={styles.modalMessage}>
                          {josa(results[1].data.facilityName, '와/과')}의 연결을
                          끊었어요
                        </div>
                      </>
                    )}
                  </div>
                </ModalBody>
                <ModalFooter className={styles.modalFooter}>
                  <Button primary size="thick" onClick={onClose}>
                    확인
                  </Button>
                </ModalFooter>
              </>
            )}
          </ModalContent>
        </Modal>
      </>
    );
  } else if (results[1].isError) {
    return (
      <>
        <FetchError
          onClick={() => {
            results[1].refetch();
          }}
        />
      </>
    );
  }
}

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { fundId } = context.query;

  return {
    props: {
      fundId: fundId,
    },
  };
};
