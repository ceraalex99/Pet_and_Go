package api.services;

import api.dao.EventoDAO;
import api.dao.MascotaDAO;
import entities.CalendarioId;
import entities.Evento;
import entities.Mascota;
import entities.MascotaId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("eventoServices")
@Transactional
public class EventoServicesImpl implements EventoServices {

    @Autowired
    EventoDAO eventoDAO;

    @Override
    public boolean altaEvento(Evento evento) {
        return eventoDAO.save(evento) != null;
    }

    @Override
    public boolean deleteEventoById(CalendarioId id) {
        eventoDAO.deleteById(id);
        return !eventoDAO.findById(id).isPresent();
    }

    @Override
    public void deleteEvento(Evento evento) {
        eventoDAO.delete(evento);
    }

    @Override
    public void updateEvento(Evento evento) {
        eventoDAO.save(evento);
    }

    @Override
    public List findAllEventos() {
        return eventoDAO.findAll();
    }

    @Override
    public Evento findById(CalendarioId id) {
        Evento evento = null;
        Optional<Evento> resultEvento = eventoDAO.findById(id);
        if (resultEvento.isPresent()) evento = resultEvento.get();
        return evento;
    }
}
