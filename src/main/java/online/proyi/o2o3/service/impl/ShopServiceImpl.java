package online.proyi.o2o3.service.impl;

import online.proyi.o2o3.dao.repository.ShopAuthMapRepository;
import online.proyi.o2o3.dao.repository.ShopRepository;
import online.proyi.o2o3.dto.ImageHolder;
import online.proyi.o2o3.dto.ShopExecution;
import online.proyi.o2o3.entity.Shop;
import online.proyi.o2o3.entity.ShopAuthMap;
import online.proyi.o2o3.enums.ShopStateEnum;
import online.proyi.o2o3.exceptions.ShopOperationException;
import online.proyi.o2o3.service.IShopService;
import online.proyi.o2o3.utils.ImageUtil;
import online.proyi.o2o3.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 〈店铺Service〉
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl implements IShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopAuthMapRepository shopAuthMapRepository;

    //注册店铺信息，包括图片处理
    @Override

    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setUpdateTime(new Date());
            //添加店铺信息
            Shop newShop = shopRepository.save(shop);
            if (newShop.getId() == null) {
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
                    Shop updateImgShop = shopRepository.save(shop);
                    if (StringUtils.equals(shop.getImg(), updateImgShop.getImg())) {
                        throw new ShopOperationException("更新图片地址失败");
                    }

                    //执行增加shopAuthMap操作
                    ShopAuthMap shopAuthMap = new ShopAuthMap();
                    shopAuthMap.setEmployee(shop.getOwner());
                    shopAuthMap.setShop(shop);
                    shopAuthMap.setTitle("店家");
                    shopAuthMap.setTitleFlag(0);
                    shopAuthMap.setCreateTime(new Date());
                    shopAuthMap.setUpdateTime(new Date());
                    shopAuthMap.setEnableStatus(1);
                    try {
                        ShopAuthMap dbShopAuthMap = shopAuthMapRepository.save(shopAuthMap);
                        if (dbShopAuthMap.getId() == null) {
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
    public Shop getByShopId(Integer id) {
        return shopRepository.findById(id).orElse(null);
    }

    //更新店铺信息，包括对图片的处理
    @Override
    public ShopExecution updateShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null || shop.getId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                //1.判断是否需要处理图片
                if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
                    //先将原始图片删除
                    Shop tempShop = shopRepository.findById(shop.getId()).orElse(null);
                    if (tempShop != null && tempShop.getImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getImg());
                    }

                    //生成新图片
                    addShopImg(shop, thumbnail);
                }
                //2.更新店铺信息
                shop.setUpdateTime(new Date());
                try {
                    shopRepository.save(shop);
                } catch (Exception e) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                }
                Shop dbshop = shopRepository.findById(shop.getId()).orElse(null);
                return new ShopExecution(ShopStateEnum.SUCCESS, dbshop);
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        //将图片路径存储用于更新店铺信息
        shop.setImg(shopImgAddr);
    }

    //获取分页的店铺列表
    @Override
    public Page<Shop> getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        Pageable page = PageRequest.of(pageIndex, pageSize);
        return shopRepository.findList(shopCondition, page);
    }
}