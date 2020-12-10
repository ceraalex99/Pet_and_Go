package application.services;

import domain.models.Consejo;

import java.util.List;

public interface ConsejoServices {
    boolean altaConsejo(Consejo consejo);

    boolean deleteConsejoById(Integer id);

    List<Consejo> findAllConsejos();

    Consejo findById(Integer id);
}