import axios, { AxiosInstance } from 'axios';
import { getCookie } from './cookie';
const api = 'https://www.ieum-pay.site/';
// const accessToken = localStorage.getItem('access_token');

const axiosAuthApi = (): AxiosInstance => {
  const accessToken = getCookie('access_token');
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
