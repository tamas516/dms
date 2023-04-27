package hu.nye.dms.dms.service;

import java.util.List;

import hu.nye.dms.dms.model.Document;

/**
 * DocService interface.
 */
public interface DocService {

  List<Document> getAllFiles(int userId);

  List<Document> search(int userId, String filename);

  void saveAllFilesList(List<Document> fileList);

  int getUserId(String username);

  void deleteFile(int fileId);
}
