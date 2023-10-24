package com.haenu.wiki;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haenu.wiki.domain.pojo.Ebook;
import com.haenu.wiki.domain.pojo.User;
import com.haenu.wiki.mapper.UserMapper;
import com.haenu.wiki.service.EbookService;
import com.haenu.wiki.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class WikiApplicationTests {
    @Resource
    EbookService ebookService;
    @Test
    void contextLoads() {
        Ebook byId = ebookService.getById(1L);
        System.out.println(byId);
    }

}
