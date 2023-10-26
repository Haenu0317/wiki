package com.haenu.wiki.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbookPageQueryDto  {
    private Integer page;
    private Integer size;
    private Long id;
    private String name;
}
