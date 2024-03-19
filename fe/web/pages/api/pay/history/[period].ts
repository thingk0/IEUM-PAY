// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import type { NextApiRequest, NextApiResponse } from 'next';
type Data = {
  historyId: number;
  historyDate: string;
  type: string;
  title: string;
  amount: number;
  detail: {
    type: string;
    name: string;
    price: number;
  }[];
}[];
const history = [
  {
    historyId: 1,
    historyDate: '2021-11-14 20:25:11',
    type: '결제',
    title: '쿠팡',
    amount: 900,
    detail: [
      {
        type: '충전',
        name: '이음페이머니',
        price: 10000,
      },
      {
        type: '결제',
        name: 'COOPANG',
        price: 500,
      },
      {
        type: '기부',
        name: 'btc 아동센터',
        price: 400,
      },
    ],
  },
  {
    historyId: 2,
    historyDate: '2021-12-14 20:25:11',
    type: '송금',
    title: '김보경',
    amount: 10000,
    detail: [
      {
        type: '송금',
        name: '김보경',
        price: 10000,
      },
    ],
  },
  {
    historyId: 3,
    historyDate: '2021-12-24 20:25:11',
    type: '입금',
    title: '김예지',
    amount: 1230,
    detail: [
      {
        type: '입금',
        name: '김예지',
        price: 1230,
      },
    ],
  },
  {
    historyId: 4,
    historyDate: '2022-11-14 20:25:11',
    type: '충전',
    title: '이음페이머니',
    amount: 20000,
    detail: [
      {
        type: '충전',
        name: '이음페이머니',
        price: 20000,
      },
    ],
  },
];

export default function handler(
  req: NextApiRequest,
  res: NextApiResponse<Data>,
) {
  res.status(200).json(history);
}
