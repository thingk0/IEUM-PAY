'use-clinet';
import { axiosApi, axiosAuthApi } from '@/utils/instance';

export async function getBalance() {
  return axiosAuthApi().get('/api/pay/balance');
}

export const getPaymentInfo = async (store: string, price: string) => {
  const local = axiosAuthApi();
  return await local
    .get(`/api/payment/info/${store}/${price}`)
    .then((response) => {
      console.log(response);
      return response.data;
    })
    .catch((error) => {
      console.log(error.message);
    });
};

export const confirmPassword = async (password: string) => {
  const local = axiosAuthApi();
  return await local
    .post('/api/auth/password', { paymentPassword: password })
    .then((response) => {
      console.log(response);
      return response.data;
    })
    .catch((error) => {
      console.log(error.message);
      return null;
    });
};

export const payment = async (paymentInfo: object) => {
  const local = axiosAuthApi();
  return await local
    .post('api/payment', paymentInfo)
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error.message);
    });
};

export async function postCardImage(formData: FormData) {
  let axiosConfig = {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  };
  return axiosAuthApi().post('/api/card/ocr', formData, axiosConfig);
}
