package com.haenu.wiki.mapper;

import org.apache.ibatis.annotations.Param;

public interface DocMapperCust {

    void increaseViewCount(@Param("id") String id);

    /**
     * 定时更新电子书信息
     */
    void updateEbookInfo();
}
