package org.excode.springcloud.ext.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author iheng
 * @date 3/21/18
 */
@Slf4j
public class EnhancedFeignRequestInterceptor implements RequestInterceptor {
    @Value("${feign.client.accept:application/json}")
    private String accept;

    @Override
    public void apply(RequestTemplate template) {
        Collection<String> templateAccept = template.headers().get("Accept");
        if(templateAccept==null){
            templateAccept=new ArrayList<>();
        }

        if(!templateAccept.contains(accept)){
            templateAccept.clear();
            templateAccept.add(accept);
            template.header("Accept", templateAccept);
        }
    }
}
