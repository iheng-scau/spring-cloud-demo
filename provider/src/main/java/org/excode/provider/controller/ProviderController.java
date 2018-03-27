package org.excode.provider.controller;

import com.google.common.collect.ImmutableMap;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
@RequestMapping("/demo")
public class ProviderController {
    @Autowired
    EurekaInstanceConfig eurekaInstanceConfig;
    @Autowired
    EurekaClient eurekaClient;


    String eurekaServer = "http://localhost:8000/eureka";

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public int add(@RequestParam(name = "a") int a, @RequestParam(name = "b") int b) {
        log.info("a:{},b:{}", a, b);
        int i=1/0;
        return (a + b);
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    @ResponseBody
    public Object getRegisterService() {
        String instanceId = eurekaInstanceConfig.getInstanceId();
        String appName = eurekaInstanceConfig.getAppname();
        return ImmutableMap.of("appName", appName, "InstanceId", instanceId);
    }

    @RequestMapping(value = "/apps", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllApplication() {
        return eurekaClient.getApplications();
    }

    @RequestMapping(value = "/unregister", method = RequestMethod.GET)
    @ResponseBody
    public Object unregister() {
        eurekaClient.shutdown();
        return "success";
    }
}
