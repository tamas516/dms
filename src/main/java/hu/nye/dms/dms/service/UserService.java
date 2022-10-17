package hu.nye.dms.dms.service;

import hu.nye.dms.dms.model.User;
import hu.nye.dms.dms.repository.DmsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  @Autowired
  DmsRepo dmsRepo;

//  public List<User> getAllUsers()
//  {
//    List<User>users = new ArrayList<>();
//    dmsRepo.findAll().forEach(users::add);
//    return users;
//  }

}
