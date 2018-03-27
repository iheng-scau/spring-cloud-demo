package org.excode.springcloud.ext.feign.exception;

import lombok.Data;

/**
 * @author iheng
 * @date 3/26/18
 */
@Data
public class WrappedException extends Exception{
    private Throwable exception;
}
