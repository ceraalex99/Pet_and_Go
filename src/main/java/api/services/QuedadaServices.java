package api.services;

import entities.Quedada;

import java.util.List;

public interface QuedadaServices {
    boolean altaQuedada(Quedada Quedada);
    boolean deleteQuedadaById(Integer id);
    boolean deleteQuedada(Quedada Quedada);
    void updateQuedada(Quedada Quedada);
    List<Quedada> findAllQuedada();
    Quedada findById(Integer id);
}
