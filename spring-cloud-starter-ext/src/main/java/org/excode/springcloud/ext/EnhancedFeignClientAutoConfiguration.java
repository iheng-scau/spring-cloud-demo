package org.excode.springcloud.ext;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.excode.springcloud.ext.feign.aop.MethodThrowingAdvice;
import org.excode.springcloud.ext.feign.codec.EnhancedErrorDecoder;
import org.excode.springcloud.ext.feign.interceptor.EnhancedFeignRequestInterceptor;
import org.excode.springcloud.ext.web.GlobalExceptionAdvice;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author iheng
 * @date 3/21/18
 */
@Slf4j
@Configuration
public class EnhancedFeignClientAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ErrorDecoder errorDecoder(){
        return new EnhancedErrorDecoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestInterceptor requestInterceptor(){
        return new EnhancedFeignRequestInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "eureka.provider", havingValue = "true")
    public GlobalExceptionAdvice globalExceptionAdvice(){
        return new GlobalExceptionAdvice();
    }

    @Bean
    @Qualifier("methodThrowingAdvice")
    @ConditionalOnMissingBean
    public MethodThrowingAdvice methodThrowingAdvice(){
        return new MethodThrowingAdvice();
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator=new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("*FeignClient");
        beanNameAutoProxyCreator.setInterceptorNames("methodThrowingAdvice");
        return beanNameAutoProxyCreator;
    }

/*    @Bean
    public MethodAdviceMatcherPointcutAdvisor methodAdviceMatcherPointcutAdvisor(){
        //return new MethodAdviceMatcherPointcutAdvisor(new MethodThrowingAdvice());
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }*/
}
