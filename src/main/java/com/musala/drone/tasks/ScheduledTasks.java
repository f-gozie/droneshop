package com.musala.drone.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.musala.drone.services.BatteryAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final BatteryAuditService batteryAuditService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(BatteryAuditService batteryAuditService) {
        this.batteryAuditService = batteryAuditService;
    }
    
    @Scheduled(cron = "0 */1 * * * *")
    public void checkBatteryLevels() {
        log.info("Checking battery levels");
        batteryAuditService.createBatteryAudit();
    }
}
