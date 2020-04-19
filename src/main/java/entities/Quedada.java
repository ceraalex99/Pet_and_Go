package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name= "quedadas")
public class Quedada implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="admin")
    private String admin;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="fechaquedada")
    private LocalDateTime fechaQuedada;

    @Column(name="lugarinicio")
    private String lugarInicio;

    @Column(name="lugarfin")
    private String lugarFin;

    @JsonIgnore
    @ManyToMany(mappedBy = "quedadasPart", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    Set<Mascota> participantes = new HashSet<>();

    public Quedada(){}

    public Quedada(String admin, LocalDateTime createdAt, LocalDateTime fechaQuedada, String lugarInicio, String lugarFin) {
        this.admin = admin;
        this.createdAt = createdAt;
        this.fechaQuedada = fechaQuedada;
        this.lugarInicio = lugarInicio;
        this.lugarFin = lugarFin;
    }

    public Integer getId() {
        return id;
    }


    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFechaQuedada() {
        return fechaQuedada;
    }

    public void setFechaQuedada(LocalDateTime fechaQuedada) {
        this.fechaQuedada = fechaQuedada;
    }

    public String getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(String lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public String getLugarFin() {
        return lugarFin;
    }

    public void setLugarFin(String lugarFin) {
        this.lugarFin = lugarFin;
    }

    public Set<Mascota> getParticipantes() {
        return participantes;
    }

    public void addParticipante(Mascota mascota){
        participantes.add(mascota);
    }
}