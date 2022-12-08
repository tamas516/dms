package hu.nye.dms.dms.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * User model.
 */


@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @OneToOne
  @PrimaryKeyJoinColumn(name = "id")
  Document document;

  @OneToOne(mappedBy = "user")
  Session session;

  public User() {

  }

  /**
   * User constructor.
   */

  public User(Integer id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return id.equals(user.id) && username.equals(user.username) && password.equals(user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

}
