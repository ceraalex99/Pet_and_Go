package api.services;

import entities.Evento;

import java.util.List;

public interface EventoServices {
    boolean altaEvento(Evento evento);
    boolean deleteEventoById(Integer id);
    void deleteEvento(Evento evento);
    void updateEvento(Evento evento);
    List<Evento> findAllEventos();
    Evento findById(Integer id);
}