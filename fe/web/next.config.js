const prod = process.env.NODE_ENV === 'production';
const withPWA = require('next-pwa')({
  dest: 'public',
  disable: prod ? false : true,
});

module.exports = withPWA({
  // next.js config
  reactStrictMode: true,
});

const nextConfig = {
  async redirects() {
    return [
      {
        source: '/',
        destination: '/main',
        permanent: true,
      },
    ];
  },
};

module.exports = nextConfig;
