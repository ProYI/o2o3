package online.proyi.o2o3.dto;

import lombok.Data;
import online.proyi.o2o3.entity.Shop;
import online.proyi.o2o3.enums.ShopStateEnum;

import java.util.List;

/**
 * 〈店铺的扩展类 添加店铺的返回值类型〉
 */
@Data
public class ShopExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //店铺数量
    private int count;

    //操作shop(增删改查的时候用到)
    private Shop shop;

    //shop列表(查询店铺列表的时候使用)
    private List<Shop> shopList;

    public ShopExecution() {
    }
    //店铺操作失败的时候生成ShopExecution对象构造器
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //店铺操作成功的构造器
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }
    //店铺操作成功的构造器
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }
}