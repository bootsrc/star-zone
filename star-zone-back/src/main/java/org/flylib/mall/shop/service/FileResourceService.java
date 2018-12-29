package org.flylib.mall.shop.service;

import org.flylib.mall.shop.entity.jpa.FileResource;
import org.flylib.mall.shop.repository.jpa.FileResourceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileResourceService {
    @Resource
    private FileResourceRepository fileResourceRepository;

    public void insert(FileResource upfile) {
        fileResourceRepository.save(upfile);
    }
}
