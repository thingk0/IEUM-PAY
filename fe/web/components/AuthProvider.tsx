import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';

function AuthProvider({ children }: { children: React.ReactNode }) {
  const [isLogin, setIsLogin] = useState(false);
  const [shouldRender, setShouldRender] = useState(false);
  const router = useRouter();
  useEffect(() => {
    async function protect() {
      if (!localStorage.getItem('access_token')) {
        router.replace('/user').then(() => setShouldRender(true));
      }
    }
    protect();
  }, [shouldRender]);
  return shouldRender ? <>{children}</> : <>로딩</>;
}
export default AuthProvider;
