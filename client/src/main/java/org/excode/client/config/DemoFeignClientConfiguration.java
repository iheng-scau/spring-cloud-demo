package org.excode.client.config;

import feign.codec.ErrorDecoder;
import org.excode.springcloud.ext.feign.codec.EnhancedErrorDecoder;
import org.springframework.context.annotation.Configuration;

/**
 * @author iheng
 * @date 3/20/18
 */
@Configuration
public class DemoFeignClientConfiguration {
    public ErrorDecoder errorDecoder(){
        return new EnhancedErrorDecoder();
    }
}
