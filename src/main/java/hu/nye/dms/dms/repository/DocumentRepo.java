package hu.nye.dms.dms.repository;

import hu.nye.dms.dms.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DocumentRepo extends JpaRepository<Document,Integer> {

    @Query("SELECT new Document(d.fileId,d.fileName,d.subjectCode,d.companyName," +
            "d.semester,d.lengthOfWork,d.neptunCode,d.userId,d.data) FROM User u INNER JOIN Document d ON u.id=d.userId " +
            "WHERE u.id=?1")
    List<Document> findAll(int userId);

    @Query("SELECT u.id FROM User u INNER JOIN Session s ON u.username=s.sessionUsername" +
            " WHERE s.sessionUsername=?1")
    int getUserId(String username);

}
