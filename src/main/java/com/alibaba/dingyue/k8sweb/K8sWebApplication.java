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


class DebugSignalHandler implements SignalHandler {
	public static void listenTo(String name) {
		Signal signal = new Signal(name);
		Signal.handle(signal, new DebugSignalHandler());
	}

	public void handle(Signal signal) {
		System.out.println("Signal: " + signal);
		if (signal.toString().trim().equals("SIGTERM")) {
			System.out.println("SIGTERM raised, terminating...");
			System.exit(1);
		}
	}
}