package ru.neoflex.sender.Quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import ru.neoflex.sender.Parser.CsvParser;
import ru.neoflex.sender.Service.MailScheduler;

import java.text.ParseException;

public class QuartzJob{
    @Autowired
    private MailScheduler mailScheduler;
    @Autowired
    private CsvParser csvParser;

    @Scheduled(cron = "0 0 0/12")
    public void setDateForScheduled() throws SchedulerException {
        String day = csvParser.getDate();
        if(!day.equals(null)) {
            StringBuilder cron = new StringBuilder("0 0 17 * * " + day);
            task(cron.toString());
        }
    }

    public void task(String cron) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        try {
            scheduler = schedulerFactory.getScheduler();
            JobDetail details = JobBuilder.newJob(MailScheduler.class)
                    .withDescription("Message send")
                    .withIdentity("Send", "myGroup")
                    .storeDurably(true).build();
            CronTriggerImpl trigger = new CronTriggerImpl();
            trigger.setName("T1");
            try {
                trigger.setCronExpression(String.valueOf(cron));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            trigger.setDescription("desc");
            scheduler.scheduleJob(details, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
