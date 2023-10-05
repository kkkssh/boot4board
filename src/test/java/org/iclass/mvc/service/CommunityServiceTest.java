package org.iclass.mvc.service;

import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dao.CommunityMapper;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class CommunityServiceTest {

    @Autowired
    CommunityMapper dao;

    @Autowired
    CommunityService service;

    @Test
    void pagelist() {
        // 아래 dto는 Controller 를 사용하면 직접 받을 수 있으므로 생성자로 값 설정은 테스트용입니다.
        // PageRequestDTO dto = new PageRequestDTO(1, 0, 0, 0, new String[]{"t","c"}, null, "회원", null, null);
        PageRequestDTO dto = new PageRequestDTO(1, 0, 0, 0, null, "tcw", "공지", null, null);   // view 에서 받는 type
        List<Community> list = service.pagelist(dto);
        list.forEach(i -> {
            log.info(">>>> 글 : {}", i);
        });
    }
}