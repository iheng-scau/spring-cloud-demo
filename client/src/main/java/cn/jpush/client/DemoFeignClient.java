package cn.jpush.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider")
@RequestMapping("/demo")
public interface DemoFeignClient {
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    int add(@RequestParam(name = "a") int a, @RequestParam(name = "b") int b);
}
