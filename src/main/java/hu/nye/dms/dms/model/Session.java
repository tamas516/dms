package hu.nye.dms.dms.model;

import java.util.Arrays;
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
 * Session entity class.
 */
@Entity
@Table(name = "spring_session_attributes")
public class Session {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SESSION_PRIMARY_ID ")
  private String sessionId;

  @Column(name = "ATTRIBUTE_NAME ")
  private String sessionUsername;

  @Column(name = "ATTRIBUTE_BYTES")
  private byte[] sessionData;

  @OneToOne
  @PrimaryKeyJoinColumn(name = "ATTRIBUTE_NAME")
  User user;

  public Session() {
  }

  /**
   * Session Constructor.
   */
  public Session(String sessionId, String sessionUsername, byte[] sessionData) {
    this.sessionId = sessionId;
    this.sessionUsername = sessionUsername;
    this.sessionData = sessionData;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getSessionUsername() {
    return sessionUsername;
  }

  public void setSessionUsername(String sessionUsername) {
    this.sessionUsername = sessionUsername;
  }

  public byte[] getSessionData() {
    return sessionData;
  }

  public void setSessionData(byte[] sessionData) {
    this.sessionData = sessionData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Session)) {
      return false;
    }
    Session session = (Session) o;
    return Objects.equals(sessionId, session.sessionId)
        && Objects.equals(sessionUsername, session.sessionUsername)
        && Arrays.equals(sessionData, session.sessionData)
        && Objects.equals(user, session.user);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(sessionId, sessionUsername, user);
    result = 31 * result + Arrays.hashCode(sessionData);
    return result;
  }

  @Override
  public String toString() {
    return "Session{"
        + "sessionId='" + sessionId + '\''
        + ", sessionUsername='" + sessionUsername + '\''
        + ", sessionData=" + Arrays.toString(sessionData)
        + ", user=" + user
        + '}';
  }
}
