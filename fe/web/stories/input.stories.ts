import { Meta, StoryObj} from '@storybook/react'
import React from 'react';
import Input from './input';

const meta = {
    title : 'Example/Input',
    component: Input,
    tags: ['autodocs'],
    parameters : {
        layout : 'fullscreen',
    }
} satisfies Meta<typeof Input>;

export default meta;

type Story = StoryObj<typeof meta>;

export const NormalInput: Story = {
    args: {
        labelValue: 'test',
        isPassword: false,
    },
}
