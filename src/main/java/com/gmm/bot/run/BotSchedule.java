package com.gmm.bot.run;

import com.gmm.bot.base.BotBase;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@Getter
public class BotSchedule {
    @Autowired
    private ObjectFactory<BotBase> botObjectFactory;
    private BotBase bot;
    private ThreadPoolTaskScheduler taskScheduler;
    private List<BotBase> bots;
    private ScheduledFuture<?> scheduledTask;
    private boolean executeCreateBot;
    private int CCU;

    public BotSchedule(ThreadPoolTaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
        this.bots = new Vector<>();
    }

    public String prepareScheduleCreateBot(StartBotForm data) {
        if(scheduledTask != null){
            return "Process schedule create bot is running!";
        }
        scheduledTask = taskScheduler.scheduleAtFixedRate(new RunBot(),data.getDelayStartBot()* 1000L);
        executeCreateBot = true;
        CCU = data.getBotsSize();
        log.info("Schedule create bot| size bot: "+CCU +", delay to create bot:"+data.getDelayStartBot());
        System.out.println("Schedule create bot| size bot: "+CCU +", delay to create bot:"+data.getDelayStartBot());
        return "Schedule create bot is created successfully";
    }

    public String createOneBot(){
        runBot();
        return "Start bot "+ bot.getUsername()+" successfully";
    }

    public String stopCreateBot(){
        if(scheduledTask != null){
            scheduledTask.cancel(true);
            scheduledTask = null;
            executeCreateBot = false;
            CCU = 0;
            log("Stop schedule create bot successfully");
            return "Stop schedule create bot successfully";
        }
        return "Nothing to stop";
    }

    public String disconnectBots(){
        bots.forEach(BotBase::disconnect);
        log("All bot have been disconnected!");
        return "All bot have been disconnected!";
    }

    public String disconnectOneBot() {
        if (bots.isEmpty()) {
            return "No any bot to disconnect!";
        }
        BotBase botBase;
        do {
            int i = ThreadLocalRandom.current().nextInt(bots.size());
            botBase = bots.get(i);
        } while (botBase.isDisconnect());
        botBase.disconnect();
        return botBase.getUsername() + "have been disconnect";
    }

    public String reconnectBots(){
        bots.forEach((botBase -> {if(botBase.isDisconnect()) botBase.connectSmartfox();}));
        log("All bot have been reconnected!");
        return "All bot have been reconnected!";
    }

    private class RunBot implements Runnable{
        @Override
        public void run() {
            runBot();
        }
    }

    private void runBot() {
        getBotBaseInstance();
        bot.connectSmartfox();
        bots.add(bot);
        log("Start bot "+ bot.getUsername()+" successfully, size: "+bots.size());
    }

    private void getBotBaseInstance() {
        bot =  botObjectFactory.getObject();
    }

    @Scheduled(cron = "* * * ? * *")
    private void scheduleCronTask() {
       if(CCU == 0){
           return;
       }
       if(bots.size() >= CCU){
           log("Stop schedule create bot successfully by schedule");
           stopCreateBot();
       }
    }

    public Long countConnect(){
        return bots.stream().filter(BotBase::isConnectSmartFox).count();
    }

    private void log(String message){
        log.info(message);
    }


}
