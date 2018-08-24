package model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "volunteer")
public class Volunteer extends User implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Column(name = "status_vol", unique = false, updatable = false)
    public boolean status;

    @Column(name = "rank_col", unique = false, updatable = false)
    public String rank;

    public boolean isStatus() {
        return status;
    }


    @SuppressWarnings("UnusedDeclaration")
    public Volunteer() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public Volunteer(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public Volunteer(String name) {
        this.setId(-1);
        this.setName(name);
    }


    public Volunteer(String name, String lastName, String age, boolean status, String rank) {
        super(name, lastName, age);
        this.status = status;
        this.rank = rank;
    }

}