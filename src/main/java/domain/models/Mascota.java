package domain.models;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "mascotas"  )
public class Mascota implements Serializable {


    @EmbeddedId
    private MascotaId id;

    @Column(name="fechanacimiento")
    private LocalDate fechaNacimiento;

    @Column(name="raza")
    private String raza;

    @Column(name="petimage")
    private byte[] petimage;

    @ManyToOne
    @JoinColumn(name = "emailusuario" ,insertable = false, updatable = false)
    Usuario amo;

    @ManyToMany()
    @JoinColumn(nullable = false,insertable = false)
    private Set<Quedada> quedadasPart;



    public Set<Quedada> getQuedadasPart() {
        return quedadasPart;
    }

    public void addQuedadaPart(Quedada quedada) {
        this.quedadasPart.add(quedada);
    }

    public void removeQuedadaPart(Quedada quedada) {
        this.quedadasPart.remove(quedada);
    }

    public Mascota() {
        quedadasPart = new HashSet<>();
    }

    public Mascota(MascotaId id,LocalDate fechaNacimiento) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
    }

    public MascotaId getId() {
        return id;
    }

    public void setId(MascotaId id) {
        this.id = id;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public byte[] getPetimage() {
        return petimage;
    }

    public void setPetimage(byte[] petimage) {
        this.petimage = petimage;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                ", nombre='" + id.getNombre() + '\'' +
                ", amo='" + id.getAmo() + '\'' +
                ", fecha de nacimiento='" + fechaNacimiento + '\'' +
                '}';
    }
}