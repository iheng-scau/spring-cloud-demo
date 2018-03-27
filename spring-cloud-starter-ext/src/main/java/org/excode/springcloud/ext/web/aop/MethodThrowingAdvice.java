package org.excode.springcloud.ext.web.aop;

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
    public void afterThrowing(Exception e){
        log.error("exception caught");
    }
}
