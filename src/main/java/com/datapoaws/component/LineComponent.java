package com.datapoaws.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.datapoaws.service.LineService;

@Component
public class LineComponent {

	@Autowired
	private LineService lineService;

	public void getLines() {
		new Thread() {
			@Override
			public void run() {
				System.out.println("********** Atualizando Lines **********");
				lineService.getLineWs();
			}
		}.start();
	}

	@Scheduled(fixedDelay = 1000 * 3600 * 24)
	public void executar() {
		getLines();
	}
	
}
