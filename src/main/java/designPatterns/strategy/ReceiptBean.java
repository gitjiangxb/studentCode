package designPatterns.strategy;

/**
 * @ClassName: ReceiptBean
 * @Description: TODO  回执处理实体类
 * @Author: Jiangxb
 * @Date: 2020/06/22 10:14
 * @Version 1.0
 * 不同的回执类型，返回的回执信息不一样
 */
public class ReceiptBean {
    /**
     * 回执类型(`MT1101、MT2101、MT4101、MT8104、MT8105、MT9999`)
     */
    private String type;
    /**
     * 回执信息
     */
    private String message;

    public ReceiptBean(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
