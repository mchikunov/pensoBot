package model;

import javax.persistence.*;


@Entity
@Table(name = "pensioner")
public class Pensioner extends User { // Serializable Important to Hibernate!

    @Column(name = "address", unique = false)
    private String address;

    @Column(name = "phone", unique = false)
    private String phone;

    @Column(name = "comment", unique = false)
    private String comment;

    @Column(name = "is_waiting", unique = false)
    private boolean isWaiting;

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public synchronized boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean waiting) {
        this.isWaiting = waiting;
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