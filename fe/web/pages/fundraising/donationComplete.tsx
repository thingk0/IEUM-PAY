import PageTitleCenter from '@/components/PageTitleCenter';
import styles from './donationComplete.module.scss';
import LineBox from '@/components/LineBox';
import Button from '@/stories/Button';
interface CompletePage {
  title: string;
  description: string;
  main: string;
  sub: string;
  badgeUrl: string;
  buttonText: string;
}
export default function CompletePage({
  title,
  description,
  main,
  sub,
  badgeUrl,
  buttonText,
}: CompletePage) {
  return (
    <div className={styles.container}>
      <PageTitleCenter title={title} description={description} />
      <div>
        <img src={badgeUrl} alt={'badge'} />
      </div>
      <LineBox main={main} sub={sub} />
      <Button size={'thin'}>{buttonText}</Button>
    </div>
  );
}
