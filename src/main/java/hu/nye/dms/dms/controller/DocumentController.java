package hu.nye.dms.dms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import hu.nye.dms.dms.model.Document;
import hu.nye.dms.dms.repository.DocumentRepo;
import hu.nye.dms.dms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * DocumentController class.
 */
@Controller
public class DocumentController {

  @Autowired
  DocumentRepo documentRepo;

  @Autowired
  UserRepo userRepo;

  /**
   * FileUpload method.
   */
  @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
  public String fileUpload(Model model) {

    String user = userRepo.getSessionUser();

    model.addAttribute("username", user);

    return "dms/fileUpload";
  }

  /**
   * Logged method.
   */
  @RequestMapping(value = "/logged", method = RequestMethod.GET)
  public String logged(Model model) {

    String user = userRepo.getSessionUser();
    int userId = documentRepo.getUserId(user);

    List<Document> listDocs = documentRepo.findAll(userId);

    model.addAttribute("allFiles", listDocs);
    model.addAttribute("username", user);

    return "dms/logged";
  }

  /**
   * Delete method.
   */
  @RequestMapping(value = "fileDelete", method = RequestMethod.POST)
  public String delete(Model model, @RequestParam(value = "delete") int fileId) {
    documentRepo.deleteFile(fileId);
    String user = userRepo.getSessionUser();
    int userId = documentRepo.getUserId(user);

    List<Document> listDocs = documentRepo.findAll(userId);

    model.addAttribute("username", user);
    model.addAttribute("allFiles", listDocs);

    return "dms/logged";
  }

  /**
   * Upload method.
   */
  @PostMapping("/upload")
  public String uploadMultipartFile(@RequestParam("document") MultipartFile multipartFile,
                                    Model model,
                                    @RequestParam("targykod") String targykod,
                                    @RequestParam("cegnev") String cegnev,
                                    @RequestParam("felev") String felev,
                                    @RequestParam("ora") String ora)
                                      throws IOException {

    if (targykod.equals("Válassz tárgykódot!") || cegnev.equals("Válassz céget!")
        || felev.equals("Válassz félévet!") || ObjectUtils.isEmpty(ora)) {
      model.addAttribute("errorMessage", "Adatok megadása kötelező!");
      model.addAttribute("username", userRepo.getSessionUser());
      return "dms/fileUpload";
    } else {
      String fileName = StringUtils.cleanPath(
          Objects.requireNonNull(multipartFile.getOriginalFilename()));

      Document document = new Document();
      document.setFileName(fileName);
      document.setData(multipartFile.getBytes());

      String user = userRepo.getSessionUser();
      int userId = documentRepo.getUserId(user);

      String nk = userRepo.getNeptunCode(user);

      document.setSubjectCode(targykod);
      document.setCompanyName(cegnev);
      document.setSemester(felev);
      document.setLengthOfWork(ora);
      document.setNeptunCode(nk);
      document.setUserId(userId);

      documentRepo.save(document);

      List<Document> listDocs = documentRepo.findAll(userId);

      model.addAttribute("allFiles", listDocs);

      model.addAttribute("username", user);
      return "dms/logged";
    }
  }

  /**
   * Download method.
   */
  @RequestMapping("/{fileId}")
  public void downLoadFile(@PathVariable(value = "fileId") Integer fileId,
                           HttpServletResponse response)
      throws Exception {
    Optional<Document> result = documentRepo.findById(fileId);
    if (!result.isPresent()) {
      throw new Exception("Nem találja a dokumentumot a megadott id-val: " + fileId);
    }

    Document document = result.get();
    response.setContentType("application/octet-stream");
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=" + document.getFileName();
    response.setHeader(headerKey, headerValue);

    ServletOutputStream outputStream = response.getOutputStream();
    outputStream.write(document.getData());
    outputStream.close();

  }

  /**
   * Search method.
   */
  @RequestMapping("/search")
  public String searchFile(Model model, @RequestParam("search") String search) {

    String user = userRepo.getSessionUser();
    int userId = documentRepo.getUserId(user);

    List<Document> searcResult = documentRepo.search(userId, search);
    model.addAttribute("searchResult", searcResult);
    model.addAttribute("username", user);
    return "dms/searchResult";

  }

}
