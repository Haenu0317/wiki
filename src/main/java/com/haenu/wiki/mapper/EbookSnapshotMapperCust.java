package com.haenu.wiki.mapper;


import com.haenu.wiki.domain.dto.StatisticDTO;

import java.util.List;

public interface EbookSnapshotMapperCust {

    void genSnapshot();

    List<StatisticDTO> getStatistic();

    List<StatisticDTO> get30Statistic();
}
