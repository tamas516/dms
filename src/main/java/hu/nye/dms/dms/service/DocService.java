package hu.nye.dms.dms.service;

import hu.nye.dms.dms.model.Document;

import java.util.List;

public interface DocService {

  List<Document> getAllFiles(int userId);
  void saveAllFilesList(List<Document> fileList);
  int getUserId(String username);

}
