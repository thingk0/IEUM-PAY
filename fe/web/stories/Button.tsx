import React from 'react';
import './button.css';

interface ButtonProps {
  /**
   * Is this the principal call to action on the page?
   */
  primary?: boolean;
  /**
   * What background color to use
   */
  backgroundColor?: string;
  /**
   * How large should the button be?
   */
  size?: 'small' | 'medium' | 'large';
  /**
   * Button contents
   */
  label: string;
  /**
   * Optional click handler
   */
  onClick?: () => void;
}

/**
 * Primary UI component for user interaction
 */
export default function Button({
  primary = false,
  size = 'medium',
  backgroundColor = '#ffffff',
  label,
  onClick,
  ...props
}: ButtonProps) {
  const mode = primary
    ? 'storybook-button--primary'
    : 'storybook-button--secondary';
  const buttonStyle = {
    backgroundColor,
  };

  return (
    <button
      type="button"
      className={['storybook-button', `storybook-button--${size}`, mode].join(
        ' ',
      )}
      style={buttonStyle}
      onClick={onClick} // onClick 핸들러를 바인딩
      {...props}
    >
      {label}
    </button>
  );
}

Button.defaultProps = {
  primary: false,
  backgroundColor: '#ffffff',
  size: 'medium' as 'medium',
  label: '버튼',
  onClick: () => {}, // defaultProps로 기본값 설정
};
