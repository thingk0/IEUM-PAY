import { create } from 'zustand';
import { axiosApi } from '@/utils/instance';
import { AxiosResponse } from 'axios';

type UserStore = {
  phoneNumber: string;
  randomKey: string;
  userPassword: string;
  /**
   * random key를 요청하는 함수
   */
  requestRandomKey: () => void;
  setNumber: (num: string) => void;
  setPassword: (words: string) => void;
};

const instance = axiosApi();

export const useUserStore = create<UserStore>((set) => ({
  phoneNumber: '',
  randomKey: '',
  userPassword: '',

  setNumber: (num) => {
    set((state) => ({ phoneNumber: (state.phoneNumber = num) }));
  },

  setPassword: (words) => {
    set((state) => ({ userPassword: (state.userPassword = words) }));
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
