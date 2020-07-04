package io.tanghuibo.github.springmultiplemybatis.mapper;

import io.tanghuibo.github.springmultiplemybatis.mapper.one.OneMapper;
import io.tanghuibo.github.springmultiplemybatis.mapper.three.ThreeMapper;
import io.tanghuibo.github.springmultiplemybatis.mapper.two.TwoMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author tanghuibo
 * @date 2020/7/4下午11:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Resource
    OneMapper oneMapper;

    @Resource
    TwoMapper twoMapper;

    @Resource
    ThreeMapper threeMapper;


    @Test
    public void testSelect() {
        String oneResult = oneMapper.selectTest();
        String twoResult = twoMapper.selectTest();
        String threeResult = threeMapper.selectTest();

        Assert.assertEquals(oneResult, "one");
        Assert.assertEquals(twoResult, "two");
        Assert.assertEquals(threeResult, "three");
    }
}
