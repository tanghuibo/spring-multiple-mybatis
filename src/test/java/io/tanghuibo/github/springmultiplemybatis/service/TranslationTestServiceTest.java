package io.tanghuibo.github.springmultiplemybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author tanghuibo
 * @date 2020/7/22下午11:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TranslationTestServiceTest {

    @Resource
    TranslationTestService translationTestService;



    /**
     * 事物测试
     */
    @Test
    public void testTranslateTest() {
        try {
            translationTestService.translateTest(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
