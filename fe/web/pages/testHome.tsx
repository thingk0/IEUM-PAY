import { useState } from 'react';
import Html5QrcodePlugin from './test';
import Html5QrcodeError from './test';
import { useRouter } from 'next/router';

interface AppProps {
  // props의 타입을 정의해줍니다.
}

const asd: React.FC<AppProps> = (props) => {
  const onNewScanResult = (decodedText: any, decodedResult: any) => {
    console.log(decodedText);
    console.log(decodedResult);
    setQrResult(decodedText);
  };

  const [qrResult, setQrResult] = useState('');

  const router = useRouter();
  console.log(qrResult);
  if (qrResult) {
    router.push('/');
  }
  return (
    <div className="App">
      <Html5QrcodePlugin
        fps={10}
        qrbox={{ width: 250, height: 250 }} // qrbox는 너비와 높이를 객체로 전달해야 합니다.
        disableFlip={false}
        qrCodeSuccessCallback={onNewScanResult}
        qrCodeErrorCallback={(error: typeof Html5QrcodeError) =>
          console.error(error)
        } // 오류 콜백도 추가해줍니다.
      />
    </div>
  );
};

export default asd;
