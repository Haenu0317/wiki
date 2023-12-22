package com.haenu.wiki.controller;


import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.dto.StatisticDTO;
import com.haenu.wiki.service.EbookSnapshotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public Result<List<StatisticDTO>> getStatistic() {
        List<StatisticDTO> StatisticDTO = ebookSnapshotService.getStatistic();
        return Result.success(StatisticDTO);
    }

    @GetMapping("/get-30-statistic")
    public Result<List<StatisticDTO>> get30Statistic() {
        List<StatisticDTO> StatisticDTO = ebookSnapshotService.get30Statistic();
        return Result.success(StatisticDTO);
    }

}
