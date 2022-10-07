package hu.nye.dms.dms.controller;

import hu.nye.dms.dms.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * Controller class.
 */
@Controller
public class DmsController {

  @GetMapping("/dms/registration")
  public String registration() {
    return "/dms/registration";
  }


}
