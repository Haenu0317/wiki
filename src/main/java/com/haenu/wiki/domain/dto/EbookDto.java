package com.haenu.wiki.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

@Data
public class EbookDto {
    private Long id;
    private String name;
}
