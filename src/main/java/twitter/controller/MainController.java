package twitter.controller;

import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import twitter.domain.Message;
import twitter.domain.User;
import twitter.repos.Messagerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import twitter.weather.Weather;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MainController {
    @Autowired
    private Messagerepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ) {
        Message message = new Message(text, tag, user);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }





    @GetMapping("/parser")
    public String parser() {
        return "parser";
    }

    @PostMapping("/parser")
    public String getweather(@RequestParam String city, Model model){
        if(city==""){
            city="Helsinki";
        }
        String showcity = city;
        Weather a = new Weather();
        String jsonString = a.getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=fa11a742a4d2e48ad88945dcea73facb&units=metric");
        JSONObject obj = new JSONObject(jsonString);
        double tempd= obj.getJSONObject("main").getDouble("temp");
        double temp_mind = obj.getJSONObject("main").getDouble("temp_min");
        double temp_maxd = obj.getJSONObject("main").getDouble("temp_max");
        double feelsd = obj.getJSONObject("main").getDouble("feels_like");
        double humidity = obj.getJSONObject("main").getDouble("humidity");

//        double tempd  = Double.parseDouble(temp);
        int tempi= (int) Math. round(tempd);

//        double temp_mind  = Double.parseDouble(temp_min);
        int temp_mini= (int) Math. round(temp_mind);

//        double temp_maxd  = Double.parseDouble(temp_max);
        int temp_maxi= (int) Math. round(temp_maxd);

//        double feelsd = Double.parseDouble(feels);
        int feelsi= (int) Math. round(feelsd);

        String[] elements = jsonString.split("\"");


        model.addAttribute("temp", tempi);
        model.addAttribute("temp_min", temp_mini);
        model.addAttribute("temp_max", temp_maxi);
        model.addAttribute("feels", feelsi);
        model.addAttribute("humidity", humidity);
        model.addAttribute("cityshow", elements[elements.length-4]);
        return "parser";

    }



    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ) {
        Set<Message> messages = user.getMessages();

        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag
    ) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }

            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;
    }

    @GetMapping("/del-user-messages/{user}")


    public String deleteMessage(
            @PathVariable Long user,
            @RequestParam("message") Long messageId
    ) throws IOException {

        messageRepo.deleteById(messageId);

        return "redirect:/user-messages/" + user;
    }




    @GetMapping("/del-messages/")


    public String deleteMessages(
            @RequestParam("message") Long messageId
    ) throws IOException {

        messageRepo.deleteById(messageId);

        return "redirect:/main";
    }








}