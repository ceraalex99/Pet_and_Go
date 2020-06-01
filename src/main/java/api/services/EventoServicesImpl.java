package api.services;

import api.dao.EventoDAO;
import entities.Evento;
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
    public boolean deleteEventoById(Integer id) {
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
    public List<Evento> findAllEventos() {
        return eventoDAO.findAll();
    }

    @Override
    public Evento findById(Integer id) {
        Evento evento = null;
        Optional<Evento> resultEvento = eventoDAO.findById(id);
        if (resultEvento.isPresent()) evento = resultEvento.get();
        return evento;
    }
}
