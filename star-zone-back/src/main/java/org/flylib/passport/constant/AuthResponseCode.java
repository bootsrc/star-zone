package org.flylib.passport.constant;

/**
 * 
 * @author Frank.Liu
 * 应答码
 */
public class AuthResponseCode
{
    // 成功
    public static final int SUCCESS = 0;
    
    public static final String SUCCESS_DESC = "处理成功";
    
    // token值为空
    public static final int TOKEN_IS_NULL = 918;
    
    public static final String TOKEN_IS_NULL_DESC = "token值为空";
    
    // token值过期
    public static final int TOKEN_EXPIRED = 919;
    
    public static final String TOKEN_EXPIRED_DESC = "token值过期";
    
    // token无效
    public static final int TOKEN_INVALID = 1;
    
    public static final String TOKEN_INVALID_DESC = "token无效";

    // userId为null
    public static final int USER_ID_IS_NULL = 2;

    public static final String USER_ID_IS_NULL_DESC = "userId为null";
    
    // 状态无效
    public static final int STATUS_INVALID = 121;
    
    public static final String STATUS_INVALID_DESC = "状态无效";
    
    /******************************* 用户 begin ******************************/
    public static final int USER_MOBILE_IS_EMPTY = 200;
    public static final String USER_MOBILE_IS_EMPTY_DESC = "手机号码为空";

    // 手机号码不存在
    public static final int USER_MOBILE_NOT_FOUND = 201;
    
    public static final String USER_MOBILE_NOT_FOUND_DESC = "手机号码不存在";
    
    // 手机号码已经注册
    public static final int USER_MOBILE_REPEAT = 199;
    
    public static final String USER_MOBILE_REPEAT_DESC = "手机号码已注册";
    
    public static final int USER_REPEAT = 1000;
    public static final String USER_REPEAT_DESC = "此用户名已注册";
    
    public static final int USER_REGISTER_FAILED = 1001;
    public static final String USER_REGISTER_FAILED_DESC = "用户注册失败";
    
    // 输入密码不正确
    public static final int USER_PASSWORD_ERROR = 202;

    public static final String USER_PASSWORD_ERROR_DESC = "输入密码不正确";
    
    
    // v2 输入密码不正确
//    public static final String V2_USER_PASSWORD_ERROR = "209";
//
//    public static final String V2_USER_PASSWORD_ERROR_DESC = "您的密码输入不正确,连续输错5次将冻结账号30分钟,请注意区分大小写,您可以通过忘记密码找回密码。";
    
    
    // 密码错误5次被冻结
//    public static final String USER_FROZEN = "218";
//
//    public static final String USER_FROZEN_DESC = "您的账号因密码错误次数太多,已被冻结,请30分钟后尝试登陆！";
    
    // 验证码不正确
    public static final int USER_CAPTCHA_ERROR = 203;
    
    public static final String USER_CAPTCHA_ERROR_DESC = "验证码不正确";
    
    // 验证码已过期
    public static final int USER_CAPTCHA_EXPARIED = 204;
    
    public static final String USER_CAPTCHA_EXPARIED_DESC = "验证码已过期";
    
    // 验证码发太快
    public static final int USER_CAPTCHA_TOOFAST = 205;
//
    public static final String USER_CAPTCHA_TOOFAST_DESC = "之前的短信验证码有效，请勿重复获取";
     
    
    // 用户中心登陆错误
    public static final int USERCENTER_ERROR = 206;
    
    public static final String USERCENTER_ERROR_DESC = "用户中心登陆异常";
    
    
    
    /******************************* 用户 end ******************************/
    
    // =================== 订单模块 ===================
    // 订单处理超时
//    public static final String ORDER_TIME_OUT = "601";
//
//    public static final String ORDER_TIME_OUT_DESC = "订单处理超时";
//
//    // 业务层处理失败
//    public static final String ORDER_BUSINESS_EXCEPTION = "603";
//
//    // 业务层处理失败
//    public static final String ORDER_BUSINESS_EXCEPTION_DESC = "业务层处理失败";
//
//    // 提交订单：订单校验失败
//    public static final String ORDER_VALIDATE_EXCEPTION = "604";
//
//    public static final String ORDER_VALIDATE_EXCEPTION_DESC = "订单校验失败";
//
//    // 订单状态发生了改变
//    public static final String ORDER_STATUS_EXCHANGE_EXCEPTION = "608";
//
//    public static final String ORDER_STATUS_EXCHANGE_EXCEPTION_DESC = "订单状态发生了改变";
//
//    // 订单支付成功
//    public static final String ORDER_PAY_SUCCESS = "610";
//
//    public static final String ORDER_PAY_SUCCESS_DESC = "订单支付成功";
//
//    // 订单支付失败
//    public static final String ORDER_PAY_FAILED = "611";
//
//    public static final String ORDER_PAY_FAILED_DESC = "订单支付失败";
//
//    // =================== 阿里微信支付 ===================
//
//    public static final String ORDER_AMOUNT_EXCEED = "800";
//
//    public static final String ORDER_AMOUNT_EXCEED_DESC = "金额超出限制";
//
//    public static final String ORDER_NOT_FOUND = "801";
//
//    public static final String ORDER_NOT_FOUND_DESC = "订单不存在";
//
//    public static final String ORDER_PAID = "802";
//
//    public static final String ORDER_PAID_DESC = "该笔订单已经支付";
//
//    public static final String ORDER_VEVIFY_FAILED = "803";
//
//    public static final String ORDER_VEVIFY_FAILED_DESC = "该笔订单金额验证失败";
//
//    public static final String WEIXIN_DOMAIN_VEVIFY_FAILED = "810";
//
//    public static final String WEIXIN_DOMAIN_FAILED_DESC = "该笔订单金额验证失败";
//
//    // =================== 系统异常应答码 ===================
//
//    // 系统异常
    public static final int SYSTEM_EXCEPTION = 900;

    public static final String SYSTEM_EXCEPTION_DESC = "系统异常";
//
//    // 数据库异常
//    public static final String DB_EXCEPTION = "901";
//
//    public static final String DB_EXCEPTION_DESC = "数据库异常";
//
//    // 数据空异常
//    public static final String DB_EMPTYE_XCEPTION = "902";
//
//    public static final String DB_EMPTY_EXCEPTION_DESC = "数据空异常";
//
//    // 密钥异常
//    public static final String KEY_EXCEPTION = "902";
//
//    public static final String KEY_EXCEPTION_DESC = "密钥异常";
//
//    // 数据异常
//    public static final String DATA_EXCEPTION = "903";
//
//    public static final String DATA_EXCEPTION_DESC = "数据异常";
//
//    // 服务端保存数据异常
//    public static final String MKT_SAVE_DATA_EXCEPTION = "907";
//
//    public static final String MKT_SAVE_DATA_EXCEPTION_DESC = "服务端保存数据异常";
//
//    // 服务端转换上报的JSON数据异常
//    public static final String CONVERT_JSON_DATA_EXCEPTION = "908";
//
//    public static final String CONVERT_JSON_DATA_EXCEPTION_DESC = "服务端转换JSON数据异常";
//
//    // 系统未知错误
//    public static final String SYSTEM_ERROR = "912";
//
//    public static final String SYSTEM_ERROR_DESC = "系统未知错误";
//
//    public static final String SYSTEM_VERVIFY_FAIL = "388";
//
//    public static final String SYSTEM_VERVIFY_FAIL_DESC = "签名错误";
//
    // 网络不好
    public static final int NETWORK_NOT_GOOD = 913;

    public static final String NETWORK_NOT_GOOD_DESC = "网络不好";
//
//    // 请求太频繁
//    public static final String REQUEST_TOO_FREQUENT = "922";
//
//    public static final String REQUEST_TOO_FREQUENT_DESC = "请求太频繁";
//
//
//    //用户中心
//
//    public static final String USER_CENTER_EXCEPTION = "707";
//
//    public static final String USER_CENTER_EXCEPTION_DESC = "用户中心异常";
//
//
//    public static final String USER_SIGN_EXCEPTION = "391";
//
//    public static final String USER_SIGN_EXCEPTION_DESC = "重复签到";
//
//
//    public static final String QIANGGOU_FAIL = "999";
//
//    public static final String QIANGGOU_FAIL_DESC = "下单失败，商品已售完";
//
    public static final int USER_CENTER_NOT_EXIST = 704;

    public static final String USER_CENTER_NOT_EXIST_DESC = "用户不存在";
//
//
//
//    public static final String USER_WALLET_NOT_EXIST = "705";
//
//    public static final String USER_WALLET_NOT_EXIST_DESC = "用户钱包钱不存在";
//
//   public static final String USER_WALLET_INSUFFICIENT = "706";
//
//    public static final String USER_WALLET_INSUFFICIENT_DESC = "用户钱包钱不足";
    
//    /**
//     * 处理成功
//     * 
//     * @param response
//     */
//    public static void success(CommonOut out)
//    {
//        out.setCode(SUCCESS);
//        out.setDesc(SUCCESS_DESC);
//    }
//    
//    /**
//     * 处理成功
//     * 
//     * @param response
//     */
//    public static void success(QuickBaseRes response)
//    {
//        response.setCode(SUCCESS);
//        response.setDesc(SUCCESS_DESC);
//    }
//    
//    /**
//     * 系统异常
//     * 
//     * @param response
//     */
//    public static void sysException(QuickBaseRes response)
//    {
//        response.setCode(SYSTEM_EXCEPTION);
//        response.setDesc(SYSTEM_EXCEPTION_DESC);
//    }
//    
//    public static void sysException(CommonOut out)
//    {
//        out.setCode(SYSTEM_EXCEPTION);
//        out.setDesc(SYSTEM_EXCEPTION_DESC);
//    }
//    
//    /**
//     * 判断业务层处理是否成功
//     * 
//     * 1、如果处理成功，直接return true
//     * 
//     * 2、如果处理失败，把业务层处理失败的cod和描述赋予Action的应答对象
//     * 
//     * @param out
//     *            业务层对象
//     * @param response
//     *            Action层的应答信息
//     * @return true-处理成功，false-处理失败
//     */
//    public static boolean isSuccess(CommonOut out, QuickBaseRes response)
//    {
//        if(out.getCode().equals(SUCCESS)) return true;
//        
//        response.setCode(out.getCode());
//        response.setDesc(out.getDesc());
//        return false;
//    }
    
}
