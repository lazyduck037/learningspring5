package com.taskagile.domain.model.user;

import com.taskagile.domain.common.model.AbstractBaseEntity;
import java.util.Objects;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User extends AbstractBaseEntity {

    private static final long serialVersionUID = -538781580460070724L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "email_address", nullable = false, length = 50, unique = true)
    private String emailAddress;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    public User(){}

    public static User create(String username, String emailAddress, String password){
        User user = new User();
        user.username = username;
        user.emailAddress = emailAddress;
        user.password = password;
        user.firstName = "";
        user.lastName = "";
        user.createdDate = new Date();
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User user = (User) obj;
        return Objects.equals(username, user.username) &&
                Objects.equals(emailAddress, user.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, emailAddress);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password=<Protected> " +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void updateName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserId getId() {
        return new UserId(id);
    }

    public String getPassword(){
        return password;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Date getCreatedDate(){
        return createdDate;
    }

    public String getInitials() {
        return "Username Init";//(firstName.substring(0, 1) + lastName.substring(0, 1)).toUpperCase();
    }


}
