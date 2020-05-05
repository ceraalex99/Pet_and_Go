package api.services;

import entities.CalendarioId;
import entities.Evento;
import entities.Mascota;
import entities.MascotaId;

import java.util.List;

public interface EventoServices {
    boolean altaEvento(Evento evento);
    boolean deleteEventoById(CalendarioId id);
    void deleteEvento(Evento evento);
    void updateEvento(Evento evento);
    List findAllEventos();
    Evento findById(CalendarioId id);
}