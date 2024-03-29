import { useEffect, useRef, useState } from 'react';

// Styles
import styles from './qrReader.module.scss';

// Qr Scanner
import QrScanner from 'qr-scanner';
import { useRouter } from 'next/router';

export default function QrReader() {
  // QR States
  const scanner = useRef<QrScanner>();
  const videoEl = useRef<HTMLVideoElement>(null);
  const qrBoxEl = useRef<HTMLDivElement>(null);
  const [qrOn, setQrOn] = useState<boolean>(true);

  // Result
  const [scannedResult, setScannedResult] = useState<string | undefined>('');

  const router = useRouter();

  const goBack = () => {
    router.back();
  };
  if (scannedResult) {
    router.push('/payment');
  }
  // Success
  const onScanSuccess = (result: QrScanner.ScanResult) => {
    // 🖨 Print the "result" to browser console.
    console.log('스캔 결과 :', result);
    // ✅ Handle success.
    // 😎 You can do whatever you want with the scanned result.
    setScannedResult(result?.data);
  };

  // Fail
  const onScanFail = (err: string | Error) => {
    // 🖨 Print the "err" to browser console.
    console.log(err);
  };

  useEffect(() => {
    if (videoEl?.current && !scanner.current) {
      // 👉 Instantiate the QR Scanner
      scanner.current = new QrScanner(videoEl?.current, onScanSuccess, {
        onDecodeError: onScanFail,
        // 📷 This is the camera facing mode. In mobile devices, "environment" means back camera and "user" means front camera.
        preferredCamera: 'environment',
        // 🖼 This will help us position our "QrFrame.svg" so that user can only scan when qr code is put in between our QrFrame.svg.
        highlightScanRegion: true,
        // 🔥 This will produce a yellow (default color) outline around the qr code that we scan, showing a proof that our qr-scanner is scanning that qr code.
        highlightCodeOutline: true,
        // 📦 A custom div which will pair with "highlightScanRegion" option above 👆. This gives us full control over our scan region.
        overlay: qrBoxEl?.current || undefined,
      });

      // 🚀 Start QR Scanner
      scanner?.current
        ?.start()
        .then(() => setQrOn(true))
        .catch((err) => {
          if (err) setQrOn(false);
        });
    }

    // 🧹 Clean up on unmount.
    // 🚨 This removes the QR Scanner from rendering and using camera when it is closed or removed from the UI.
    return () => {
      if (!videoEl?.current) {
        scanner?.current?.stop();
      }
    };
  }, []);

  // ❌ If "camera" is not allowed in browser permissions, show an alert.
  useEffect(() => {
    if (!qrOn)
      alert(
        // 'Camera is blocked or not accessible. Please allow camera in your browser permissions and Reload.',
        '카메라에 접근할 수 없습니다.',
      );
  }, [qrOn]);

  return (
    <div className={styles.container}>
      <div className={styles.qrReader}>
        <video ref={videoEl}></video>
        <div className={styles.header}>
          <div className={styles.headerTop}>
            <img src={'/longLogo.svg'} alt="logo" className={styles.logo} />
          </div>
          <div className={styles.headerBottom}>
            <p className={styles.title}>QR코드 찍고 간편결제하세요!</p>
          </div>
        </div>
        <div ref={qrBoxEl} className={styles.qrBox}>
          <img src={'/qrFrame.svg'} alt="Qr Frame" className={styles.qrFrame} />
        </div>
        <div className={styles.bottom}>
          <img
            src={'/qrClose.svg'}
            alt="Qr Close"
            className={styles.qrClose}
            onClick={goBack}
          />
        </div>
        {/* Show Data Result if scan is success */}
        {/* {scannedResult && (
          <p className={styles.result}>스캔 결과입니다잉 : {scannedResult}</p>
        )} */}
      </div>
    </div>
  );
}
