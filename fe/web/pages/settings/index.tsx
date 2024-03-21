import PageTitleLeft from '@/components/PageTitleLeft';
import styles from './settings.module.scss';
import SettingsMenu from '@/components/settingsMenu';

export default function Settings() {
  return (
    <div className={styles.container}>
      <PageTitleLeft
        title={'설정'}
        description={'설정은 설명이 없지만 테스트용으로 써봤습니다잉.'}
      />
      <div className={styles.myBox}></div>
      <div className={styles.menuBox}>
        <SettingsMenu title={'비밀번호 변경'} />
        <SettingsMenu title={'약관 및 정책'} />
        <SettingsMenu title={'로그아웃'} />
        <SettingsMenu title={'탈퇴하기'} />
      </div>
    </div>
  );
}
