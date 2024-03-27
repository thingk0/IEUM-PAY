import axios, { AxiosInstance } from 'axios';
const api = 'http://j10b103.p.ssafy.io:8081/';
// const accessToken = localStorage.getItem('access_token');

const axiosAuthApi = (): AxiosInstance => {
  /**
   * 나중에 터질 것 같음
   */
  const accessToken =
    typeof window != undefined ? localStorage.getItem('access_token') : '';
  const instance = axios.create({
    baseURL: api,
    headers: { Authorization: 'Bearer ' + accessToken },
    timeout: 1000,
  });
  return instance;
};

const axiosApi = (): AxiosInstance => {
  const instance = axios.create({
    baseURL: api,
    timeout: 1000,
  });
  return instance;
};

export { axiosApi, axiosAuthApi };
