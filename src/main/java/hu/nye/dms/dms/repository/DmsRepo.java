package hu.nye.dms.dms.repository;


import hu.nye.dms.dms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DmsRepo extends JpaRepository<User,Integer> {

  @Query("SELECT u.username FROM User u WHERE u.username=?1 AND u.password=?2")
  String getUsers(String username, String password);

  @Query("SELECT u.username FROM User u WHERE u.username=?1")
  String getRegUser(String username);

  @Query(value = "SELECT ATTRIBUTE_NAME FROM spring_session_attributes", nativeQuery = true)
  String getSessionUser();

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM spring_session_attributes WHERE ATTRIBUTE_NAME!=?1", nativeQuery = true)
  void deleteSessionUser(String user);

}
