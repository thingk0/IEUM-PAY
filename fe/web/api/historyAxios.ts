import { axiosAuthApi } from '@/utils/instance';

export const getHistory = async () => {
  let instance = axiosAuthApi;
  return await instance()
    .get('api/pay/history/1')
    .then((response) => {
      console.log(response);
      return response.data;
    })
    .catch((error) => {
      console.log(error.message);
    });
};
