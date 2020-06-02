package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name= "mensajes"  )
public class Mensaje implements Serializable{
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="created_at")
    private LocalDate created_at;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender")
    private Usuario sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver")
    private Usuario receiver;

    @Column(name="data")
    private String data;


    public Mensaje() {
    }

    public Mensaje(Usuario sender, Usuario receiver, String data, LocalDate created_at) {
        this.sender  = sender;
        this.receiver = receiver;
        this.data = data;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public Usuario getSender() {
        return sender;
    }

    public void setSender(Usuario sender) {
        this.sender = sender;
    }

    public Usuario getReceiver() {
        return receiver;
    }

    public void setReceiver(Usuario receiver) {
        this.receiver = receiver;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                ", id='" + id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", sender='" + sender + '\'' +
                ", reciever='" + receiver + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
