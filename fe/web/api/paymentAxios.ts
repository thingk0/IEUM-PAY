import { axiosApi, axiosAuthApi } from '@/utils/instance';

const local = axiosAuthApi();

export async function getBalance() {
  return axiosAuthApi().get('/api/pay/balance');
}

export const getPaymentInfo = async (store: string, price: string) => {
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
  return await local
    .post('api/payment', paymentInfo)
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error.message);
    });
};
