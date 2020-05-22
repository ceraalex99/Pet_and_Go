package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "consejos"  )
public class Consejo implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="consejo",unique = true)
    private String consejo;


    public Consejo() {
    }

    public Consejo(String consejo) {
        this.consejo = consejo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setConsejo(String consejo) {
        this.consejo = consejo;
    }

    public String getConsejo() {
        return consejo;
    }

    @Override
    public String toString() {
        return "Consejo{" +
                ", id='" + id + '\'' +
                ", consejo='" + consejo + '\'' +
                '}';
    }
}