const IeumBadge = () => {
  const svgCode = (
    <svg
      width="28"
      height="28"
      viewBox="0 0 28 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <circle cx="14" cy="14" r="14" fill="url(#paint0_linear_873_4185)" />
      <path
        d="M11.3359 14.0092C11.3359 12.8267 11.7287 11.6777 12.4526 10.7427C13.1765 9.80765 14.1905 9.13956 15.3353 8.84337C16.4801 8.54717 17.6909 8.63964 18.7774 9.10625C19.864 9.57287 20.7648 10.3872 21.3383 11.4213C21.9118 12.4554 22.1255 13.6508 21.946 14.8196C21.7664 15.9884 21.2036 17.0644 20.3461 17.8787C19.4886 18.6929 18.3849 19.1993 17.2084 19.3182C16.0319 19.4371 14.8492 19.1618 13.8461 18.5356L16.6719 14.0092L11.3359 14.0092Z"
        fill="white"
      />
      <path
        d="M16.6724 14.0098C16.6724 15.1913 16.2803 16.3394 15.5576 17.274C14.8348 18.2086 13.8223 18.877 12.6788 19.1742C11.5353 19.4714 10.3255 19.3807 9.23908 18.9163C8.15269 18.4519 7.25114 17.6401 6.67583 16.6081C6.10052 15.5761 5.88397 14.3824 6.06014 13.2141C6.23632 12.0459 6.79526 10.9691 7.6493 10.1527C8.50335 9.33626 9.60421 8.82638 10.7792 8.703C11.9543 8.57962 13.137 8.84971 14.142 9.47092L11.3365 14.0098H16.6724Z"
        fill="#D9D9D9"
      />
      <path
        d="M11.3359 14.0092C11.3359 13.0971 11.5697 12.2002 12.015 11.4041C12.4604 10.6081 13.1023 9.93948 13.8796 9.46216C14.6569 8.98485 15.5435 8.71475 16.4549 8.67767C17.3663 8.64059 18.272 8.83776 19.0855 9.25035L16.6719 14.0092L11.3359 14.0092Z"
        fill="white"
      />
      <defs>
        <linearGradient
          id="paint0_linear_873_4185"
          x1="5.89474"
          y1="3.31579"
          x2="21.3684"
          y2="23.9474"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#7835F9" />
          <stop offset="1" stopColor="#C576F5" />
        </linearGradient>
      </defs>
    </svg>
  );
  return svgCode;
};

const SeedBadge = () => {
  const svgCode = (
    <svg
      width="28"
      height="28"
      viewBox="0 0 28 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      xmlnsXlink="http://www.w3.org/1999/xlink"
    >
      <circle cx="14" cy="14" r="14" fill="url(#paint0_linear_887_4087)" />
      <rect
        x="5.93472"
        y="8.06543"
        width="16.1304"
        height="13.2391"
        fill="url(#pattern-seed-b)"
      />
      <defs>
        <pattern
          id="pattern-seed-b"
          patternContentUnits="objectBoundingBox"
          width="1"
          height="1"
        >
          <use
            xlinkHref="#image0_887_4087"
            transform="matrix(0.00787402 0 0 0.00959363 0 -0.00846239)"
          />
        </pattern>
        <linearGradient
          id="paint0_linear_887_4087"
          x1="5.89474"
          y1="3.31579"
          x2="21.3684"
          y2="23.9474"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#7835F9" />
          <stop offset="1" stopColor="#C576F5" />
        </linearGradient>
        <image
          id="image0_887_4087"
          width="127"
          height="106"
          xlinkHref="data:image/png;base64,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"
        />
      </defs>
    </svg>
  );

  return svgCode;
};
const FruitBadge = () => {
  const svgCode = (
    <svg
      width="28"
      height="28"
      viewBox="0 0 28 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      xmlnsXlink="http://www.w3.org/1999/xlink"
    >
      <circle cx="14" cy="14" r="14" fill="url(#paint0_linear_873_4186)" />
      <rect
        x="7.7608"
        y="7.45605"
        width="13.2391"
        height="13.087"
        fill="url(#pattern-fruit-b)"
      />
      <defs>
        <pattern
          id="pattern-fruit-b"
          patternContentUnits="objectBoundingBox"
          width="1"
          height="1"
        >
          <use
            xlinkHref="#image0_873_4186"
            transform="matrix(0.00840336 0 0 0.00850107 0 -0.00156335)"
          />
        </pattern>
        <linearGradient
          id="paint0_linear_873_4186"
          x1="5.89474"
          y1="3.31579"
          x2="21.3684"
          y2="23.9474"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#7835F9" />
          <stop offset="1" stopColor="#C576F5" />
        </linearGradient>
        <image
          id="image0_873_4186"
          width="119"
          height="118"
          xlinkHref="data:image/png;base64,
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"
        />
      </defs>
    </svg>
  );

  return svgCode;
};
const SessakBadge = () => {
  const svgCode = (
    <svg
      width="28"
      height="28"
      viewBox="0 0 28 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      xmlnsXlink="http://www.w3.org/1999/xlink"
    >
      <circle cx="14" cy="14" r="14" fill="url(#paint0_linear_873_4188)" />
      <rect
        x="6.54346"
        y="8.06543"
        width="15.0652"
        height="13.2391"
        fill="url(#pattern-sessak-b)"
      />
      <defs>
        <pattern
          id="pattern-sessak-b"
          patternContentUnits="objectBoundingBox"
          width="1"
          height="1"
        >
          <use
            xlinkHref="#image0_873_4188"
            transform="matrix(0.00798898 0 0 0.00909091 -0.00330575 0)"
          />
        </pattern>
        <linearGradient
          id="paint0_linear_873_4188"
          x1="5.89474"
          y1="3.31579"
          x2="21.3684"
          y2="23.9474"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#7835F9" />
          <stop offset="1" stopColor="#C576F5" />
        </linearGradient>
        <image
          id="image0_873_4188"
          width="126"
          height="110"
          xlinkHref="data:image/png;base64,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"
        />
      </defs>
    </svg>
  );
  return svgCode;
};

const SmallTreeBadge = () => {
  const svgCode = (
    <svg
      width="28"
      height="28"
      viewBox="0 0 28 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      xmlnsXlink="http://www.w3.org/1999/xlink"
    >
      <circle cx="14" cy="14" r="14" fill="url(#paint0_linear_873_4190)" />
      <rect
        x="7.4566"
        y="6.08691"
        width="13.087"
        height="14.4565"
        fill="url(#pattern-stree-b)"
      />
      <defs>
        <pattern
          id="pattern-stree-b"
          patternContentUnits="objectBoundingBox"
          width="1"
          height="1"
        >
          <use
            xlinkHref="#image0_873_4190"
            transform="matrix(0.00920543 0 0 0.00833333 -0.00169573 0)"
          />
        </pattern>
        <linearGradient
          id="paint0_linear_873_4190"
          x1="5.89474"
          y1="3.31579"
          x2="21.3684"
          y2="23.9474"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#7835F9" />
          <stop offset="1" stopColor="#C576F5" />
        </linearGradient>
        <image
          id="image0_873_4190"
          width="109"
          height="120"
          xlinkHref="data:image/png;base64,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"
        />
      </defs>
    </svg>
  );
  return svgCode;
};

const TooSmallTreeBadge = () => {
  const svgCode = (
    <svg
      width="28"
      height="28"
      viewBox="0 0 28 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      xmlnsXlink="http://www.w3.org/1999/xlink"
    >
      <circle cx="14" cy="14" r="14" fill="url(#paint0_linear_873_4191)" />
      <rect
        x="7.6087"
        y="6.69531"
        width="12.9348"
        height="14.6087"
        fill="url(#pattern-tstree-b)"
      />
      <defs>
        <pattern
          id="pattern-tstree-b"
          patternContentUnits="objectBoundingBox"
          width="1"
          height="1"
        >
          <use
            xlinkHref="#image0_873_4191"
            transform="matrix(0.00941177 0 0 0.00833333 -0.00823532 0)"
          />
        </pattern>
        <linearGradient
          id="paint0_linear_873_4191"
          x1="5.89474"
          y1="3.31579"
          x2="21.3684"
          y2="23.9474"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#7835F9" />
          <stop offset="1" stopColor="#C576F5" />
        </linearGradient>
        <image
          id="image0_873_4191"
          width="108"
          height="120"
          xlinkHref="data:image/png;base64,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"
        />
      </defs>
    </svg>
  );
  return svgCode;
};

const TreeBadge = () => {
  const svgCode = (
    <svg
      width="28"
      height="28"
      viewBox="0 0 28 28"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      xmlnsXlink="http://www.w3.org/1999/xlink"
    >
      <circle cx="14" cy="14" r="14" fill="url(#paint0_linear_873_4189)" />
      <rect
        x="5.17395"
        y="6.84766"
        width="17.6522"
        height="14.4565"
        fill="url(#pattern-tree-b)"
      />
      <defs>
        <pattern
          id="pattern-tree-b"
          patternContentUnits="objectBoundingBox"
          width="1"
          height="1"
        >
          <use
            xlinkHref="#image0_873_4189"
            transform="matrix(0.00634857 0 0 0.00775194 -0.00153703 0)"
          />
        </pattern>
        <linearGradient
          id="paint0_linear_873_4189"
          x1="5.89474"
          y1="3.31579"
          x2="21.3684"
          y2="23.9474"
          gradientUnits="userSpaceOnUse"
        >
          <stop stopColor="#7835F9" />
          <stop offset="1" stopColor="#C576F5" />
        </linearGradient>
        <image
          id="image0_873_4189"
          width="158"
          height="129"
          xlinkHref="data:image/png;base64,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"
        />
      </defs>
    </svg>
  );
  return svgCode;
};

interface Badges {
  [key: string]: () => JSX.Element;
}

export const badges: Badges = {
  GR001: SeedBadge,
  GR002: SessakBadge,
  GR003: TooSmallTreeBadge,
  GR004: SmallTreeBadge,
  GR005: TreeBadge,
  GR006: FruitBadge,
  GR007: IeumBadge,
};
