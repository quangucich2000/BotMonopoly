package com.gmm.bot.controller;

import com.gmm.bot.base.BotBase;
import com.gmm.bot.run.BotSchedule;
import com.gmm.bot.run.StartBotForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BotController {

    private final BotSchedule botSchedule;
    private final String pathRedirectToIndex;

    public BotController(BotSchedule botSchedule) {
        this.botSchedule = botSchedule;
        pathRedirectToIndex = "redirect:/index";
    }

    @GetMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("botsSize",botSchedule.getBots().size());
        model.addAttribute("startBotForm", new StartBotForm());
        model.addAttribute("botsConnect",botSchedule.countConnect());
        return "index";
    }

    @PostMapping("/start-bot")
    public String startBot(@ModelAttribute StartBotForm startBotForm, RedirectAttributes redirectAttributes){
        System.out.println("+++++++++++CONTROLLER+++++++++++++");
        System.out.println("++++++++++++Start bot++++++++++++");
        startBotForm = new StartBotForm(2,1);
        String message = botSchedule.prepareScheduleCreateBot(startBotForm);
        redirectAttributes.addFlashAttribute("message",message);
        return pathRedirectToIndex;
    }

    @PostMapping("/create-one-bot")
    public String createOneBot(RedirectAttributes redirectAttributes){
        String message = botSchedule.createOneBot();
        redirectAttributes.addFlashAttribute("message",message);
        return pathRedirectToIndex;
    }

    @PostMapping("/stop-bot")
    public String stopBot(RedirectAttributes redirectAttributes){
        String message = botSchedule.stopCreateBot();
        redirectAttributes.addFlashAttribute("message",message);
        return pathRedirectToIndex;
    }

    @PostMapping("/disconnect-bot")
    public String disconnectBot(RedirectAttributes redirectAttributes){
        String message = botSchedule.disconnectBots();
        redirectAttributes.addFlashAttribute("message",message);
        return pathRedirectToIndex;
    }

    @PostMapping("/disconnect-one-bot")
    public String disconnectOneBot(RedirectAttributes redirectAttributes){
        String message = botSchedule.disconnectOneBot();
        redirectAttributes.addFlashAttribute("message",message);
        return pathRedirectToIndex;
    }

    @PostMapping("/reconnect-bot")
    public String reconnectBot(RedirectAttributes redirectAttributes){
        String message = botSchedule.reconnectBots();
        redirectAttributes.addFlashAttribute("message",message);
        return pathRedirectToIndex;
    }
}
