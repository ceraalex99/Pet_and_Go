package domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh-mm-ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="fechaquedada")
    private Date fechaQuedada;

    @Column(name="lugarinicio")
    private String lugarInicio;

    @Column(name="latitud")
    private double latitud;

    @Column(name="longitud")
    private double longitud;

    @Column(name = "idimagegoogle")
    private String idImageGoogle;

    @Column(name = "finalizada", columnDefinition = "boolean default false")
    private boolean finalizada;

    @JsonIgnore
    @ManyToMany(mappedBy = "quedadasPart", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<Mascota> participantes = new HashSet<>();

    public Quedada(){}

    public Quedada(String admin, Date createdAt, Date fechaQuedada, String lugarInicio, double latitud, double longitud, String idImageGoogle) {
        this.admin = admin;
        this.createdAt = createdAt;
        this.fechaQuedada = fechaQuedada;
        this.lugarInicio = lugarInicio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idImageGoogle = idImageGoogle;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getFechaQuedada() {
        return fechaQuedada;
    }

    public void setFechaQuedada(Date fechaQuedada) {
        this.fechaQuedada = fechaQuedada;
    }

    public String getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(String lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getIdImageGoogle() {
        return idImageGoogle;
    }

    public void setIdImageGoogle(String idImageGoogle) {
        this.idImageGoogle = idImageGoogle;
    }

    public Set<Mascota> getParticipantes() {
        return participantes;
    }

    public void addParticipante(Mascota mascota){
        participantes.add(mascota);
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    private boolean tieneParticipantes() {
        return participantes != null && !participantes.isEmpty();
    }

    public void cambiarAdmin(){

        boolean paricipanteNoAdmin = false;
        String nombreParticipante;

        if (tieneParticipantes()){
            Iterator<Mascota> it = participantes.iterator();
            while(it.hasNext() && !paricipanteNoAdmin){
                nombreParticipante = it.next().getId().getAmo();
                if (!admin.equals(nombreParticipante)){
                    paricipanteNoAdmin = true;
                    admin = nombreParticipante;
                }
            }
        }
    }

}