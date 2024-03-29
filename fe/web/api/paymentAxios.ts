import { axiosApi, axiosAuthApi } from '@/utils/instance';

export async function getBalance() {
  return axiosAuthApi().get('/api/pay/balance');
}

export const getPaymentInfo = async (store: number, price: number) => {
  const local = axiosApi();

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
