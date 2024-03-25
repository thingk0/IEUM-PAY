package com.ieum.pay.service;

import com.ieum.pay.domain.Stores;
import com.ieum.pay.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public Stores getStoreName(Long id){
       return storeRepository.findByStoreId(id);
    }
}
