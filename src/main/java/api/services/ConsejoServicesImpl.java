package api.services;

import api.dao.ConsejoDAO;
import entities.Consejo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("consejoServices")
@Transactional
public class ConsejoServicesImpl implements ConsejoServices {

    @Autowired
    ConsejoDAO consejoDAO;

    @Override
    public boolean altaConsejo(Consejo consejo) {
        return consejoDAO.save(consejo) != null;
    }

    @Override
    public boolean deleteConsejoById(Integer id) {
        consejoDAO.deleteById(id);
        return !consejoDAO.findById(id).isPresent();
    }

    @Override
    public void deleteConsejo(Consejo consejo) {
        consejoDAO.delete(consejo);
    }

    @Override
    public void updateConsejo(Consejo consejo) {
        consejoDAO.save(consejo);
    }

    @Override
    public List<Consejo> findAllConsejos() {
        return consejoDAO.findAll();
    }

    @Override
    public Consejo findById(Integer id) {
        Consejo consejo = null;
        Optional<Consejo> resultConsejo = consejoDAO.findById(id);
        if (resultConsejo.isPresent()) consejo = resultConsejo.get();
        return consejo;
    }
}
