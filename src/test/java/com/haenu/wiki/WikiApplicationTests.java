package com.haenu.wiki;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haenu.wiki.domain.pojo.User;
import com.haenu.wiki.mapper.UserMapper;
import com.haenu.wiki.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class WikiApplicationTests {
    @Resource
    UserService userService;
    @Test
    void contextLoads() {
        Page<User> page = userService.page(new Page<>(1, 2));
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
    }

}
