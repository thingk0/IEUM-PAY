package com.ieum.pay.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public record CardValidRequest(Long memberId, Long registeredCardId) {

}