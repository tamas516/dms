package hu.nye.dms.dms.service;

import java.util.List;

import hu.nye.dms.dms.model.User;
import hu.nye.dms.dms.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService Implementation class.
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepo userRepo;

  User user = new User();

  @Override
  public String getSessionUsername() {
    return userRepo.getSessionUser();
  }

  @Override
  public String getNeptunCode(String username) {
    return userRepo.getNeptunCode(username);
  }

  @Override
  public List<String> getAllNeptunCode() {
    return userRepo.getAllNeptunCode();
  }

  @Override
  public String getRegUsername(String username) {
    user.setUsername(username);
    return userRepo.getRegUser(user.getUsername());
  }

  @Override
  public String getPassword(String username) {
    user.setUsername(username);
    return userRepo.getPassword(user.getUsername());
  }

  @Override
  public void deleteSessionUser(String username) {
    user.setUsername(username);
    userRepo.deleteSessionUser(user.getUsername());
  }
}
