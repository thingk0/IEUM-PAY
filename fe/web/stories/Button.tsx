import React from 'react';
import classes from './button.module.scss';

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
  size?: 'thin' | 'thick';
  /**
   * Button contents
   */
  children: React.ReactNode;
  /**
   * Optional click handler
   */
  onClick?: () => void;
}

/**
 * Primary UI component for user interaction
 */
export default function Button({
  primary = true,
  size = 'thick',
  backgroundColor,
  children,
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
      className={[
        classes['storybook-button'],
        classes[`storybook-button--${size}`],
        classes[mode],
      ].join(' ')}
      style={buttonStyle}
      onClick={onClick} // onClick 핸들러를 바인딩
      {...props}
    >
      {children}
    </button>
  );
}
