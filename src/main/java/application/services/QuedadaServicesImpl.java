package application.services;

import infrastructure.dao.QuedadaDAO;
import domain.models.Quedada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("PerreParadaServices")
@Transactional
public class QuedadaServicesImpl implements QuedadaServices {

    @Autowired
    private QuedadaDAO quedadaDAO;

    @Override
    public int altaQuedada(Quedada quedada) {
        quedadaDAO.save(quedada);
        return quedada.getId();
    }

    @Override
    public boolean deleteQuedadaById(Integer id) {
        quedadaDAO.deleteById(id);
        return !quedadaDAO.findById(id).isPresent();
    }

    @Override
    public boolean deleteQuedada(Quedada quedada) {
        quedadaDAO.delete(quedada);
        return !quedadaDAO.findById(quedada.getId()).isPresent();
    }

    @Override
    public void updateQuedada(Quedada quedada) {
        quedadaDAO.save(quedada);
    }

    @Override
    public List<Quedada> findAllQuedada() {
        return quedadaDAO.findAll();
    }

    @Override
    public List<Quedada> getPendientesFinalizar() {
        return quedadaDAO.getPendientesFinalizar();
    }

    @Override
    public Quedada findById(Integer id) {
        Quedada           quedada       = null;
        Optional<Quedada> resultQuedada = quedadaDAO.findById(id);
        if (resultQuedada.isPresent()) quedada = resultQuedada.get();
        return quedada;
    }

    @Override
    public void flush() {
        quedadaDAO.flush();
    }

}
