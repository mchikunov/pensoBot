package model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "pensioner")
public class Pensioner extends User implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Column(name = "address", unique = false, updatable = false)
    private String address;

    @Column(name = "phone", unique = false, updatable = false)
    private String phone;

    @Column(name = "comment", unique = false, updatable = false)
    private String comment;

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

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


    public Pensioner(String name, String lastName, String age, String address, String phone, String comment) {
        super(name, lastName, age);
        this.address = address;
        this.phone = phone;
        this.comment = comment;
    }



}