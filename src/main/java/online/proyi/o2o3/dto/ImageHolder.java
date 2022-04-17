package online.proyi.o2o3.dto;


import lombok.Data;

import java.io.InputStream;

/**
 * 〈图片处理包装〉
 */

@Data
public class ImageHolder {
    private String imageName;
    private InputStream image;

}