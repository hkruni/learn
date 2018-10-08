package learn.BasicType;

import com.google.common.collect.Lists;

import java.util.List;

public enum EnumValidationType {

    BACK_GROUND_COLOR(101, "图文底色校验"),
    TEXT_RANGE(102, "文字安全区域校验"),
    IMG_SENSITIVE(103, "图片敏感词校验"),
    URL_VISITABLE(104, "落地页是否能正常访问"),
    ACTIVITY_EXPIRATION(105, "活动是否过期"),
    URL_LINK_FORMAT(106, "落地页链接是否正确"),
    URL_SENSITIVE(107, "落地页是否含有敏感词"),
    URL_FORBIDDEN(108, "落地页sku是否属于禁投"),
    URL_SHARED_INCLUDED(109, "落地页是否含有分享词分享图"),
    URL_SHARED_SENSITIVE(110, "落地页分享词分享图是否含有敏感词"),
    CONTAINS_SKU(111, "落地页是否包含引流sku");


    EnumValidationType(int errorCode, String desc) {
        this.errorCode = errorCode;
        this.desc = desc;
    }

    //  错误码
    private int errorCode;
    //  描述
    private String desc;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void main(String[] args) {
        for (EnumValidationType type : EnumValidationType.values()) {
            System.out.println(type.getErrorCode() + " : " + type.getDesc());
        }

        List<EnumValidationType> list =  Lists.newArrayList(EnumValidationType.values());
        for (EnumValidationType type : list) {
            System.out.println(type.getDesc());
        }

    }
}
