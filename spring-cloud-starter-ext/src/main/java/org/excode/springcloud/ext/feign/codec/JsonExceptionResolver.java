package org.excode.springcloud.ext.feign.codec;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.excode.springcloud.ext.feign.entity.DefaultSpringBootExceptionMessage;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static feign.Util.UTF_8;

/**
 * @author iheng
 * @date 3/21/18
 */
@Slf4j
public class JsonExceptionResolver extends HttpExceptionResolver {
    public final static String SUPPORT_MEDIA_TYPE = "application/json";
    private ObjectMapper objectMapper;

    public JsonExceptionResolver() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    Exception resolve(InputStream in) {
        InputStreamReader reader = new InputStreamReader(in, UTF_8);

        String body;
        String exceptionType;
        try {
            body = Util.toString(reader);
            DefaultSpringBootExceptionMessage message = objectMapper.readValue(body, DefaultSpringBootExceptionMessage.class);
            exceptionType = message.getExceptionType();
            // 返回路径中未指定异常路径
            if (StringUtils.isEmpty(exceptionType)) {

            }
            Class<?> clazz = Class.forName(exceptionType);
            return (Exception) clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException e) {
            log.error("can not generate exception from the response,e:{}", e.getMessage());
            e.printStackTrace();
            return e;
        }
    }

    @Override
    Boolean isMatch(String mediaType) {
        return SUPPORT_MEDIA_TYPE.equals(mediaType);
    }
}
