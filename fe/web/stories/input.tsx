import React from 'react';

import './input.css';

export default function Input () {
  const size = 'medium'

  return (
    <input type="text" className={`input ${size}`} />
  );
}
