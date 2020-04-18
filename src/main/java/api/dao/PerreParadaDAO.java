package api.dao;

import entities.PerreParada;

import java.util.List;

public interface PerreParadaDAO {
    boolean altaPerreParada(PerreParada usuario);
    boolean deletePerreParadaById(Integer id);
    boolean deletePerreParada(PerreParada usuario);
    void updatePerreParada(PerreParada usuario);
    List findAllPerreParada();
    PerreParada  findById(Integer id);
}
