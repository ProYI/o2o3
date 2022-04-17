package online.proyi.o2o3.service;


import online.proyi.o2o3.dto.ImageHolder;
import online.proyi.o2o3.dto.ShopExecution;
import online.proyi.o2o3.entity.Shop;
import online.proyi.o2o3.exceptions.ShopOperationException;
import org.springframework.data.domain.Page;

public interface IShopService {
    //注册店铺信息，包括图片处理 文件流和文件名通过ImageHolder重构，使代码更加清晰
    ShopExecution addShop(Shop shop, ImageHolder thumbnail);

    //通过店铺Id获取店铺信息
    Shop getByShopId(Integer shopId);

    //更新店铺信息，包括对图片的处理 方法需要的 文件流和文件名通过ImageHolder重构，使代码更加清晰
    ShopExecution updateShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    //根据shopCondition分页返回相应店铺列表数据
    Page<Shop> getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
