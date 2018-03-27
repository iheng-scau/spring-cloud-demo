package org.excode.springcloud.ext.web.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author iheng
 * @date 3/26/18
 */
public class MethodAdviceMatcherPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

    public MethodAdviceMatcherPointcutAdvisor(Advice advice) {
        super(advice);
    }

    /**
     * 筛选使用指定注解(@RequestMapping)的方法,即接口方法
     * 筛选@RequestMapping注解实际上包含@GetMapping,@PostMapping等注解
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Annotation annotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (annotation != null) {
            return true;
        }
        return false;
    }

    /**
     * 筛选接口类,即@Controller类
     * @return
     */
    @Override
    public ClassFilter getClassFilter() {
        return clazz -> AnnotationUtils.isAnnotationInherited(Controller.class, clazz);
    }
}
