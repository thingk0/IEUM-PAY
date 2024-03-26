import { create } from 'zustand';

const MAX_TRANSFER = 100000000;

interface donateMoneyInfoType {
  기관아이디: number;
  기관명: string;
  남은금액: number;
  송금금액: number;
  송금은행: string;
  잔액: number;
}

interface DonateMoneyInfoState {
  donateMoneyInfo: donateMoneyInfoType;
}

interface DonateMoneyInfoActions {
  setDonateMoneyInfo: (donateMoneyInfo: donateMoneyInfoType) => void;
  pushNumber: (amount: number) => void;
  popNumber: () => void;
  addAmount: (amount: number) => void;
  setFullAmount: () => void;
  setLastAmount: () => void;
  deleteDonateMoneyInfo: () => void;
}

const defaultState = {
  기관아이디: 1,
  기관명: 'BTC 아동센터',
  남은금액: 239390,
  송금금액: 0,
  송금은행: '이음페이',
  잔액: 30000,
};

const useDonateMoneyInfo = create<
  DonateMoneyInfoState & DonateMoneyInfoActions
>((set) => ({
  donateMoneyInfo: defaultState,

  pushNumber: (amount: number) => {
    set((state) => ({
      donateMoneyInfo: {
        기관아이디: state.donateMoneyInfo.기관아이디,
        기관명: state.donateMoneyInfo.기관명,
        남은금액: state.donateMoneyInfo.남은금액,
        송금금액: Math.min(
          MAX_TRANSFER,
          state.donateMoneyInfo.송금금액 * 10 + amount,
        ),
        송금은행: state.donateMoneyInfo.송금은행,
        잔액: state.donateMoneyInfo.잔액,
      },
    }));
  },

  popNumber: () => {
    set((state) => ({
      donateMoneyInfo: {
        기관아이디: state.donateMoneyInfo.기관아이디,
        기관명: state.donateMoneyInfo.기관명,
        남은금액: state.donateMoneyInfo.남은금액,
        송금금액: Math.trunc(state.donateMoneyInfo.송금금액 / 10),
        송금은행: state.donateMoneyInfo.송금은행,
        잔액: state.donateMoneyInfo.잔액,
      },
    }));
  },

  addAmount: (amount: number) => {
    set((state) => ({
      donateMoneyInfo: {
        기관아이디: state.donateMoneyInfo.기관아이디,
        기관명: state.donateMoneyInfo.기관명,
        남은금액: state.donateMoneyInfo.남은금액,
        송금금액: Math.min(
          state.donateMoneyInfo.송금금액 + amount,
          MAX_TRANSFER,
        ),
        송금은행: state.donateMoneyInfo.송금은행,
        잔액: state.donateMoneyInfo.잔액,
      },
    }));
  },

  setFullAmount: () => {
    set((state) => ({
      donateMoneyInfo: {
        기관아이디: state.donateMoneyInfo.기관아이디,
        기관명: state.donateMoneyInfo.기관명,
        남은금액: state.donateMoneyInfo.남은금액,
        송금금액: state.donateMoneyInfo.잔액,
        송금은행: state.donateMoneyInfo.송금은행,
        잔액: state.donateMoneyInfo.잔액,
      },
    }));
  },

  setLastAmount: () => {
    set((state) => ({
      donateMoneyInfo: {
        기관아이디: state.donateMoneyInfo.기관아이디,
        기관명: state.donateMoneyInfo.기관명,
        남은금액: state.donateMoneyInfo.남은금액,
        송금금액: state.donateMoneyInfo.남은금액,
        송금은행: state.donateMoneyInfo.송금은행,
        잔액: state.donateMoneyInfo.잔액,
      },
    }));
  },

  setDonateMoneyInfo: (donateMoneyInfo: donateMoneyInfoType) => {
    set({ donateMoneyInfo });
  },

  deleteDonateMoneyInfo: () => {
    set({ donateMoneyInfo: defaultState });
  },
}));

export default useDonateMoneyInfo;
