package org.test.o2o.service;

import org.test.o2o.dto.ImageHolder;
import org.test.o2o.dto.ShopExecution;
import org.test.o2o.entity.Shop;
import org.test.o2o.exceptions.ShopOperationException;

public interface ShopService {
    //注册店铺信息，包括图片处理 文件流和文件名通过ImageHolder重构，使代码更加清晰
    ShopExecution addShop(Shop shop, ImageHolder thumbnail);

    //通过店铺Id获取店铺信息
    Shop getByShopId(long shopId);

    //更新店铺信息，包括对图片的处理 方法需要的 文件流和文件名通过ImageHolder重构，使代码更加清晰
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    //根据shopCondition分页返回相应店铺列表数据
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
