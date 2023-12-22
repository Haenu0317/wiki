package com.haenu.wiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.wiki.domain.dto.StatisticDTO;
import com.haenu.wiki.domain.pojo.EbookSnapshot;
import com.haenu.wiki.mapper.EbookSnapshotMapper;
import com.haenu.wiki.mapper.EbookSnapshotMapperCust;
import com.haenu.wiki.service.EbookSnapshotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Haenu0317
 * @description 针对表【ebook_snapshot(电子书快照表)】的数据库操作Service实现
 * @createDate 2023-12-22 11:13:08
 */
@Service
public class EbookSnapshotServiceImpl extends ServiceImpl<EbookSnapshotMapper, EbookSnapshot>
        implements EbookSnapshotService {

    @Resource
    private EbookSnapshotMapperCust ebookSnapshotMapperCust;

    public void genSnapshot() {
        ebookSnapshotMapperCust.genSnapshot();
    }

    /**
     * 获取首页数值数据：总阅读数、总点赞数、今日阅读数、今日点赞数、今日预计阅读数、今日预计阅读增长
     */
    public List<StatisticDTO> getStatistic() {
        return ebookSnapshotMapperCust.getStatistic();
    }

    /**
     * 30天数值统计
     */
    public List<StatisticDTO> get30Statistic() {
        return ebookSnapshotMapperCust.get30Statistic();
    }

}




