package hu.nye.dms.dms.service;

public interface UserService {

  String getSessionUsername();

  String getRegUsername(String username);

  String getPassword(String username);

  void deleteSessionUser(String username);

}
