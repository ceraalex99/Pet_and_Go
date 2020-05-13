package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;


import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name= "usuarios")
public class Usuario implements Serializable {

    @Column(name="nombre")
    private String nombre;

    @Column(name="username")
    private String username;

    @Id
    @Column(name="email")
    private String email;

    @Column(name="password")
    @JsonIgnore
    private String password;

    @Column(name="profileimage")
    private byte[] image;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emailusuario", referencedColumnName = "email",nullable = false, insertable=false )
    @JsonIgnore
    private Set<Mascota> mascotas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario", referencedColumnName = "email",nullable = false, insertable=false )
    @JsonIgnore
    private Set<Evento> eventos;


    @OneToMany
    @JoinColumn(name= "admin",nullable=false, insertable=false)
    @JsonIgnore
    private Set<Quedada> quedadasAdmin;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    @JsonIgnore
    private Set<Mensaje> mensajesEnviados;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    @JsonIgnore
    private Set<Mensaje> mensajesRecibidos;

    @Column(name="firebase_token")
    private String firebaseToken;

    public Set<Quedada> preRemove(){
        if(quedadasAdmin != null) {
            Iterator<Quedada> itr = quedadasAdmin.iterator();
            Quedada q;
            while (itr.hasNext()) {
                q = itr.next();
                q.cambiarAdmin();
            }
        }
        return quedadasAdmin;
    }

    public Usuario() {
    }

    public Usuario(String nombre,String username,String password,String email) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Mascota> getMascotas() {
        return mascotas;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }
    public void addEvento(Evento evento) {
        this.eventos.add(evento);
    }

    public void addMascota(Mascota mascota) {
        this.mascotas.add(mascota);
    }

    public void removeMascota(Mascota mascota) {
        this.mascotas.remove(mascota);
    }
    public void removeEvento(Evento evento) {
        this.eventos.remove(evento);
    }

    public Set<Quedada> getQuedadasAdmin() {
        return quedadasAdmin;
    }

    public void addQuedadaAdmin(Quedada quedada) {
        this.quedadasAdmin.add(quedada);
    }

    public void removeQuedadaAdmin(Quedada quedada) {
        this.quedadasAdmin.remove(quedada);
    }

    public void setImage(byte[] image) { this.image = image; }

    public byte[] getImage() { return image; }

    public Set<Mensaje> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public void addMensajeEnviado(Mensaje mensaje) {
        this.mensajesEnviados.add(mensaje);
    }

    public void removeMensajeEnviado(Mensaje mensaje) {
        this.mensajesEnviados.remove(mensaje);
    }

    public Set<Mensaje> getMensajesRecibido() {
        return mensajesRecibidos;
    }

    public void addMensajeRecibido(Mensaje mensaje) {
        this.mensajesRecibidos.add(mensaje);
    }

    public void removeMensajeRecibido(Mensaje mensaje) {
        this.mensajesRecibidos.remove(mensaje);
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}