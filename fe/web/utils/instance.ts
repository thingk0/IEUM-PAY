import axios, { AxiosInstance } from 'axios';
const api = 'http://192.168.31.172:8081/';
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
