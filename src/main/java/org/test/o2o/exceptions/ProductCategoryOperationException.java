
package org.test.o2o.exceptions;


/**

 * 〈功能简述〉<br>

 * 〈商品类别的自定义异常〉

 *

 * @author Administrator

 * @create 2018-09-11

 * @since 1.0.0

 */

public class ProductCategoryOperationException extends RuntimeException {
    public ProductCategoryOperationException(String msg) {
        super(msg);
    }
}