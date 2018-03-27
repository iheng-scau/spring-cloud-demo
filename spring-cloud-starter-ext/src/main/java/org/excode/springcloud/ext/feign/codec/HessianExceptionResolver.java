package org.excode.springcloud.ext.feign.codec;

import com.caucho.hessian.io.HessianInput;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author iheng
 * @date 3/22/18
 */
@Slf4j
public class HessianExceptionResolver extends HttpExceptionResolver{
    public final static String SUPPORT_MEDIA_TYPE="application/x-hessian";

    @Override
    Exception resolve(InputStream in) {
        HessianInput hi=new HessianInput(in);
        try {
            Exception e = (Exception) hi.readObject();
            return e;
        }catch (IOException e){
            log.error("hessian deserialization failed,e:{}",e.getMessage());
            return e;
        }
    }

    @Override
    Boolean isMatch(String mediaType) {
        return SUPPORT_MEDIA_TYPE.equals(mediaType);
    }
}
