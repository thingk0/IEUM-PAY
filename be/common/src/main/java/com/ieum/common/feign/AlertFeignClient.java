package com.ieum.common.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "alert", url = "${gateway.alert}")
public interface AlertFeignClient {
}
