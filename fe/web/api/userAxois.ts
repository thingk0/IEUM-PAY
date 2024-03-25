import axios, { AxiosRequestConfig } from 'axios';
import { axiosApi, axiosAuthApi } from '@/utils/instance';
import useUserStore from '@/stores/user-store';
import { useRouter } from 'next/router';

/**
 * login 후 mainpage로 이동 or return error message
 */
export const customlogin = async () => {
  const { userInfo, setIsLogin } = useUserStore();
  const local = axiosApi();
  const router = useRouter();
  await local
    .post('api/member/login', {
      phoneNumber: userInfo.phoneNumber,
      password: userInfo.userPassword,
    })
    .then((response) => {
      localStorage['accessToken'] = response.data['accessToken'];
      setIsLogin(true);
      router.push('/');
    })
    .catch((error) => {
      console.log(error.message);
    });
};

export const requestRandomKey = async () => {
  const { setRandomKey, userInfo } = useUserStore();
  const local = axiosApi();
  const router = useRouter();
  await local
    .post('api/mms/auth', {
      phoneNumber: userInfo.phoneNumber,
    })
    .then((response) => {
      setRandomKey(response.data['mmsAuth']);
      router.push('/user/register/mms');
    })
    .catch((error) => {
      console.log(error.message);
    });
};

export const getUserInfo = async () => {
  const local = axiosAuthApi();

  return await local
    .get('api/member/summary')
    .then((response) => {
      console.log(response);
      return response.data;
    })
    .catch((error) => {
      console.log(error.message);
    });
};
