package org.flylib.mall.shop.service;

import com.mysql.cj.log.Log;
import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.shop.constant.AccountType;
import org.flylib.mall.shop.constant.DefaultUserProfile;
import org.flylib.mall.shop.constant.UserStatus;
import org.flylib.mall.shop.entity.CaptchaRecord;
import org.flylib.mall.shop.entity.User;
import org.flylib.mall.shop.entity.UserProfile;
import org.flylib.mall.shop.repository.UserDAO;
import org.flylib.mall.shop.util.MD5Util;
import org.flylib.passport.entity.LoginResult;
import org.flylib.passport.service.TokenDBService;
import org.flylib.passport.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.flylib.passport.constant.AuthResponseCode;
import org.springframework.util.StringUtils;

import java.util.Date;

/** 
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月26日 下午11:35:32 
* 类说明 
*/
@Service
public class PassportService {
	private static final Logger logger = LoggerFactory.getLogger(PassportService.class);
	/**
	 * token7天内有效
	 */
	public static final long TOKEN_EXPIRE = 1000L * 60 * 60 * 24 * 7;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private TokenDBService tokenDBService;

	@Autowired
	private CaptchaRecordService captchaRecordService;

	@Autowired
	private SnowflakeIdWorker snowflakeIdWorker;

	@Autowired
	private UserProfileService userProfileService;

	public LoginResult register(String mobile, String captcha, String password) {
		LoginResult loginResult = new LoginResult();
		CaptchaRecord captchaRecord = captchaRecordService.findLatestByMobile(mobile);
		if (captchaRecord != null && !StringUtils.isEmpty(captchaRecord.getCaptcha()))  {
			String storedCaptcha = captchaRecord.getCaptcha();
			if (!captchaRecord.getExpireTime().after(new Date())) {
				loginResult.setCode(AuthResponseCode.USER_CAPTCHA_EXPARIED);
				loginResult.setCodeDesc(AuthResponseCode.USER_CAPTCHA_EXPARIED_DESC);
				return loginResult;
			}
			if (!storedCaptcha.equals(captcha)) {
				loginResult.setCode(AuthResponseCode.USER_CAPTCHA_ERROR);
				loginResult.setCodeDesc(AuthResponseCode.USER_CAPTCHA_ERROR_DESC);
				return loginResult;
			}
			loginResult = doRegister(mobile, password);
			return loginResult;
		} else {
			loginResult.setCode(AuthResponseCode.USER_CAPTCHA_ERROR);
			loginResult.setCodeDesc(AuthResponseCode.USER_CAPTCHA_ERROR_DESC);
			return loginResult;
		}
	}
	
	public LoginResult doRegister(String mobile, String password) {
		User user = new User();
		LoginResult loginResult = new LoginResult();
		boolean exist = existUsername(mobile);
		boolean registerSuccess = false;
		if (exist) {
			logger.info("This mobile exists! username={}", mobile);
			loginResult.setUserId(0L);
			loginResult.setToken("");
			loginResult.setUsername(mobile);
			loginResult.setCode(AuthResponseCode.USER_REPEAT);
			loginResult.setCodeDesc(AuthResponseCode.USER_REPEAT_DESC);
		} else {
			long userId = snowflakeIdWorker.nextId();
			String encodedPassword = MD5Util.md5(password).toLowerCase();
			user.setPassword(encodedPassword);
			boolean setSuccess = setUsername(mobile, AccountType.MOBILE, user);
			int insertedCount = 0;
			if (setSuccess) {
				user.setId(userId);
				user.setStatus(UserStatus.active);
				Date nowTime = new Date();
				user.setCreateTime(nowTime);
				user.setUpdateTime(nowTime);
				user.setMobile(mobile);
				insertedCount = userDAO.add(user);
				
				if (insertedCount == 1) {
					// 产生token并存入数据库
					String token = MD5Util.md5(new Date().getTime() + password).toLowerCase();
					Integer count = tokenService.insert(userId, token, TOKEN_EXPIRE);
					if (count > 0) {
						loginResult.setUserId(userId);
						loginResult.setToken(token);
						loginResult.setUsername(mobile);
						loginResult.setCode(AuthResponseCode.SUCCESS);
						loginResult.setCodeDesc(AuthResponseCode.SUCCESS_DESC);
						registerSuccess = true;
						userProfileService.addDefaultUser(userId, mobile);
					}
					
				} 
			} 
			if (!registerSuccess) {
				loginResult.setUserId(0L);
				loginResult.setToken("");
				loginResult.setUsername(mobile);
				loginResult.setCode(AuthResponseCode.USER_REGISTER_FAILED);
				loginResult.setCodeDesc(AuthResponseCode.USER_REGISTER_FAILED_DESC);
			}
		}
		
		return loginResult;
	}
	
	public LoginResult login(String username, String password, Integer accountType) {
		String encodedPassword = MD5Util.md5(password).toLowerCase();
		String passwordInDB = "";
		User user = new User();
		switch (accountType) {
		case AccountType.MOBILE :
			if (username.length() == 11 && username.charAt(0) == '1') {
				user = userDAO.getUserByMobile(username);
			}
			break;
		}
		// if (mobile == null) {
		// else {}

		passwordInDB = user.getPassword();
		Long userId = user.getId();
		int code = AuthResponseCode.USERCENTER_ERROR;
		String desc = AuthResponseCode.USERCENTER_ERROR_DESC;
		String token = "";
		if (encodedPassword.equals(passwordInDB)) {
			token = tokenService.copyTokenToCache(userId);
			code = AuthResponseCode.SUCCESS;
			desc = AuthResponseCode.SUCCESS_DESC;
		} else {
			code = AuthResponseCode.USER_PASSWORD_ERROR;
			desc = AuthResponseCode.USER_PASSWORD_ERROR_DESC;
		}
		LoginResult loginResult = new LoginResult();
		loginResult.setUserId(userId);
		loginResult.setToken(token);
		loginResult.setUsername(username);
		loginResult.setCode(code);
		loginResult.setCodeDesc(desc);
		return loginResult;
	}
	
	private boolean existUsername(String mobile) {
		if (mobile.length() == 11 && mobile.charAt(0) == '1') {
			User user = userDAO.getUserByMobile(mobile);
			if (user != null && user.getId() >0) {
				return true;
			}
		}
		return false;
	}
	
	private boolean setUsername(String username, Integer accountType, User user) {
		boolean success = false;
		switch (accountType) {
		case AccountType.MOBILE :
			if (username.length() == 11 && username.charAt(0) == '1') {
				user.setMobile(username);
				success = true;
			} else {
				logger.info("手机号不合法,mobile number is invalid,value={}", username);
			}
			break;
		}
		return success;
	}

	/**
	 * 更新password的时候同时更新token; token更新后，黑客手里掌握的userId+token无法进行进一步的操作
	 * 提高了系统的安全性
	 * @param mobile
	 * @param captcha
	 * @param password
	 * @return
	 */
	public LoginResult changePassword(String mobile, String captcha, String password) {
		LoginResult loginResult = new LoginResult();
		CaptchaRecord captchaRecord = captchaRecordService.findLatestByMobile(mobile);
		if (captchaRecord != null && !StringUtils.isEmpty(captchaRecord.getCaptcha()))  {
			String storedCaptcha = captchaRecord.getCaptcha();
			if (!captchaRecord.getExpireTime().after(new Date())) {
				loginResult.setCode(AuthResponseCode.USER_CAPTCHA_EXPARIED);
				loginResult.setCodeDesc(AuthResponseCode.USER_CAPTCHA_EXPARIED_DESC);
				return loginResult;
			}
			if (!storedCaptcha.equals(captcha)) {
				loginResult.setCode(AuthResponseCode.USER_CAPTCHA_ERROR);
				loginResult.setCodeDesc(AuthResponseCode.USER_CAPTCHA_ERROR_DESC);
				return loginResult;
			}
			loginResult = doChangePassword(mobile, password);
			return loginResult;
		} else {
			loginResult.setCode(AuthResponseCode.USER_CAPTCHA_ERROR);
			loginResult.setCodeDesc(AuthResponseCode.USER_CAPTCHA_ERROR_DESC);
			return loginResult;
		}
	}

	/**
	 * 做更新密码的操作. 更新password的时候同时更新token
	 * @param mobile
	 * @param password
	 * @return
	 */
	private LoginResult doChangePassword(String mobile, String password) {
		LoginResult loginResult = new LoginResult();
		User user= userDAO.getUserByMobile(mobile);
		if (user != null && user.getId()> 0) {
			long userId = user.getId();
			String encodedPassword = MD5Util.md5(password).toLowerCase();
			int updatedCount = userDAO.updatePassword(userId, encodedPassword);
			if (updatedCount == 1) {
				// 产生新的token并存入数据库和Redis中
				String token = MD5Util.md5(new Date().getTime() + password).toLowerCase();
				tokenService.updateToken(userId, token);

				loginResult.setUserId(userId);
				loginResult.setToken(token);
				loginResult.setUsername(mobile);
				loginResult.setCode(AuthResponseCode.SUCCESS);
				loginResult.setCodeDesc(AuthResponseCode.SUCCESS_DESC);
			} else {
				loginResult.setCode(AuthResponseCode.SYSTEM_EXCEPTION);
				loginResult.setCodeDesc(AuthResponseCode.SYSTEM_EXCEPTION_DESC);
			}
		} else {
			loginResult.setCode(AuthResponseCode.USER_CENTER_NOT_EXIST);
			loginResult.setCodeDesc(AuthResponseCode.USER_CENTER_NOT_EXIST_DESC);
		}
		return loginResult;
	}
}
