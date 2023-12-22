package com.haenu.wiki.service;

import com.haenu.wiki.domain.dto.StatisticDTO;
import com.haenu.wiki.domain.pojo.EbookSnapshot;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
* @author Haenu0317
* @description 针对表【ebook_snapshot(电子书快照表)】的数据库操作Service
* @createDate 2023-12-22 11:13:08
*/
public interface EbookSnapshotService extends IService<EbookSnapshot> {


    void genSnapshot();

    /**
     * 获取首页数值数据：总阅读数、总点赞数、今日阅读数、今日点赞数、今日预计阅读数、今日预计阅读增长
     */
   List<StatisticDTO> getStatistic();

    /**
     * 30天数值统计
     */
   List<StatisticDTO> get30Statistic();
}
