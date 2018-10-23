/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AwardServiceImpl
 * Author: Administrator
 * Date: 2018-10-22 19:37
 * Description: 奖品管理实现
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.o2o.dao.AwardDao;
import org.test.o2o.dto.AwardExecution;
import org.test.o2o.dto.ImageHolder;
import org.test.o2o.entity.Award;
import org.test.o2o.enums.AwardStateEnum;
import org.test.o2o.exceptions.AwardOperationException;
import org.test.o2o.exceptions.ProductOperationException;
import org.test.o2o.service.AwardService;
import org.test.o2o.util.ImageUtil;
import org.test.o2o.util.PageCalculator;
import org.test.o2o.util.PathUtil;

import java.util.Date;
import java.util.List;

/**
 * 〈奖品管理实现〉
 */
@Service
public class AwardServiceImpl implements AwardService{
    @Autowired
    private AwardDao awardDao;

    @Override
    public AwardExecution getAwardList(Award awardCondition, Integer pageIndex, Integer pageSize) {
        //空值判断
        if (awardCondition!=null && pageIndex!=null && pageSize!=null) {
            //页码转成行数
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            //依据查询条件分页取出列表
            List<Award> awardList = awardDao.queryAwardList(awardCondition, beginIndex, pageSize);
            //按照同等的查询条件返回该查询条件下的奖品总数
            int count = awardDao.queryAwardCount(awardCondition);
            AwardExecution ae = new AwardExecution();
            ae.setAwardList(awardList);
            ae.setCount(count);
            return ae;
        } else {
            return null;
        }
    }

    @Override
    public Award getAwardById(Long awardId) {
        return awardDao.queryAwardByAwardId(awardId);
    }

    @Override
    @Transactional
    //1.处理缩略图，获取相对路径后赋给award
    //2.往tb_award中写入奖品信息
    public AwardExecution addAward(Award award, ImageHolder thumbnail) {
        //空值判断
        if (award!=null && award.getShopId()!=null) {
            //给award赋上初始值
            award.setCreateTime(new Date());
            award.setLastEditTime(new Date());
            //award默认可用，即出现在前端展示系统中
            award.setEnableStatus(1);
            if (thumbnail != null) {
                //若传入的图片信息不为空，则更新图片
                addThumbnail(award, thumbnail);
            }
            try {
                //添加奖品信息
                int effectedNum = awardDao.insertAward(award);
                if (effectedNum <= 0) {
                    throw new AwardOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new AwardOperationException("创建商品失败:" + e.toString());
            }
            return new AwardExecution(AwardStateEnum.SUCCESS, award);
        } else {
            return new AwardExecution(AwardStateEnum.EMPTY, award);
        }
    }

    @Override
    @Transactional
    //1.若缩略图参数有值，则处理缩略图
    //若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋给award
    //2.更新tb_award的信息
    public AwardExecution modifyward(Award award, ImageHolder thumbnail) {
        //空值判断
        if (award!=null && award.getAwardId()!=null && award.getShopId()!= null) {
            //给奖品设置上默认属性
            award.setLastEditTime(new Date());
            //若奖品缩略图不为空并且原有缩略图不为空则删除原有缩略图并添加
            if (thumbnail != null) {
                //先获取一遍原有信息,因为原来的信息里有原图片地址
                Award tempAward = awardDao.queryAwardByAwardId(award.getAwardId());
                if (tempAward.getAwardImg() != null) {
                    ImageUtil.deleteFileOrPath(tempAward.getAwardImg());
                }
                addThumbnail(award, thumbnail);
            }
            try {
                //更新商品信息
                int effectedNum = awardDao.updateAward(award);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新奖品信息失败");
                }
                return new AwardExecution(AwardStateEnum.SUCCESS, award);
            } catch (Exception e) {
                throw new RuntimeException("更新奖品信息失败:" + e.toString());
            }
        } else {
            return new AwardExecution(AwardStateEnum.EMPTY);
        }
    }

    /**
     * 添加奖品缩略图
     * @param award
     * @param imageHolder
     */
    private void addThumbnail(Award award, ImageHolder imageHolder){
        String dest= PathUtil.getShopImagePath(award.getShopId());
        String thumbnailAddr= ImageUtil.generateThumbnail(imageHolder,dest);
        award.setAwardImg(thumbnailAddr);
    }
}