import { useRouter } from 'next/router';

export default function CloseIcon() {
  const router = useRouter();
  const goBack = () => {
    router.back();
  };
  return (
    <div onClick={goBack}>
      <svg
        width="30"
        height="30"
        viewBox="0 0 30 30"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M7.64502 6.97998L22.27 21.6049"
          stroke="#191E28"
          strokeWidth="2"
          strokeLinecap="round"
        />
        <path
          d="M7.3125 21.9374L21.9374 7.31243"
          stroke="#191E28"
          strokeWidth="2"
          strokeLinecap="round"
        />
      </svg>
    </div>
  );
}
