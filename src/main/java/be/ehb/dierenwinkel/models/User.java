package be.ehb.dierenwinkel.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name is required!")
    private String name;

    @NotEmpty(message = "Email is required!")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password is required!")
    @Size(min = 8, message = "Password needs to be at least 8 characters!")
    private String password;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public User(int id,String name,String email,String password) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
    }
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
