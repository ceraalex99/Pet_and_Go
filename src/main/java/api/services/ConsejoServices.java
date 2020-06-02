package api.services;

import entities.Consejo;

import java.util.List;

public interface ConsejoServices {
    boolean altaConsejo(Consejo consejo);
    boolean deleteConsejoById(Integer id);
    void deleteConsejo(Consejo consejo);
    void updateConsejo(Consejo consejo);
    List<Consejo> findAllConsejos();
    Consejo findById(Integer id);
}