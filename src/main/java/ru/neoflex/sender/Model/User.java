package ru.neoflex.sender.Model;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Document
public class User {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private long id;
    @NotBlank(message = "Field name is empty!")
    @Length(min = 3,max=30,message = "Field name: minimum length=3,maximum length=255")
    private String name;
    @NotBlank(message = "Field email is empty!")
    @Length(min = 3,max=30,message = "Field email: minimum length=4,maximum length=255")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
