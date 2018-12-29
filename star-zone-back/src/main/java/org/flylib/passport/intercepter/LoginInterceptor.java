package org.flylib.passport.intercepter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.flylib.passport.annotation.AuthController;
import org.flylib.passport.constant.AuthResponseCode;
import org.flylib.passport.entity.LoginResult;
import org.flylib.passport.entity.Passport;
import org.flylib.passport.service.LoginIntercepterService;
import org.flylib.passport.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Frank.Liu
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    LoginIntercepterService loginIntercepterService;

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            logger.info("进入拦截器...........");
            Object controller = handlerMethod.getBean();
            printParameters(request, controller);
            // 访问需要登录的接口那些
            // 被@AuthController注解的Controller
            boolean isPresent = controller.getClass().isAnnotationPresent(AuthController.class);

            if (!isPresent) { // 不是需要登录验证的Controller，放行
                return true;
            } else {
                String userIdStr = request.getHeader("userId");
                String token = request.getHeader("token");
                if (!StringUtils.isEmpty(userIdStr)) {
                    long userId = Long.valueOf(userIdStr);
                    if (userId > 0) {
//                        logger.info("访问需要登录的接口(Authed)...........");
                        if (StringUtils.isEmpty(token)) {
                            flushError(response, AuthResponseCode.TOKEN_IS_NULL, AuthResponseCode.TOKEN_IS_NULL_DESC);
                            return false;
                        }
                        Passport passport = loginIntercepterService.getPassport(userId);
                        String storedToken = passport.getToken();
                        if (StringUtils.isEmpty(storedToken)) {
                            flushError(response, AuthResponseCode.TOKEN_EXPIRED, AuthResponseCode.TOKEN_EXPIRED_DESC);
                            return false;
                        } else if (!storedToken.equals(token)) {
                            flushError(response, AuthResponseCode.TOKEN_INVALID, AuthResponseCode.TOKEN_INVALID_DESC);
                            return false;
                        }

                    } // 被@AuthController注解的 结束
                } else {
                    flushError(response, AuthResponseCode.USER_ID_IS_NULL, AuthResponseCode.USER_ID_IS_NULL_DESC);
                    return false;
                }

            }


        }     //HandlerMethod 结束

        boolean flag = isPermission(request, response);
//        logger.info("isPermission:" + flag);
        return flag;
    }

    // private boolean isPermission(HttpServletRequest request,
    // HttpServletResponse response) throws IOException {
    // }
    private boolean isPermission(HttpServletRequest request, HttpServletResponse response) {
        return true;
        /*
         * String path=request.getServletPath();
         * System.out.println("request.getServletPath()"+request.getServletPath(
         * )); if(path.contains("public/wxpay/notify")){ return true; } //访问次数限制
         * if(accessUtil.isPermission(request)){ return true; }else{
         * flushError(response,MobResponseCode.REQUEST_TOO_FREQUENT,
         * MobResponseCode.REQUEST_TOO_FREQUENT_DESC); return false; }
         */
    }

    /**
     * 输出请求参数
     *
     * @param request
     * @param controller
     */
    private void printParameters(HttpServletRequest request, Object controller) {
        StringBuilder paramsResponse = new StringBuilder();

        String controllerName = controller.getClass().getSimpleName();
        if (controllerName.contains("WebCache")) {
            return;
        }
        Map<String, String[]> paramsMap = request.getParameterMap();

        paramsResponse.append("request params :\n");

        for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
            paramsResponse.append(entry.getKey() + ":" + entry.getValue()[0] + "\n");
        }
        // logger.info(paramsResponse.toString());
        System.out.println(paramsResponse.toString());
        System.out.println(controllerName);
    }

    private void flushError(HttpServletResponse res, int code, String desc) throws IOException {
        res.setCharacterEncoding("utf-8");
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = res.getWriter();

        LoginResult loginResult = new LoginResult();
        loginResult.setCode(code);
        loginResult.setCodeDesc(desc);
        String jsonStr = JSON.toJSONString(loginResult);

        writer.append(jsonStr);
        writer.flush();
        writer.close();
    }
}
