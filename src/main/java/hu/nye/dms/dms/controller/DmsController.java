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
import org.springframework.util.ObjectUtils;
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

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> users = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (users == null) {
            users = new ArrayList<>();
        }
        model.addAttribute("username", users);

        return "dms/index";
    }

    @RequestMapping(value = "/sessionCreate", method = RequestMethod.POST, params = "action=registration")
    public String registrationLink() {
        return "dms/registration";
    }

    @RequestMapping(value = "/sessionCreate", method = RequestMethod.POST, params = "action=login")
    public String createSession(final Model model, @Param("user") String user, @Param("pass") String pass, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<String> users = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (users == null) {
            users = new ArrayList<>();
            request.getSession().setAttribute("MY_SESSION_MESSAGES", users);
        }
        if (dmsRepo.getUsers(user, pass) != null) {
            request.getSession().setAttribute("MY_SESSION_MESSAGES", users);
            users.add(dmsRepo.getUsers(user, pass));
            model.addAttribute("username", users);
            return "dms/logged";
        } else if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(pass)) {
            model.addAttribute("errorMessage", "Hiányzó adatok!");
            return "dms/index";
        } else {
            model.addAttribute("errorMessage", "Hibás adatok!");
            return "dms/index";
        }

    }

    @PostMapping("/sessionDestroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @PostMapping("/reg")
    public String registration(final Model model, @Param("user") String user, @Param("pass") String pass) {

        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(pass)) {
            model.addAttribute("errorMessage", "Hiányzó adatok!");
            return "dms/registration";
        } else if (dmsRepo.getRegUser(user) == null) {
            dmsRepo.insertUser(user, pass);
            return "dms/index";
        } else {
            model.addAttribute("errorMessage", "Hibás adatok!");
            return "dms/registration";
        }

    }

}
