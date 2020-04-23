package api.services;

import api.dao.QuedadaDAO;
import entities.Mascota;
import entities.Quedada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("PerreParadaServices")
@Transactional

public class QuedadaServicesImpl implements QuedadaServices {
    @Autowired
    private QuedadaDAO quedadaDAO;

    @Override
    public int altaQuedada(Quedada quedada) {
        return quedadaDAO.altaQuedada(quedada);
    }

    @Override
    public boolean deleteQuedadaById(Integer id) {
        return quedadaDAO.deleteQuedadaById(id);
    }

    @Override
    public boolean deleteQuedada(Quedada quedada) {
        return quedadaDAO.deleteQuedada(quedada);
    }

    @Override
    public void updateQuedada(Quedada quedada) {
        quedadaDAO.updateQuedada(quedada);
    }

    @Override
    public List<Quedada> findAllQuedada() {
        return quedadaDAO.findAllQuedada();
    }

    @Override
    public Quedada findById(Integer id) {
        return quedadaDAO.findById(id);
    }


}
