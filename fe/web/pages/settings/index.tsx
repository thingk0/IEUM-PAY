import PageTitleLeft from '@/components/PageTitleLeft';
import styles from './settings.module.scss';
import SettingsMenu from '@/components/settingsMenu';
import ChevronRightIcon from '@/components/icons/ChevronRightIcon';
import HalfButton from '@/components/HalfButton';
import HeaderMain from '@/stories/HeaderMain';
import TabBar from '@/stories/TabBar';

export default function Settings() {
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
          <SettingsMenu title={'비밀번호 변경'} />
          <SettingsMenu title={'약관 및 정책'} />
          <SettingsMenu title={'로그아웃'} />
          <SettingsMenu title={'탈퇴하기'} />
        </div>
      </div>
      <TabBar selected={'none'} />
    </>
  );
}
