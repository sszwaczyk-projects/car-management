package pl.sszwaczyk.carmanagement.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sszwaczyk.carmanagement.application.config.url.HomeURLs;

@Controller
public class HomeController {

    @RequestMapping(value = HomeURLs.HOME)
    public String home() {
        return HomeURLs.HOME;
    }
}
