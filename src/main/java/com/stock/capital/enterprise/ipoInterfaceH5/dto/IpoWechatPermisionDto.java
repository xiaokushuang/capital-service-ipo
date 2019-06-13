package com.stock.capital.enterprise.ipoInterfaceH5.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
*
*  @author yangj
*/
public class IpoWechatPermisionDto implements Serializable {

    private static final long serialVersionUID = 1560319346429L;


    /**
     * 主键
     * 主键
     * isNullAble:0
     */
    private String id;

    /**
     * 用户在公众平台下唯一标识
     * isNullAble:1
     */
    private String unionid;

    /**
     * 用户在公众号下唯一标识
     * isNullAble:1
     */
    private String openid;

    /**
     * 用户昵称
     * isNullAble:1
     */
    private String nickname;

    /**
     * 性别 值为1时是男性，值为2时是女性，值为0时是未知
     * isNullAble:1
     */
    private String sex;

    /**
     * 用户个人资料填写的省份
     * isNullAble:1
     */
    private String province;

    /**
     * 普通用户个人资料填写的城市
     * isNullAble:1
     */
    private String city;

    /**
     * 国家
     * isNullAble:1
     */
    private String country;

    /**
     * 用户头像
     * isNullAble:1
     */
    private String headimgurl;

    /**
     * 网页授权接口调用凭证
     * isNullAble:1
     */
    private String access_token;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUnionid() {
        return this.unionid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return this.sex;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return this.province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getHeadimgurl() {
        return this.headimgurl;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    @Override
    public String toString() {
        return "IpoWechatPermision{" +
                "id='" + id + '\'' +
                "unionid='" + unionid + '\'' +
                "openid='" + openid + '\'' +
                "nickname='" + nickname + '\'' +
                "sex='" + sex + '\'' +
                "province='" + province + '\'' +
                "city='" + city + '\'' +
                "country='" + country + '\'' +
                "headimgurl='" + headimgurl + '\'' +
                "access_token='" + access_token + '\'' +
                '}';
    }

}