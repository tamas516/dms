package hu.nye.dms.dms.controller;

import hu.nye.dms.dms.model.Document;
import hu.nye.dms.dms.repository.DmsRepo;
import hu.nye.dms.dms.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class DocumentController {


    @Autowired
    DocumentRepo documentRepo;

    @Autowired
    DmsRepo dmsRepo;

    @PostMapping("/upload")
    public String uploadMultipartFile(@RequestParam("document") MultipartFile multipartFile, Model model,
                                      @RequestParam("targykod") String targykod, @RequestParam("cegnev") String cegnev,
                                      @RequestParam("felev") String felev, @RequestParam("ora") String ora,
                                      @RequestParam("nk") String nk) throws IOException {

        if(targykod.equals("Válassz tárgykódot!") || cegnev.equals("Válassz céget!") ||
                felev.equals("Válassz félévet!") || ObjectUtils.isEmpty(ora) || ObjectUtils.isEmpty(nk)) {
            model.addAttribute("errorMessage", "Adatok megadása kötelező!");
            model.addAttribute("username", dmsRepo.getSessionUser());
            return "dms/logged";
        } else {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

            Document document = new Document();
            document.setFileName(fileName);
            document.setData(multipartFile.getBytes());

            String user = dmsRepo.getSessionUser();
            int userId = documentRepo.getUserId(user);

            document.setSubjectCode(targykod);
            document.setCompanyName(cegnev);
            document.setSemester(felev);
            document.setLengthOfWork(ora);
            document.setNeptunCode(nk);
            document.setUserId(userId);

            documentRepo.save(document);

            List<Document> listDocs = documentRepo.findAll(userId);

            model.addAttribute("allFiles", listDocs);

            model.addAttribute("username", dmsRepo.getSessionUser());
            return "dms/logged";
        }
    }

    @RequestMapping("/{fileId}")
    public void downLoadFile(@PathVariable(value = "fileId") Integer fileId, HttpServletResponse response) throws Exception {
        Optional<Document> result = documentRepo.findById(fileId);
        if(!result.isPresent()){
            throw new Exception("Could not find document by id: " + fileId);
        }

        Document document = result.get();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + document.getFileName();
        response.setHeader(headerKey,headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(document.getData());
        outputStream.close();

    }

}
