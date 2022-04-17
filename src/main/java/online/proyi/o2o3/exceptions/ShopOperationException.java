package online.proyi.o2o3.exceptions;


/**
 * 〈店铺相关的业务异常〉
 */

public class ShopOperationException extends RuntimeException {
    public ShopOperationException(String msg) {
        super(msg);
    }
}