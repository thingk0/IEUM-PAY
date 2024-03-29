function GradientSVG(): JSX.Element {
  const idCSS: string = 'hello';

  return (
    <svg style={{ height: 0 }}>
      <defs>
        <linearGradient
          id={idCSS}
          x1="22.9474"
          y1="12.9079"
          x2="83.1842"
          y2="93.2237"
          gradientUnits="userSpaceOnUse"
        >
          <stop stop-color="#7835F9" />
          <stop offset="1" stop-color="#C576F5" />
        </linearGradient>
      </defs>
    </svg>
  );
}

export default GradientSVG;
