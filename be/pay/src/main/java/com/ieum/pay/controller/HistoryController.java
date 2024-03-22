package com.ieum.pay.controller;

import com.ieum.pay.request.HistoryRequestDTO;
import com.ieum.pay.response.HistoryResponseDTO;
import com.ieum.pay.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public List<HistoryResponseDTO> history(@RequestBody HistoryRequestDTO requestDTO){
        return historyService.historyList(requestDTO.getMemberId());
    }
}