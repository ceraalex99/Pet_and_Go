package api.dao;

import entities.Mascota;
import entities.Quedada;

import java.util.List;

public interface QuedadaDAO {
    boolean altaQuedada(Quedada usuario);
    boolean deleteQuedadaById(Integer id);
    boolean deleteQuedada(Quedada usuario);
    void updateQuedada(Quedada usuario);
    List findAllQuedada();
    Quedada findById(Integer id);

}
