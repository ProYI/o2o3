package online.proyi.o2o3.controller.superAdmin;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import online.proyi.o2o3.dao.repository.AreaRepository;
import online.proyi.o2o3.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/superAdmin")
@Tag(name = "区域管理")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @Operation(summary = "区域列表", description = "获取所有的区域列表")
    @GetMapping(value = "/listarea")
    @ResponseBody
    private Map<String, Object> listArea() {
        log.info("into listArea method!");

        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<String, Object> modelMap = Maps.newHashMap();

        try {
            List<Area> list = areaRepository.findAll();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            log.error("get AreaList 发生错误:{}", e);
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        log.debug("get AreaList costTime:{}", stopwatch.stop());
        return modelMap;
    }
}
