import Image from 'next/image';
import classes from './FetchError.module.scss';
type FetchErrorProps = {
  onClick: (e?: React.MouseEvent<HTMLButtonElement, MouseEvent>) => unknown;
};
function FetchError({ onClick }: FetchErrorProps) {
  return (
    <main className={classes.container}>
      <Image
        src={'/fetchErrorIcon.svg'}
        alt="종이 위에 경고 아이콘"
        height={64}
        width={52}
      ></Image>
      <p className={classes.message}>정보를 불러올 수 없어요</p>
      <button onClick={onClick} className={classes.button}>
        다시 불러오기
      </button>
    </main>
  );
}
export default FetchError;
