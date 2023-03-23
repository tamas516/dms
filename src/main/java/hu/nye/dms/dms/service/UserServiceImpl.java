package hu.nye.dms.dms.service;

import hu.nye.dms.dms.model.User;
import hu.nye.dms.dms.repository.DmsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  DmsRepo dmsRepo;

  User user = new User();

  @Override
  public String getSessionUsername() {
    return dmsRepo.getSessionUser();
  }

  @Override
  public String getRegUsername(String username) {
    user.setUsername(username);
    return dmsRepo.getRegUser(user.getUsername());
  }

  @Override
  public String getPassword(String username) {
    user.setUsername(username);
    return dmsRepo.getPassword(user.getUsername());
  }

  @Override
  public void deleteSessionUser(String username) {
    user.setUsername(username);
    dmsRepo.deleteSessionUser(user.getUsername());
  }
}
