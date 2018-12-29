package org.flylib.mall.shop.service;

import org.flylib.mall.common.util.SnowflakeIdWorker;
import org.flylib.mall.shop.entity.AccountData;
import org.flylib.mall.shop.repository.AccountDataRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AccountDataService {
    @Resource
    private AccountDataRepository accountDataRepository;

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    public void save(long userId, String miRegid) {
        AccountData existAccountData = accountDataRepository.findByUserId(userId);

        if (existAccountData == null){
            AccountData accountData = new AccountData();
            long id = snowflakeIdWorker.nextId();
            accountData.setId(id);
            accountData.setUserId(userId);
            accountData.setMiRegid(miRegid);
            Date nowTime = new Date();
            accountData.setCreateTime(nowTime.getTime());
            accountData.setUpdateTime(nowTime.getTime());
            accountDataRepository.add(accountData);
        } else {
            Date nowTime = new Date();
            existAccountData.setMiRegid(miRegid);
            existAccountData.setUpdateTime(nowTime.getTime());
            accountDataRepository.update(existAccountData);
        }
    }

    public AccountData findByUserId(long userId) {
        return accountDataRepository.findByUserId(userId);
    }
}
