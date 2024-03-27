import ScanCard from '@/components/ScanCard';
import Button from '@/stories/Button';
import classes from '@/styles/card.module.scss';
function ScanCardPage() {
  return (
    <div className={classes.container}>
      <ScanCard />
      <div className={classes.wrapper}>
        <div className={classes.card}></div>
        <div className={classes.top}>
          <h1>카드 스캔</h1>
          <p className={classes.desc}>카드를 사각형 안에 맞춰주세요</p>
          <button>카드번호 입력</button>
          <Button>촬영하기</Button>
        </div>
      </div>
    </div>
  );
}
export default ScanCardPage;
