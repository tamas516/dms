package hu.nye.dms.dms.repository;


import hu.nye.dms.dms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DmsRepo extends JpaRepository<User,Integer> {

  @Query(value = "SELECT username FROM users WHERE username=?1 AND password=?2", nativeQuery = true)
  public String getUsers(String username, String password);

  @Query(value = "SELECT username FROM users WHERE username=?1", nativeQuery = true)
  public String getRegUser(String username);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO users (username,password) VALUES (?1,?2)", nativeQuery = true)
  public void insertUser(String username, String password);
}
