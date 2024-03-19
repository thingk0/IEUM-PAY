import Button from '@/components/Button';

export default function Main() {
  const print = () => {
    console.log('a');
  };
  return (
    <>
      <Button text={'thickFill'} btnStyle={'thickFill'} btnFunction={print} />
      <Button text={'thickLine'} btnStyle={'thickLine'} btnFunction={print} />
      <Button text={'thinFill'} btnStyle={'thinFill'} btnFunction={print} />
      <Button text={'thinLine'} btnStyle={'thinLine'} btnFunction={print} />
    </>
  );
}
