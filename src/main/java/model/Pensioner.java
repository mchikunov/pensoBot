package model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "pensioner")
public class Pensioner extends User implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Column(name = "address", unique = false, updatable = false)
    private String address;

    @Column(name = "comment", unique = false, updatable = false)
    private String comment;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public Pensioner() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public Pensioner(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public Pensioner(String name) {
        this.setId(-1);
        this.setName(name);
    }


    public Pensioner(String name, String lastName, long age, String address, String comment) {
        super(name, lastName, age);
        this.address = address;
        this.comment = comment;
    }

//    @SuppressWarnings("UnusedDeclaration")
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return "UserDataSet{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}