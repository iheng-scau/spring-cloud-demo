package org.excode.springcloud.ext.web;

import com.caucho.hessian.io.HessianOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iheng
 * @date 3/21/18
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * 捕获全局未捕获的异常
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     */
    @ExceptionHandler({Exception.class, Error.class})
    public void handleUncaughtException(HttpServletRequest request, HttpServletResponse response, Throwable e) throws IOException {
        log.error("uncaught exception found, e:{}", e.getMessage());
        String accept = request.getHeader("Accept");
        /**
         *  根据请求的header/accept值
         */
        response.setStatus(500);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        if (accept.contains("application/x-hessian")) {
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(e);
            response.setHeader("Content-Type", "application/x-hessian");
            response.getOutputStream().write(os.toByteArray());
        }
        if (accept.contains("application/json")) {
            Map<String, Object> body = new HashMap<>();
            body.put("code", 5000);
            body.put("message", e.getMessage());
            ObjectMapper mapper = new ObjectMapper();
            String content = mapper.writeValueAsString(body);
            response.setHeader("Content-Type", "application/json");
            response.getWriter().write(content);
        }
    }
}
