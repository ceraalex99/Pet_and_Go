package api.services;

import entities.PerreParada;

import java.util.List;

public interface PerreParadaServices {
    boolean altaPerreParada(PerreParada PerreParada);
    boolean deletePerreParadaById(Integer id);
    boolean deletePerreParada(PerreParada PerreParada);
    void updatePerreParada(PerreParada PerreParada);
    List<PerreParada> findAllPerreParada();
    PerreParada findById(Integer id);
}
