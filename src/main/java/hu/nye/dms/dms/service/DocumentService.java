package hu.nye.dms.dms.service;

import hu.nye.dms.dms.model.Document;
import hu.nye.dms.dms.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService implements DocService{

  @Autowired
  DocumentRepo documentRepo;

  @Override
  public List<Document> getAllFiles(int userId) {
    return documentRepo.findAll(userId);
  }

  @Override
  public int getUserId(String username) {
    return documentRepo.getUserId(username);
  }

  @Override
  public void saveAllFilesList(List<Document> fileList) {
    for (Document document : fileList)
      documentRepo.save(document);
  }


}
