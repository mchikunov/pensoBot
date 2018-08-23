package model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "volonter")
public class Volonter extends User implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Column(name = "status_vol", unique = false, updatable = false)
    public boolean status;

    @Column(name = "rank_col", unique = false, updatable = false)
    public String rank;

    @SuppressWarnings("UnusedDeclaration")
    public Volonter() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public Volonter(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public Volonter(String name) {
        this.setId(-1);
        this.setName(name);
    }


    public Volonter(String name, String lastName, long age, boolean status, String rank) {
        super(name, lastName, age);
        this.status = status;
        this.rank = rank;
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