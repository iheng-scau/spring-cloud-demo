package org.excode.springcloud.ext.feign.codec;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author iheng
 * @date 3/21/18
 */
public class HttpExceptionResolverCache {
    private static Map<String,HttpExceptionResolver> cache=new ConcurrentHashMap<>();

    /**
     * add the resolvers that provided by default into the cache
     */
    static {
        cache.put(HessianExceptionResolver.SUPPORT_MEDIA_TYPE, new HessianExceptionResolver());
        cache.put(JsonExceptionResolver.SUPPORT_MEDIA_TYPE, new JsonExceptionResolver());
    }

    public static HttpExceptionResolver get(String mediaType){
        return cache.get(mediaType);
    }

    public static void put(String mediaType, HttpExceptionResolver resolver){
        cache.put(mediaType, resolver);
    }
}
