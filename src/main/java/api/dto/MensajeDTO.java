package api.dto;


import entities.Usuario;

import java.time.LocalDate;

public class MensajeDTO {

    private int id;
    private LocalDate created_at;
    private Usuario sender;
    private Usuario receiver;
    private String data;

    public MensajeDTO() {
    }

    public MensajeDTO(Usuario sender, Usuario receiver, String data, LocalDate created_at) {
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