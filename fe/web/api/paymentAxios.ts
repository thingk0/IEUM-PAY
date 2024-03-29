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
