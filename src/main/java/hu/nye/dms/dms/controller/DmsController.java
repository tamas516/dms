package hu.nye.dms.dms.controller;

import hu.nye.dms.dms.model.Document;
import hu.nye.dms.dms.model.User;
import hu.nye.dms.dms.repository.DmsRepo;
import hu.nye.dms.dms.repository.DocumentRepo;
import hu.nye.dms.dms.service.DocServiceImpl;
import hu.nye.dms.dms.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller class.
 */
@Controller
public class DmsController {

    @Autowired
    DmsRepo dmsRepo;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    DocServiceImpl docService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        request.getSession().invalidate();
        return "dms/index";
    }

    @RequestMapping(value = "/sessionCreate", method = RequestMethod.POST, params = "action=registration")
    public String registrationLink() {

        return "dms/registration";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST, params = "action=loginLink")
    public String loginLink() {

        return "dms/index";
    }

    @RequestMapping(value = "/fileShow", method = RequestMethod.POST, params = "action=allFiles")
    public String allFilesLink(final Model model) {

        String user = userService.getSessionUsername();
        int userId = docService.getUserId(user);

        List<Document> listDocs = docService.getAllFiles(userId);

        model.addAttribute("allFiles", listDocs);

        model.addAttribute("username", userService.getSessionUsername());

        return "dms/logged";
    }

    @RequestMapping(value = "/sessionCreate", method = RequestMethod.POST, params = "action=login")
    public String createSession(final Model model, @Param("user") String user, @Param("pass") String pass, HttpServletRequest request) {

        request.getSession().setMaxInactiveInterval(60*60);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


        User user1 = new User();

        if (bCryptPasswordEncoder.matches(pass,userService.getPassword(user))) {
            request.getSession().setAttribute(user,user);

            user1.setUsername(user);
            userService.deleteSessionUser(user1.getUsername());

            model.addAttribute("username", user1.getUsername());
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
        } else if (userService.getRegUsername(user) == null) {
            User user1 = new User();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encryptedPwd = bCryptPasswordEncoder.encode(pass);
            user1.setUsername(user);
            user1.setPassword(encryptedPwd);
            dmsRepo.save(user1);

            return "dms/index";
        } else {
            model.addAttribute("errorMessage", "Hibás adatok!");
            return "dms/registration";
        }

    }



}
