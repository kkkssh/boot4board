package org.iclass.mvc.dao;

import lombok.extern.slf4j.Slf4j;
import org.iclass.mvc.dto.Community;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class CommunityMapperTest {

    @Autowired
    CommunityMapper dao;

    @DisplayName("저장된 글의 개수는 0이 아닙니다.")
    @Test
    void count() {
        int count = 0; // dao.count();
        log.info("커뮤니티 count : {}", count);
        Assertions.assertNotEquals(0, count);
    }

    @DisplayName("지정된 글번호 조회된 글이 있어야 합니다.")
    @Test
    void selectByIdx() {
        Community dto = dao.selectByIdx(128);
        log.info("조회된 글 Community : {}", dto);
        Assertions.assertNotNull(dto);
    }
}