package model;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public class User implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = false, updatable = false)
    private String name;

    @Column(name = "lastName", unique = false, updatable = false)
    private String lastName;

    @Column(name = "age", unique = false, updatable = false)
    private String age;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public User() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public User(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public User(String name) {
        this.setId(-1);
        this.setName(name);
    }

    public User(String name, String lastName, String age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }


    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}