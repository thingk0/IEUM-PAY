import { axiosAuthApi } from '@/utils/instance';

/**
 * 진행중인 펀딩 목록 불러오는 함수
 * @returns 아이디, 개요, 제목, 날짜, 이미지, 사람 수, 목표금, 현재 모금액
 */
export const getFundListOnGoing = async () => {
  const local = axiosAuthApi();

  return await local
    .get('/api/funding/list/ongoing')
    .then((response) => {
      console.log(response);
      return response.data;
    })
    .catch((error) => {
      console.log(error.message);
    });
};

/**
 * 완료된 펀딩 목록 불러오는 함수
 * @returns 아이디, 개요, 제목, 날짜, 이미지, 사람 수, 목표금
 */
export const getFundListComplete = async () => {
  const local = axiosAuthApi();

  return await local
    .get('/api/funding/list/complete')
    .then((response) => {
      console.log(response);
      return response.data;
    })
    .catch((error) => {
      console.log(error.message);
    });
};

export const getFundDetail = async (fundId: string | string[] | undefined) => {
  const local = axiosAuthApi();

  return await local
    .get(`api/funding/${fundId}/detail`)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error.message);
    });
};
