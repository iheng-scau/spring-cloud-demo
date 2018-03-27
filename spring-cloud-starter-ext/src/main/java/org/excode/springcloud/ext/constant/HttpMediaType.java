package org.excode.springcloud.ext.constant;

/**
 * @author iheng
 * @date 3/26/18
 */
public enum HttpMediaType {
    APPLICATION_JSON("application/json"),
    APPLICATION_HESSIAN("application/x-hessian"),
    UNKNOWN_CONTENT_TYPE("unknown");

    private String value;

    HttpMediaType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
