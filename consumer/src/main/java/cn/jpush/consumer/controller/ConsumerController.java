package cn.jpush.consumer.controller;

import cn.jpush.client.DemoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    DemoFeignClient demoFeignClient;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public int add(int a, int b) {
        return demoFeignClient.add(a, b);
    }

}
