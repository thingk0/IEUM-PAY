import { NextUIProvider } from '@nextui-org/react';
import { CounterStoreProvider } from '@/providers/counter-store-provider.tsx';
import React from 'react';
import '@/styles/globals.css';
import type { AppProps } from 'next/app';
import Head from 'next/head';

export default function App({ Component, pageProps }: AppProps) {
  if (process.env.NODE_ENV === 'development') {
    if (typeof window === 'undefined') {
      (async () => {
        const { server } = await import('../mocks/server');
        server.listen();
      })();
    } else {
      (async () => {
        const { worker } = await import('../mocks/browser');
        worker.start();
      })();
    }
  }
  return (
    <>
      <Head>
        <meta
          name="viewport"
          content="minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no, user-scalable=no, viewport-fit=cover"
        />
      </Head>
      <NextUIProvider>
        <CounterStoreProvider>
          <Component {...pageProps} />
        </CounterStoreProvider>
      </NextUIProvider>
    </>
  );
}
