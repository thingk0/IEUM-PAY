import PageTitleLeft from '@/components/PageTitleLeft';
import styles from './settings.module.scss';
import SettingsMenu from '@/components/settingsMenu';
import ChevronRightIcon from '@/components/icons/ChevronRightIcon';
import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';
import { eraseCookie } from '@/utils/cookie';
import { useRouter } from 'next/router';
import {
  Modal,
  ModalContent,
  ModalBody,
  ModalFooter,
  useDisclosure,
} from '@nextui-org/react';
import Button from '@/stories/Button';

export default function Settings() {
  const router = useRouter();
  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  return (
    <>
      <HeaderMain />
      <div className={styles.container}>
        <PageTitleLeft title={'설정'} description={''} />
        <div className={styles.myBox}>
          <div className={styles.myBoxText}>
            <p className={styles.name}>{'김범수'}</p>
            <p className={styles.subText}>내 정보 수정하기</p>
          </div>
          <ChevronRightIcon />
        </div>
        <div className={styles.menuBox}>
          <SettingsMenu>비밀번호 변경</SettingsMenu>
          <SettingsMenu
            onClick={() => {
              router.push('/settings/policy');
            }}
          >
            약관 및 정책
          </SettingsMenu>
          <SettingsMenu onClick={() => onOpen()}>로그아웃</SettingsMenu>
          <SettingsMenu>탈퇴하기</SettingsMenu>
        </div>
      </div>
      <TabBar selected={'none'} />
      <Modal
        className={styles.modalComp}
        isOpen={isOpen}
        onOpenChange={onOpenChange}
      >
        <ModalContent>
          {(onClose) => (
            <>
              <ModalBody className={styles.modalContainer}>
                <div>
                  <p>정말 로그아웃 할까요?</p>
                </div>
              </ModalBody>
              <ModalFooter className={styles.modalFooter}>
                <Button
                  size="thin"
                  onClick={() => {
                    onClose;
                  }}
                >
                  취소
                </Button>
                <Button
                  primary
                  size="thin"
                  onClick={() => {
                    eraseCookie('access_token');
                    router.push('/user');
                    onClose;
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
