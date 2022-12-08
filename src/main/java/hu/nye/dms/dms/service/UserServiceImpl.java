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
  public String getUsername(String username, String password) {
    return dmsRepo.getUsers(user.getUsername(),user.getPassword());
  }

  @Override
  public String getRegUsername(String username) {
    return dmsRepo.getRegUser(user.getUsername());
  }

}
