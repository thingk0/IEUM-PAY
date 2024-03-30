export default function Vibrate(arg: number | number[]) {
  if (navigator.vibrate) {
    navigator.vibrate(arg);
  }
}
