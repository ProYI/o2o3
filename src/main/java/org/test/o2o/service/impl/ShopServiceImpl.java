/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ShopServiceImpl
 * Author: Administrator
 * Date: 2018-09-11 0:14
 * Description: 店铺Service
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.o2o.dao.ShopAuthMapDao;
import org.test.o2o.dao.ShopDao;
import org.test.o2o.dto.ImageHolder;
import org.test.o2o.dto.ShopExecution;
import org.test.o2o.entity.Shop;
import org.test.o2o.entity.ShopAuthMap;
import org.test.o2o.enums.ShopStateEnum;
import org.test.o2o.exceptions.ShopOperationException;
import org.test.o2o.service.ShopService;
import org.test.o2o.util.ImageUtil;
import org.test.o2o.util.PageCalculator;
import org.test.o2o.util.PathUtil;

import java.util.Date;
import java.util.List;

/**

 * 〈功能简述〉<br>

 * 〈店铺Service〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ShopAuthMapDao shopAuthMapDao;

    //注册店铺信息，包括图片处理
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
        //空值判断
        if(shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (thumbnail.getImage() != null) {
                    //存储图片
                    try {
                        addShopImg(shop, thumbnail);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    //因为java是值传递，所以更新图片地址后，访问相同对象，都可以访问到地址
                    //更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }

                    //执行增加shopAuthMap操作
                    ShopAuthMap shopAuthMap = new ShopAuthMap();
                    shopAuthMap.setEmployee(shop.getOwner());
                    shopAuthMap.setShop(shop);
                    shopAuthMap.setTitle("店家");
                    shopAuthMap.setTitleFlag(0);
                    shopAuthMap.setCreateTime(new Date());
                    shopAuthMap.setLastEditTime(new Date());
                    shopAuthMap.setEnableStatus(1);
                    try {
                        effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
                        if (effectedNum <= 0) {
                            throw new ShopOperationException("授权创建失败");
                        }
                    } catch (Exception e) {
                        throw new ShopOperationException("insertShopAuthMap error:" + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    //通过店铺Id获取店铺信息
    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    //更新店铺信息，包括对图片的处理
    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop==null || shop.getShopId()==null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                //1.判断是否需要处理图片
                if (thumbnail.getImage()!=null && thumbnail.getImageName()!=null && !"".equals(thumbnail.getImageName())) {
                    //先将原始图片删除
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }

                    //生成新图片
                    addShopImg(shop, thumbnail);
                }
                //2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        //将图片路径存储用于更新店铺信息
        shop.setShopImg(shopImgAddr);
    }

    //获取分页的店铺列表
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution shopExecution = new ShopExecution();
        if (shopList != null) {
            shopExecution.setShopList(shopList);
            shopExecution.setCount(count);
        } else {
            shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return shopExecution;
    }
}