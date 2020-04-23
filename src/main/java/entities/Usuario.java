package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name= "usuarios"  )
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


    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="emailusuario" , nullable=false, insertable=false )
    @JsonIgnore
    private Set<Mascota> mascotas;

    @OneToMany()
    @JoinColumn(name= "admin",nullable=false, insertable=false)
    @JsonIgnore
    private Set<Quedada> quedadasAdmin;


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

    public void addMascota(Mascota mascota) {
        this.mascotas.add(mascota);
    }

    public void removeMascota(Mascota mascota) {
        this.mascotas.remove(mascota);
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