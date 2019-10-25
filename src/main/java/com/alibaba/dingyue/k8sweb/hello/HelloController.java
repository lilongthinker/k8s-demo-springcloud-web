package com.alibaba.dingyue.k8sweb.hello;

import com.alibaba.dingyue.k8sweb.data.Student;
import com.alibaba.dingyue.k8sweb.repo.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private StudentRepo studentRepo;

    @Value("${env}")
    private String env = "app_default";

    @RequestMapping("/")
    public String index(){
//        //no 生产环境禁止systemout
//        System.out.println("hello. log to stdout");
        //打印日志到文件,同时到标准输出
        log.info("hello.log to file. info");

        //设计异常信息,方便在arms里观察
        Long now = System.currentTimeMillis();
        if(now%10 == 0){
            throw new RuntimeException("10 nanos exception. this is by designed");
        }

        return "greeting from spring cloud. message in env:"+env;
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam String name){
        return "hello! " + name + " !";
    }

    @RequestMapping("/student")
    public List<Student> list() {
        return studentRepo.findAll();
    }
}
