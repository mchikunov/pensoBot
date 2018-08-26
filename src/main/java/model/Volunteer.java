package model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "volunteer")
public class Volunteer extends User implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Column(name = "status_vol", unique = false)
    public boolean status;

    @Column(name = "rank_col", unique = false)
    public String rank;

    @Column(name = "chat_id", unique = true)
    public long chatId;

    @Column(name = "phone", unique = true)
    public String phone;

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

    public Volunteer(String name, String lastName, String age, String phone,  boolean status, long chatId) {
        super(name, lastName, age);
        this.status = status;
        this.rank = "Начинающий";
        this.chatId = chatId;
        this.phone = phone;
    }

    public Volunteer(String name, String lastName, String age, boolean status, long chatId) {
        super(name, lastName, age);
        this.status = status;
        this.rank = "Начинающий";
        this.chatId = chatId;

    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}