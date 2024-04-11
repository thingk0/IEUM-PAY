import { useEffect } from 'react';

const PortraitLayout = ({ children }: { children: React.ReactNode }) => {
  useEffect(() => {
    const lockScreenOrientation = () => {
      //@ts-expect-error
      screen.orientation?.lock('portrait').catch((error) => {
        console.error('Error locking orientation:', error);
      });
    };
    lockScreenOrientation();
  }, []);

  return <>{children}</>;
};

export default PortraitLayout;
