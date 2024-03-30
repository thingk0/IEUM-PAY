import { NextUIProvider } from '@nextui-org/react';
import { CounterStoreProvider } from '@/providers/counter-store-provider.tsx';
import React from 'react';
import '@/styles/globals.css';
import type { AppProps } from 'next/app';
import Head from 'next/head';
import { HydrationBoundary, QueryClient, QueryClientProvider } from '@tanstack/react-query';

export default function App({ Component, pageProps }: AppProps) {
  // if (process.env.NODE_ENV === 'development') {
  //   if (typeof window === 'undefined') {
  //     (async () => {
  //       const { server } = await import('../mocks/server');
  //       server.listen();
  //     })();
  //   } else {
  //     (async () => {
  //       const { worker } = await import('../mocks/browser');
  //       worker.start();
  //     })();
  //   }
  // }
  const [queryClient] = React.useState(
    () =>
      new QueryClient({
        defaultOptions: {
          queries: {
            // With SSR, we usually want to set some default staleTime
            // above 0 to avoid refetching immediately on the client
            staleTime: 1000,
          },
        },
      }),
  );
  return (
    <>
      <Head>
        <title>IEUM PAY</title>
        <meta
          name="viewport"
          content="minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no, user-scalable=no, viewport-fit=cover"
        />
      </Head>
      <QueryClientProvider client={queryClient}>
        <HydrationBoundary state={pageProps.dehydratedState}>
          <NextUIProvider>
            <CounterStoreProvider>
              <Component {...pageProps} />
            </CounterStoreProvider>
          </NextUIProvider>
        </HydrationBoundary>
      </QueryClientProvider>
    </>
  );
}
