package hu.nye.dms.dms.service;

import java.util.List;

/**
 * UserService interface.
 */
public interface UserService {

  String getSessionUsername();

  String getNeptunCode(String username);

  List<String> getAllNeptunCode();

  String getRegUsername(String username);

  String getPassword(String username);

  void deleteSessionUser(String username);


}
