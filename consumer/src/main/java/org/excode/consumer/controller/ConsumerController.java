package org.excode.consumer.controller;

import org.excode.client.DemoFeignClient;
import org.excode.consumer.dao.DemoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    DemoFeignClient demoFeignClient;
    @Autowired
    DemoDao demoDao;

    @GetMapping(value = "/add")
    @ResponseBody
    public int add(int a, int b) {
        log.info("a:{},b:{}", a, b);
        return demoFeignClient.add(a, b);
    }
}
