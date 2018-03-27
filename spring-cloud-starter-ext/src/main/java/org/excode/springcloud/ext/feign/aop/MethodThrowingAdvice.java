package org.excode.springcloud.ext.feign.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author iheng
 * @date 3/26/18
 */
@Slf4j
public class MethodThrowingAdvice implements ThrowsAdvice{
    @AfterThrowing
    public void afterThrowing(Exception e) throws Exception {
        log.error("feign client throws exception, e:{}",e.getMessage());
        throw e;
    }
}
