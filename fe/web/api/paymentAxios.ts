import { axiosApi } from '@/utils/instance';

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
