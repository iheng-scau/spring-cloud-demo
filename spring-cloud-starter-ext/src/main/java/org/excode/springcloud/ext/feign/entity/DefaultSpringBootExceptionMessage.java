package org.excode.springcloud.ext.feign.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author iheng
 * @date 3/22/18
 */
@Data
public class DefaultSpringBootExceptionMessage {
    @JsonProperty("exception")
    private String exceptionType;
}
