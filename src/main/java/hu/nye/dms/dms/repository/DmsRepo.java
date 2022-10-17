package hu.nye.dms.dms.repository;


import hu.nye.dms.dms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DmsRepo extends JpaRepository<User,Integer> {

  @Query(value = "SELECT username FROM users WHERE username=?1 AND password=?2", nativeQuery = true)
  public String getUsers(String username, String password);

}
