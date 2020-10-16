package com.alibaba.dingyue.k8sweb.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Signal;
import sun.misc.SignalHandler;


@RestController
public class SigAware implements SignalHandler {
    @Override
    public void handle(Signal signal) {
        System.out.println(signal.getName() + "is recevied.");
    }

    @RequestMapping("/signal")
    public String signal(@RequestParam String name){
        return "signal controller is ok!!!\n";
    }
}
