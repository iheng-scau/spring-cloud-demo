package org.excode.springcloud.ext.feign.codec;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.excode.springcloud.ext.constant.HttpMediaType;

import java.util.Collection;

/**
 * @author iheng
 * @date 3/20/18
 */
@Slf4j
public class EnhancedErrorDecoder implements ErrorDecoder {

    /**
     *
     * @param methodKey
     * @param response
     * @return
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        /**
         * 根据返回的content-type选择resolver
         */
        Collection<String> contentType = response.headers().get("Content-Type");
        HttpExceptionResolver resolver = null;
        for (String type : contentType) {
            type = getContentType(type);
            resolver = HttpExceptionResolverCache.get(type);
            if (resolver != null)
                break;
        }
        /**
         * 如果提供的resolver没有合适的,则使用默认的error decoder
         */
        if (resolver == null) {
            log.info("can not find corresponding resolver for the content type:{}, use the default error decode instead.");
            return new ErrorDecoder.Default().decode(methodKey, response);
        }
        return resolver.doResolve(response);
    }

    /**
     *
     * @param type
     * @return
     */
    private String getContentType(String type) {
        if (type.contains(HttpMediaType.APPLICATION_JSON.getValue()))
            return HttpMediaType.APPLICATION_JSON.getValue();
        if (type.contains(HttpMediaType.APPLICATION_HESSIAN.getValue()))
            return HttpMediaType.APPLICATION_HESSIAN.getValue();
        return HttpMediaType.UNKNOWN_CONTENT_TYPE.getValue();
    }
}
