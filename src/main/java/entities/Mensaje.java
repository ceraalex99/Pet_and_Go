package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name= "mensajes"  )
public class Mensaje implements Serializable{
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="created_at")
    private LocalDateTime created_at;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender")
    private Usuario sender;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver")
    private Usuario receiver;

    @Column(name="text")
    private String text;


    public Mensaje() {
    }

    public Mensaje(Usuario sender, Usuario receiver, String text, LocalDateTime created_at) {
        this.sender  = sender;
        this.receiver = receiver;
        this.text = text;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                ", id='" + id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", sender='" + sender + '\'' +
                ", reciever='" + receiver + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
