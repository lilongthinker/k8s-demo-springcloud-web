package com.alibaba.dingyue.k8sweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@SpringBootApplication
@Slf4j
public class K8sWebApplication  {

	public static void main(String[] args) {
		DebugSignalHandler.listenTo("HUP");
		DebugSignalHandler.listenTo("INT");
//        DebugSignalHandler.listenTo("KILL");
		DebugSignalHandler.listenTo("TERM");

		SpringApplication.run(K8sWebApplication.class, args);
	}


}

@Slf4j
class DebugSignalHandler implements SignalHandler {
	public static void listenTo(String name) {
		Signal signal = new Signal(name);
		Signal.handle(signal, new DebugSignalHandler());
	}

	public void handle(Signal signal) {
		System.out.println("Signal: " + signal);
		if (signal.toString().trim().equals("SIGTERM")) {
			// 该信号会在container的prestop执行后，发送给容器内的1号进程
			System.out.println("SIGTERM raised, terminating...");
			log.info("SIGTERM raised, terminating...");
			try {
				Thread.currentThread().sleep(1000*10L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
	}
}