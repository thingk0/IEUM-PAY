import { create } from 'zustand';

interface UserInfoType {
  phoneNumber: string;
  randomKey: string;
  userPassword: string;
  userName: string;
  userNickname: string;
  paymentPassword: string;
  isLogin: boolean;
}

interface UserInfoState {
  userInfo: UserInfoType;
}

interface UserInfoActions {
  setPhoneNumber: (value: string) => void;
  setPassword: (value: string) => void;
  setUserName: (value: string) => void;
  setUserNickname: (value: string) => void;
  setPaymentPassword: (value: string) => void;
  setRandomKey: (value: string) => void;
  setIsLogin: (value: boolean) => void;
}

const defaultState = {
  phoneNumber: '010-1245-1235',
  randomKey: 'dsawrbzsdf',
  userPassword: '1q2w3e4r!@#$',
  userName: '김범수',
  userNickname: 'zl존범수',
  paymentPassword: '000111',
  isLogin: false,
};

const useUserStore = create<UserInfoState & UserInfoActions>((set) => ({
  userInfo: defaultState,

  setPhoneNumber: (phoneNumber) =>
    set((state) => ({
      userInfo: {
        phoneNumber: phoneNumber,
        randomKey: state.userInfo.randomKey,
        userPassword: state.userInfo.userPassword,
        userName: state.userInfo.userName,
        userNickname: state.userInfo.userNickname,
        paymentPassword: state.userInfo.paymentPassword,
        isLogin: state.userInfo.isLogin,
      },
    })),
  setPassword: (userPassword) =>
    set((state) => ({
      userInfo: {
        phoneNumber: state.userInfo.phoneNumber,
        randomKey: state.userInfo.randomKey,
        userPassword: userPassword,
        userName: state.userInfo.userName,
        userNickname: state.userInfo.userNickname,
        paymentPassword: state.userInfo.paymentPassword,
        isLogin: state.userInfo.isLogin,
      },
    })),
  setUserName: (userName) =>
    set((state) => ({
      userInfo: {
        phoneNumber: state.userInfo.phoneNumber,
        randomKey: state.userInfo.randomKey,
        userPassword: state.userInfo.userPassword,
        userName: userName,
        userNickname: state.userInfo.userNickname,
        paymentPassword: state.userInfo.paymentPassword,
        isLogin: state.userInfo.isLogin,
      },
    })),
  setUserNickname: (userNickname) =>
    set((state) => ({
      userInfo: {
        phoneNumber: state.userInfo.phoneNumber,
        randomKey: state.userInfo.randomKey,
        userPassword: state.userInfo.userPassword,
        userName: state.userInfo.userName,
        userNickname: userNickname,
        paymentPassword: state.userInfo.paymentPassword,
        isLogin: state.userInfo.isLogin,
      },
    })),
  setPaymentPassword: (paymentPassword) =>
    set((state) => ({
      userInfo: {
        phoneNumber: state.userInfo.phoneNumber,
        randomKey: state.userInfo.randomKey,
        userPassword: state.userInfo.userPassword,
        userName: state.userInfo.userName,
        userNickname: state.userInfo.userNickname,
        paymentPassword: paymentPassword,
        isLogin: state.userInfo.isLogin,
      },
    })),
  setRandomKey: (randomKey) =>
    set((state) => ({
      userInfo: {
        phoneNumber: state.userInfo.phoneNumber,
        randomKey: randomKey,
        userPassword: state.userInfo.userPassword,
        userName: state.userInfo.userName,
        userNickname: state.userInfo.userNickname,
        paymentPassword: state.userInfo.paymentPassword,
        isLogin: state.userInfo.isLogin,
      },
    })),
  setIsLogin: (isLogin) =>
    set((state) => ({
      userInfo: {
        phoneNumber: state.userInfo.phoneNumber,
        randomKey: state.userInfo.randomKey,
        userPassword: state.userInfo.userPassword,
        userName: state.userInfo.userName,
        userNickname: state.userInfo.userNickname,
        paymentPassword: state.userInfo.paymentPassword,
        isLogin: isLogin,
      },
    })),
}));

export default useUserStore;
