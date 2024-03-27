import { useEffect } from 'react';
import Html5QrcodeScanner from 'html5-qrcode';
import { Html5QrcodeError } from 'html5-qrcode/esm/core';

const ScannerComponent: React.FC = () => {
  //   useEffect(() => {
  //     const html5QrcodeScanner = new Html5QrcodeScanner(
  //       'reader',
  //       {
  //         // Configuration options
  //       },
  //       false, // verbose option
  //     );

  //     const onScanSuccess = (decodedText: string, decodedResult: any) => {
  //       console.log(`Scan result: ${decodedText}`, decodedResult);
  //       html5QrcodeScanner.clear();
  //     };

  //     const onScanError = (errorMessage: Html5QrcodeError) => {
  //       // handle on error condition, with error message
  //     };

  //     html5QrcodeScanner.render(onScanSuccess, onScanError);

  //     return () => {
  //       html5QrcodeScanner.clear();
  //     };
  //   }, []);

  return (
    <div id="reader">
      <p>스캔</p>
    </div>
  );
};

export default ScannerComponent;
