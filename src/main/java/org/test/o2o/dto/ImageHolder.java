/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ImageHolder
 * Author: Administrator
 * Date: 2018-09-17 22:40
 * Description: 图片处理包装
 * History:
 * <author>      <time>      <version>   <desc>
 * <p>
 * 作者姓名      修改时间       版本号      描述
 */


package org.test.o2o.dto;


import java.io.InputStream;

/**

 * 〈功能简述〉<br>

 * 〈图片处理包装〉

 *

 * @author Administrator

 * @create 2018-09-17

 * @since 1.0.0

 */

public class ImageHolder {
    private String imageName;
    private InputStream image;

    public ImageHolder(String imageName, InputStream image) {
        this.image = image;
        this.imageName=imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}