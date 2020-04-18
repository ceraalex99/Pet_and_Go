package api.services;

import api.dao.PerreParadaDAO;
import entities.PerreParada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("PerreParadaServices")
@Transactional

public class PerreParadaServicesImpl implements PerreParadaServices {
    @Autowired
    private PerreParadaDAO perreParadaDAO;

    @Override
    public boolean altaPerreParada(PerreParada perreParada) {
        return perreParadaDAO.altaPerreParada(perreParada);
    }

    @Override
    public boolean deletePerreParadaById(Integer id) {
        return perreParadaDAO.deletePerreParadaById(id);
    }

    @Override
    public boolean deletePerreParada(PerreParada perreParada) {
        return perreParadaDAO.deletePerreParada(perreParada);
    }

    @Override
    public void updatePerreParada(PerreParada perreParada) {
        perreParadaDAO.updatePerreParada(perreParada);
    }

    @Override
    public List<PerreParada> findAllPerreParada() {
        return perreParadaDAO.findAllPerreParada();
    }

    @Override
    public PerreParada findById(Integer id) {
        return perreParadaDAO.findById(id);
    }

}
