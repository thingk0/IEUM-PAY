package com.ieum.pay.controller;

import com.ieum.pay.response.HistoryResponseDTO;
import com.ieum.pay.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("{memberId}")
    public List<HistoryResponseDTO> history(@PathVariable Long memberId){
        return historyService.historyList(memberId);
    }
}