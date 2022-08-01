package vttp2022.ssf.day15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = {"/",""})
public class GreetingsController {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public String getGreetings(Model model){
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        Object greetings = ops.get("greetings");
        model.addAttribute("greetings",greetings.toString());
        return "index";
    }

    @PostMapping
    public String postGreetings(
            @RequestParam String newGreetings,
            Model model) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set("greetings", newGreetings);
        model.addAttribute("greetings", newGreetings);
        return "index";
    }

}
