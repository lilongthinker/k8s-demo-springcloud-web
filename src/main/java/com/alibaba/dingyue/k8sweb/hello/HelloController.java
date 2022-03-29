package com.alibaba.dingyue.k8sweb.hello;

import com.alibaba.dingyue.k8sweb.data.Student;
import com.alibaba.dingyue.k8sweb.repo.StudentRepo;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private StudentRepo studentRepo;

    @Value("${env}")
    private String env = "app_default";

    @Value("${msg}")
    private String msg = "java message";

    @RequestMapping("/")
    public String index(){
//        //no 生产环境禁止systemout
//        System.out.println("hello. log to stdout");
        //打印日志到文件,同时到标准输出
        for(int i =0;i<10;i++){
            log.info("hello.log to file. info");
	}
        log.info("hello.log to file. info");

        //设计异常信息,方便在arms里观察
        Long now = System.currentTimeMillis();
        if(now%10 == 0){
            throw new RuntimeException("10 nanos exception. this is by designed");
        }
        log.info("greeting from spring cloud. message in env:"+env);
        return "greeting from spring cloud. message in env:"+env+"\n";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam String name){
        return "hello! " + name + " ! this is from "+msg+"\n";
    }

    @RequestMapping("/student")
    public List<Student> list() {
        log.info("url:student. called in controller");
        return studentRepo.findAll();
    }

    @RequestMapping("/headers")
    public String headers(@RequestHeader HttpHeaders headers){
        StringBuilder sbuilder = new StringBuilder();
        for(Map.Entry<String,List<String>> entry : headers.entrySet()){
            sbuilder.append(entry.getKey()).append(":").append( Joiner.on(",").join(entry.getValue())).append("---------------\n<br/>");
        }
        return sbuilder.toString();
    }

    @RequestMapping("/path/var/{id}")
    public String pathVariable(@PathVariable("id") Integer id){
        String msg = "path varibale. id=!\n" + id;
        log.info(msg);
        return msg;
    }

    private Map<Long, Byte[]> leakData = new HashMap<>();

    @RequestMapping("/leak")
    public String leak(){
        long current = System.nanoTime();
         Byte[] value = new Byte[1024];
        leakData.put(current, value);
        log.info("leaking");
        return msg;
    }


    @RequestMapping("/readiness/check")
    public String readinessCheck(){
        String msg = "readiness check it success!\n";
        log.info(msg);
        return msg;
    }

    @RequestMapping("/liveness/check")
    public String livenessCheck(){
        String msg = "liveness check it success!\n";
        log.info(msg);
        return msg;
    }
}
