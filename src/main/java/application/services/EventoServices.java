package application.services;

import domain.models.Evento;

public interface EventoServices {
    boolean altaEvento(Evento evento);

    boolean deleteEventoById(Integer id);

    void updateEvento(Evento evento);

    Evento findById(Integer id);
}