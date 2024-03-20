import { create } from 'zustand';
import { axiosApi } from '@/utils/instance';
import { AxiosResponse } from 'axios';

type UserStore = {
  phoneNumber: string;
  randomKey: string;
  userPassword: string;
  userName: string;
  userNickname: string;
  paymentPassword: string;

  setNumber: (value: string) => void;
  setPassword: (value: string) => void;
  setUserName: (value: string) => void;
  setUserNickname: (value: string) => void;
  setpaymentPassword: (value: string) => void;

  /**
   * random key를 요청하는 함수
   */
  requestRandomKey: () => void;
};

const instance = axiosApi();

export const useUserStore = create<UserStore>((set) => ({
  phoneNumber: '',
  randomKey: '',
  userPassword: '',
  userName: '',
  userNickname: '',
  paymentPassword: '',

  setNumber: (value) => {
    set((state) => ({ phoneNumber: (state.phoneNumber = value) }));
  },

  setPassword: (value) => {
    set((state) => ({ userPassword: (state.userPassword = value) }));
  },

  setUserName: (value) => {
    set((state) => ({ userName: (state.userName = value) }));
  },

  setUserNickname: (value) => {
    set((state) => ({ userNickname: (state.userNickname = value) }));
  },

  setpaymentPassword: (value) => {
    set((state) => ({ paymentPassword: (state.paymentPassword = value) }));
  },

  requestRandomKey: async () => {
    const response: AxiosResponse = await instance.post('api/mms/auth', {
      phoneNumber: set((state) => ({ phoneNumber: state.phoneNumber })),
    });

    set((state) => ({
      randomKey: (state.randomKey = response.data['mmsAuth']),
    }));
  },
}));
