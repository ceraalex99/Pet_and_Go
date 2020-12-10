package domain.dto;



import java.time.LocalDateTime;

public class MensajeDTO {

    private int id;
    private LocalDateTime created_at;
    private String sender;
    private String receiver;
    private String text;

    public MensajeDTO() {
    }

    public MensajeDTO(String sender, String receiver, String text, LocalDateTime created_at) {
        this.sender  = sender;
        this.receiver = receiver;
        this.text = text;
        this.created_at = created_at;
    }

    public MensajeDTO(int id, String sender, String receiver, String text, LocalDateTime created_at) {
        this.id = id;
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
