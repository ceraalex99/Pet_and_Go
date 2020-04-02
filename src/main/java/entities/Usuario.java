package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name= "usuarios"  )
public class Usuario implements Serializable {

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Id
    @Column(name="email")
    private String email;

    @Column(name="nombre")
    private String nombre;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="usuarios")
    @JsonIgnore
    private Set<Mascota> mascotas;


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

    public void setMascotas(Set<Mascota> mascotas) {
        this.mascotas = mascotas;
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