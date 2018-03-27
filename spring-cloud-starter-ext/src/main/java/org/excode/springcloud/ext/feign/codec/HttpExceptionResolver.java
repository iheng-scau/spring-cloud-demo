package org.excode.springcloud.ext.feign.codec;

import feign.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author iheng
 * @date 3/22/18
 */
@Slf4j
public abstract class HttpExceptionResolver {
    abstract Exception resolve(InputStream in);

    abstract Boolean isMatch(String mediaType);

    protected Exception doResolve(Response response){
        InputStream in= null;
        try {
            in = response.body().asInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resolve(in);
    }
}
