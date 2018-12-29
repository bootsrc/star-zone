package org.flylib.mall.shop.service;

import org.flylib.mall.shop.entity.CaptchaRecord;
import org.flylib.mall.shop.entity.User;
import org.flylib.mall.shop.repository.UserDAO;
import org.flylib.mall.shop.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User findById(long id) {
        return userDAO.findById(id);
    }
}
