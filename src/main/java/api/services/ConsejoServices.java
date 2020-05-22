package api.services;

import entities.Consejo;

import java.util.List;

public interface ConsejoServices {
    boolean altaConsejo(Consejo Consejo);
    boolean deleteConsejoById(Integer id);
    void deleteConsejo(Consejo consejo);
    void updateConsejo(Consejo consejo);
    List findAllConsejos();
    Consejo findById(Integer id);
}