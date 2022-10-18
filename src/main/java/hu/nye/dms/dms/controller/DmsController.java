package hu.nye.dms.dms.controller;

import hu.nye.dms.dms.model.User;
import hu.nye.dms.dms.repository.DmsRepo;
import hu.nye.dms.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class.
 */
@Controller
public class DmsController {

  @Autowired
  DmsRepo dmsRepo;

//  @Autowired
//  private UserService userService;
//
//  @RequestMapping(path = "/list", method = RequestMethod.GET)
//  public String getAllUser(final Model model)
//  {
//    final List<User> users = userService.getAllUsers();
//    model.addAttribute("users", users);
//    return "dms/registration";
//  }


//  @RequestMapping(path = "/users", method = RequestMethod.GET)
//  public String getUsers(final Model model, @Param("user") String user, @Param("pass") String pass) {
//    final String users = dmsRepo.getUsers(user, pass);
//    model.addAttribute("users", users);
//    return "dms/registration";
//  }

  @GetMapping("/")
  public String processSession(Model model, HttpSession session) {
    @SuppressWarnings("unchecked")
    List<String> users = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

    if (users == null) {
      users = new ArrayList<>();
    }
    model.addAttribute("username", users);

    return "dms/index";
  }

  @PostMapping("/sessionCreate")
  public String createSession(final Model model, @Param("user") String user, @Param("pass") String pass, HttpServletRequest request) {
    @SuppressWarnings("unchecked")
    List<String> users = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
    if (users == null) {
      users = new ArrayList<>();
      request.getSession().setAttribute("MY_SESSION_MESSAGES", users);
    }
    users.add(dmsRepo.getUsers(user,pass));
    request.getSession().setAttribute("MY_SESSION_MESSAGES", users);
    return "redirect:/";
  }

  @PostMapping("/sessionDestroy")
  public String destroySession(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:/";
  }

}
