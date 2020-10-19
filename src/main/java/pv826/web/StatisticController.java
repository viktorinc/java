package pv826.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticController {
    @GetMapping("/statistic")
    public String home()
    {
      return "statistic";
    }
}
