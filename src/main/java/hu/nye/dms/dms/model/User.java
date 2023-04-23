package hu.nye.dms.dms.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * User entity class.
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

  @Column(name = "neptun_code")
  private String neptuncode;

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

  public User(Integer id, String username, String password, String neptuncode) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.neptuncode = neptuncode;
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

  public String getNeptuncode() {
    return neptuncode;
  }

  public void setNeptuncode(String neptuncode) {
    this.neptuncode = neptuncode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return id.equals(user.id) && username.equals(user.username)
        && password.equals(user.password)
        && neptuncode.equals(user.neptuncode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, neptuncode);
  }

  @Override
  public String toString() {
    return "User{"
        + "id=" + id
        + ", username='" + username + '\''
        + ", password='" + password + '\''
        + ", neptun_code='" + neptuncode + '\''
        + '}';
  }

}
