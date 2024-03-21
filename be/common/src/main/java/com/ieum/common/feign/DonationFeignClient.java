package com.ieum.common.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "donation", url = "${gateway.donation}")
public interface DonationFeignClient {

}
