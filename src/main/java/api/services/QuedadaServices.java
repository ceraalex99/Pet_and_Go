package api.services;

import entities.Mascota;
import entities.Quedada;

import java.util.List;

public interface QuedadaServices {
    int altaQuedada(Quedada Quedada);
    boolean deleteQuedadaById(Integer id);
    boolean deleteQuedada(Quedada Quedada);
    void updateQuedada(Quedada Quedada);
    List<Quedada> findAllQuedada();
    Quedada findById(Integer id);

}
