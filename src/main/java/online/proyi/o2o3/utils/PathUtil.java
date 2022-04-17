package online.proyi.o2o3.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 〈图片路径工具类〉
 */
@Configuration
public class PathUtil {
    //获取系统的文件分隔符"/"
    private static String separator = System.getProperty("file.separator");

    private static String winPath;
    private static String linuxPath;
    private static String shopPath;

    @Value("${win.base.path}")
    public void setWinPath(String winPath) {
        PathUtil.winPath = winPath;
    }

    @Value("${linux.base.path}")
    public  void setLinuxPath(String linuxPath) {
        PathUtil.linuxPath = linuxPath;
    }

    @Value("${shop.relevant.path}")
    public void setShopPath(String shopPath) {
        PathUtil.shopPath = shopPath;
    }

    //图片存储位置的绝对路径
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = winPath;
        } else {
            basePath = linuxPath;
        }
        //替换不同系统的分隔符
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    //图片子路径
    public static String getShopImagePath(Integer shopId) {
        String imagePath = shopPath + shopId + separator;
        return imagePath.replace("/", separator);
    }
}