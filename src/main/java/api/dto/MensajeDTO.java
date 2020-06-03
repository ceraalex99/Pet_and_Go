package api.dto;



import java.time.LocalDate;
import java.time.LocalDateTime;

public class MensajeDTO {

    private int id;
    private LocalDateTime created_at;
    private String sender;
    private String receiver;
    private String data;

    public MensajeDTO() {
    }

    public MensajeDTO(String sender, String receiver, String data, LocalDateTime created_at) {
        this.sender  = sender;
        this.receiver = receiver;
        this.data = data;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
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
