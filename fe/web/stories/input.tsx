import React from 'react';
import './input.css';

interface inputProps {
  inputValue: (value: string) => string,
  labelValue: string,
  isPassword: boolean,
}

const Input = ({ inputValue, labelValue, isPassword} : inputProps) => {

  return (
    <>
    <div className="divTag container">
      <input 
        type="text" 
        className={`inputTag`}
        // value={inputValue}
        id={labelValue}
        onChange={(event)=> inputValue(event.target.value)}
        required
      />
      <label className={`labelTag`} htmlFor={labelValue}>
        {labelValue}
      </label>
      <span className="spanTag"></span>
    </div>
    </>
  )
  ;
};

export default Input;