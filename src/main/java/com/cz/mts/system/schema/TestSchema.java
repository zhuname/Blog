package com.cz.mts.system.schema;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import com.cz.mts.frame.common.BaseLogger;

@Component
public class TestSchema extends BaseLogger{

	
	@Scheduled(cron="00 33 19 * * ?")
	public void creat() throws Exception{
		logger.info("***********************定时任务************8");
	}
}
