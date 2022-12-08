package hu.nye.dms.dms.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="files")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "semester")
    private String semester;

    @Column(name = "length_of_work")
    private String lengthOfWork;

    @Column(name = "neptun_code")
    private String neptunCode;

    @Column(name = "user_id")
    private Integer userId;

    @Lob
    @Column(name = "data")
    private byte[] data;


    @OneToOne(mappedBy = "document")
    User user;

    public Document() {
    }

    public Document(Integer fileId, String fileName,
                    String subjectCode, String companyName, String semester,
                    String lengthOfWork, String neptunCode, Integer userId, byte[] data) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.subjectCode = subjectCode;
        this.companyName = companyName;
        this.semester = semester;
        this.lengthOfWork = lengthOfWork;
        this.neptunCode = neptunCode;
        this.userId = userId;
        this.data = data;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLengthOfWork() {
        return lengthOfWork;
    }

    public void setLengthOfWork(String lengthOfWork) {
        this.lengthOfWork = lengthOfWork;
    }

    public String getNeptunCode() {
        return neptunCode;
    }

    public void setNeptunCode(String neptunCode) {
        this.neptunCode = neptunCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        Document document = (Document) o;
        return Objects.equals(fileId, document.fileId) && Objects.equals(fileName, document.fileName) && Objects.equals(subjectCode, document.subjectCode) && Objects.equals(companyName, document.companyName) && Objects.equals(semester, document.semester) && Objects.equals(lengthOfWork, document.lengthOfWork) && Objects.equals(neptunCode, document.neptunCode) && Objects.equals(userId, document.userId) && Arrays.equals(data, document.data) && Objects.equals(user, document.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fileId, fileName, subjectCode, companyName, semester, lengthOfWork, neptunCode, userId, user);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", semester='" + semester + '\'' +
                ", lengthOfWork=" + lengthOfWork +
                ", neptunCode='" + neptunCode + '\'' +
                ", userId=" + userId +
                ", data=" + Arrays.toString(data) +
                ", user=" + user +
                '}';
    }
}
