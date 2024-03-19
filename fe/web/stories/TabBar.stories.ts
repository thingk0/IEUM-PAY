import type { Meta, StoryObj } from '@storybook/react';
import TabBar from './TabBar';
import { tabBarElementCode } from './TabBar';

const meta = {
  title: 'Example/TabBar',
  component: TabBar,
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/writing-docs/autodocs
  tags: ['autodocs'],
  parameters: {
    // More on how to position stories at: https://storybook.js.org/docs/configure/story-layout
    layout: 'fullscreen',
  },
} satisfies Meta<typeof TabBar>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Payment: Story = {
  args: {
    selected: tabBarElementCode.payment,
  },
};
export const History: Story = {
  args: {
    selected: tabBarElementCode.history,
  },
};

export const Fundraising: Story = {
  args: {
    selected: tabBarElementCode.fundraising,
  },
};
export const SendMoney: Story = {
  args: {
    selected: tabBarElementCode.sendMoney,
  },
};
export const MyPage: Story = {
  args: {
    selected: tabBarElementCode.myPage,
  },
};
