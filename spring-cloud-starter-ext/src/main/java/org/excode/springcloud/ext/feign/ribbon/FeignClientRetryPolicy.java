package org.excode.springcloud.ext.feign.ribbon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.ServiceInstanceChooser;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancedRetryPolicy;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerContext;

/**
 * @author iheng
 * @date 3/27/18
 */
@Slf4j
public class FeignClientRetryPolicy extends RibbonLoadBalancedRetryPolicy{

    public FeignClientRetryPolicy(String serviceId, RibbonLoadBalancerContext context,
                                  ServiceInstanceChooser loadBalanceChooser) {
        super(serviceId, context, loadBalanceChooser);
    }
}
