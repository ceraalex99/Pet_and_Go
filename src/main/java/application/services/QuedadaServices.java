package application.services;

import domain.models.Quedada;

import java.util.List;

public interface QuedadaServices {
    int altaQuedada(Quedada quedada);

    boolean deleteQuedadaById(Integer id);

    boolean deleteQuedada(Quedada quedada);

    void updateQuedada(Quedada quedada);

    List<Quedada> findAllQuedada();

    List<Quedada> getPendientesFinalizar();

    Quedada findById(Integer id);

    void flush();
}
