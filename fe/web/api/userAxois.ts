'use-clinet';

import axios, { AxiosRequestConfig } from 'axios';
import { axiosApi, axiosAuthApi } from '@/utils/instance';
import useUserStore from '@/stores/user-store';
import { cookies } from 'next/headers';
import { setCookie } from '@/utils/cookie';

/**
 * login 후 토큰 저장후 true 리턴
 */
export const customlogin = async (phoneNumber: string, password: string) => {
  const local = axiosApi();

  return await local
    .post('api/member/login', {
      phoneNumber: phoneNumber,
      password: password,
    })
    .then((response) => {
      console.log(response.data.data);
      // cookies().set('access_token', response.data.data);
      setCookie('access_token', response.data.data, 1);
      localStorage['access_token'] = response.data.data;
      return true;
    })
    .catch((error) => {
      console.log(error.message);
      return false;
    });
};

/**
 * 랜덤 키 요청하는 함수
 */
export const requestRandomKey = async (phoneNumber: string) => {
  const local = axiosApi();
  return await local
    .get(`api/auth?phone-number=${phoneNumber}`)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error.message);
    });
};

/**
 * 유저 정보를 불러오는 함수
 * @returns reponse data 값 or error
 */
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

export const IsRegister = async (nunmber: string) => {
  const local = axiosApi();

  return await local
    .get(`api/member/exist?phone-number=${nunmber}`)
    .then((response) => {
      console.log(response);
      return response.data;
    })
    .catch((error) => {
      console.log('에러뜸');
      console.log(error.message);
    });
};

interface registerType {
  phoneNumber: string;
  name: string;
  nickName: string;
  password: string;
  passwordConfirm: string;
  paymentPassword: string;
}

export const register = async ({
  phoneNumber,
  name,
  nickName,
  password,
  passwordConfirm,
  paymentPassword,
}: registerType) => {
  const local = axiosApi();

  return await local
    .post('api/member', {
      phoneNumber,
      name,
      nickName,
      password,
      passwordConfirm,
      paymentPassword,
    })
    .then((response) => {
      console.log(response);
      return true;
    })
    .catch((error) => {
      console.log(error.message);
      return false;
    });
};
