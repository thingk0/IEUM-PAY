import ScanQR from '@/components/ScanQR';
import Button from '@/stories/Button';
import classes from '@/styles/ScanQRPage.module.scss';
function ScanQRPage() {
  return (
    <div className={classes.container}>
      <ScanQR />
      <div className={classes.wrapper}>
        <div className={classes.card}></div>
        <div className={classes.top}>
          <h1>QR코드 촬영</h1>
          <p className={classes.desc}>QR코드를 사각형 안에 맞춰주세요</p>
          <button>촬영하기</button>
        </div>
      </div>
    </div>
  );
}
export default ScanQRPage;
