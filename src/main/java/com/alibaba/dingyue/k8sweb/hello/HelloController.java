package com.alibaba.dingyue.k8sweb.hello;

import com.alibaba.dingyue.k8sweb.data.Student;
import com.alibaba.dingyue.k8sweb.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private StudentRepo studentRepo;

    @Value("${env}")
    private String env = "app_default";

    @RequestMapping("/")
    public String index(){
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
