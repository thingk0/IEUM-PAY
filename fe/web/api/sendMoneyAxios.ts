import { axiosAuthApi } from '@/utils/instance';

export async function getMemberByPhoneNumber(phoneNumber: number | string) {
  return axiosAuthApi()
    .post('/api/member/search?phone-number=' + phoneNumber)
    .then((response) => {
      console.log(response.data.data);
      return response.data.data;
    })
    .catch((error) => {
      console.log(error.message);
    });
}
