package hu.nye.dms.dms.controller;

import hu.nye.dms.dms.model.Document;
import hu.nye.dms.dms.model.User;
import hu.nye.dms.dms.repository.DmsRepo;
import hu.nye.dms.dms.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    private User user;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("files") MultipartFile[] files)
    {
        for(MultipartFile file: files)
        {
            documentService.saveFile(file);
        }

        return "dms/logged";
    }

    @RequestMapping("/fileup")
    public String fileup(final Model model) {
        model.addAttribute(user.getUsername());
        return "dms/fileupload";
    }
}
