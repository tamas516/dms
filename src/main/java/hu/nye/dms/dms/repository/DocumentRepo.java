package hu.nye.dms.dms.repository;

import java.util.List;

import hu.nye.dms.dms.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Document Repository class.
 */
@Repository
@Transactional
public interface DocumentRepo extends JpaRepository<Document, Integer> {

  @Query("SELECT new Document(d.fileId,d.fileName,d.subjectCode,d.companyName,"
      + "d.semester,d.lengthOfWork,d.neptunCode,d.userId,d.data)"
      + " FROM User u INNER JOIN Document d ON u.id=d.userId "
      + "WHERE u.id=?1")
  List<Document> findAll(int userId);

  @Query("SELECT new Document(d.fileId,d.fileName,d.subjectCode,d.companyName,"
          + "d.semester,d.lengthOfWork,d.neptunCode,d.userId,d.data)"
          + " FROM User u INNER JOIN Document d ON u.id=d.userId "
          + "WHERE u.id=?1 AND d.fileName LIKE %?2%")
  List<Document> search(int userId, String filename);


  @Query("SELECT u.id FROM User u WHERE u.username=?1")
  int getUserId(String username);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM files WHERE file_id=?1", nativeQuery = true)
  void deleteFile(int fileId);

}
