package hu.nye.dms.dms.service;

import java.util.List;

import hu.nye.dms.dms.model.Document;
import hu.nye.dms.dms.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DocService Implementation class.
 */
@Service
public class DocServiceImpl implements DocService {

  @Autowired
  DocumentRepo documentRepo;

  @Override
  public List<Document> getAllFiles(int userId) {
    return documentRepo.findAll(userId);
  }

  @Override
  public List<Document> search(int userId, String filename) {
    return documentRepo.search(userId, filename);
  }

  @Override
  public int getUserId(String username) {
    return documentRepo.getUserId(username);
  }

  @Override
  public void deleteFile(int fileId) {
    documentRepo.deleteFile(fileId);
  }

  @Override
  public void saveAllFilesList(List<Document> fileList) {
    for (Document document : fileList) {
      documentRepo.save(document);
    }
  }


}
