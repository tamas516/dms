package hu.nye.dms.dms.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import hu.nye.dms.dms.model.Document;
import hu.nye.dms.dms.model.User;
import hu.nye.dms.dms.repository.UserRepo;
import hu.nye.dms.dms.service.DocServiceImpl;
import hu.nye.dms.dms.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller class.
 */
@Controller
public class UserController {

  @Autowired
  UserRepo userRepo;

  @Autowired
  UserServiceImpl userService;

  @Autowired
  DocServiceImpl docService;

  /**
   * Index session method.
   */
  @GetMapping("/")
  public String indexSession(HttpServletRequest request) {

    request.getSession().invalidate();
    return "dms/index";
  }

  /**
   * Index method.
   */
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index() {
    return "dms/index";
  }

  /**
   * Login method.
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "dms/login";
  }

  /**
   * Registration link method.
   */
  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public String reg() {
    return "dms/registration";
  }

  /**
   * CreateSession method.
   */
  @RequestMapping(value = "/sessionCreate", method = RequestMethod.POST,
      params = "action=login")
  public String createSession(final Model model, @Param("user") String user,
                              @Param("pass") String pass,
                              HttpServletRequest request) {

    request.getSession().setMaxInactiveInterval(60 * 60);

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    User user1 = new User();

    String username = userService.getRegUsername(user);

    if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(pass)) {
      model.addAttribute("errorMessage", "Hiányzó adatok!");
      return "dms/login";
    } else if (username.equals(user) && bcrypt.matches(pass, userService.getPassword(user))) {
      request.getSession().setAttribute(user, user);

      user1.setUsername(user);
      userService.deleteSessionUser(user1.getUsername());

      int userId = docService.getUserId(username);

      List<Document> listDocs = docService.getAllFiles(userId);

      model.addAttribute("allFiles", listDocs);

      model.addAttribute("username", userService.getRegUsername(user));
      return "dms/logged";
    } else {
      model.addAttribute("errorMessage", "Hibás adatok!");
      return "dms/login";
    }

  }

  /**
   * DestroySession method.
   */
  @PostMapping("/sessionDestroy")
  public String destroySession(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:/";
  }

  /**
   * Registration method.
   */
  @PostMapping("/reg")
  public String registration(final Model model, @Param("user") String user,
                             @Param("pass") String pass,
                             @Param("nk") String nk) {

    boolean benne = false;
    List<String> allNeptunCode = userService.getAllNeptunCode();

    for (String s : allNeptunCode) {
      if (s.equals(nk)) {
        benne = true;
        break;
      }
    }

    if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(pass) || ObjectUtils.isEmpty(nk)) {
      model.addAttribute("errorMessage", "Hiányzó adatok!");
      return "dms/registration";
    } else if (!benne) {
      User user1 = new User();
      BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
      String encryptedPwd = bcrypt.encode(pass);
      user1.setUsername(user);
      user1.setPassword(encryptedPwd);
      user1.setNeptuncode(nk);
      userRepo.save(user1);

      return "dms/login";
    } else {
      model.addAttribute("errorMessage", "A neptun kód már létezik!");
      return "dms/registration";
    }

  }



}
