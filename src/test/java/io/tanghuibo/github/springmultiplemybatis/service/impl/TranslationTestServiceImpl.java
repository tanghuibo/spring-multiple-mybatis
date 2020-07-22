package io.tanghuibo.github.springmultiplemybatis.service.impl;

import io.tanghuibo.github.springmultiplemybatis.mapper.one.OneMapper;
import io.tanghuibo.github.springmultiplemybatis.mapper.three.ThreeMapper;
import io.tanghuibo.github.springmultiplemybatis.mapper.two.TwoMapper;
import io.tanghuibo.github.springmultiplemybatis.service.TranslationTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author tanghuibo
 * @date 2020/7/22下午11:04
 */
@Service
public class TranslationTestServiceImpl implements TranslationTestService {

    @Resource
    OneMapper oneMapper;

    @Resource
    TwoMapper twoMapper;

    @Resource
    ThreeMapper threeMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void translateTest(boolean hasError) {

        oneMapper.insertTest();
        twoMapper.insertTest();
        threeMapper.insertTest();
        if(hasError) {
            throw new RuntimeException("rollback database");
        }
    }
}
