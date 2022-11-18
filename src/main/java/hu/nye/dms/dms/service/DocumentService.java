package hu.nye.dms.dms.service;

import hu.nye.dms.dms.model.Document;
import hu.nye.dms.dms.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepo documentRepo;

    public Document saveFile(MultipartFile multipartFile)
    {
        String fileName = multipartFile.getOriginalFilename();
        try {
            Document document = new Document(fileName, multipartFile.getContentType(), multipartFile.getBytes());
            return documentRepo.save(document);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  null;
    }
}
