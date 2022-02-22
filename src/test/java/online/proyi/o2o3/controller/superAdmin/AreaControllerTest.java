package online.proyi.o2o3.controller.superAdmin;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import online.proyi.o2o3.dao.repository.AreaRepository;
import online.proyi.o2o3.entity.Area;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@Slf4j
@SpringBootTest
class AreaControllerTest {
    @Autowired
    AreaRepository areaRepository;

    @Test
    public void AreaInsertTest() {
        Area area = new Area();
        area.setName("地区1");
        area.setPriority(1);
        Area dbArea = areaRepository.save(area);
        log.info("db返回值:{}", JSONUtil.toJsonStr(dbArea));
        Assert.isTrue(dbArea.getName().equals("地区1"), "存储方法有误!");
    }

}