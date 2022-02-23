package online.proyi.o2o3.controller.superAdmin;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import online.proyi.o2o3.dao.mapper.AreaMapper;
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
    @Autowired
    AreaMapper areaMapper;

    @Test
    public void AreaInsertTest() {
        Area area = new Area();
        area.setName("地区1");
        area.setPriority(1);
        Area dbArea = areaRepository.save(area);
        log.info("db返回值:{}", JSONUtil.toJsonStr(dbArea));
        Assert.isTrue(dbArea.getName().equals("地区1"), "地区保存方法有误!");
    }

    @Test
    public void AreaSelectTest() {
        Area dbArea = areaRepository.findByName("地区1").orElse(null);
        log.info("db返回值:{}", JSONUtil.toJsonStr(dbArea));
        Assert.isTrue(dbArea.getName().equals("地区1"), "地区查询方法有误!");
    }

    @Test
    public void AreaUpdateTest() {
        Area dbArea = areaRepository.findByName("地区1").orElse(null);
        dbArea.setPriority(2);
        Area updateArea = areaRepository.save(dbArea);
        log.info("db返回值:{}", JSONUtil.toJsonStr(updateArea));
        Assert.isTrue(updateArea.getPriority().equals(2), "地区更新方法有误!");
    }

    @Test
    public void AreaLoginDeleteTest() {
        int result = areaMapper.deleteById(1);
        Assert.isTrue(result > 0 , "地区逻辑删除方法有误!");
    }
}