import { createStore } from 'zustand/vanilla'

export type UserNumberState = {
  PhoneNumber: string
}

// export type CounterActions = {
//   decrementCount: () => void
//   incrementCount: () => void
// }

// export type CounterStore = CounterState & CounterActions

// export const initCounterStore = (): CounterState => {
//   return { count: new Date().getFullYear() }
// }

export const defaultInitState: UserNumberState = {
  PhoneNumber: "",
}
