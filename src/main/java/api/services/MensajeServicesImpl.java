package api.services;


import api.dao.MensajeDAO;
import entities.Mensaje;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mensajeServices")
@Transactional
public class MensajeServicesImpl implements MensajeServices{

    @Autowired
    MensajeDAO mensajeDAO;

    @Override
    public boolean altaMensaje(Mensaje mensaje){
        return mensajeDAO.save(mensaje) != null;
    }

    @Override
    public List<Mensaje> findBySender(Usuario sender) {
        List<Mensaje> mensaje = new ArrayList<>();
        Optional<List<Mensaje>> resultMensaje = mensajeDAO.findBySender(sender);
        if(resultMensaje.isPresent()) mensaje = resultMensaje.get();
        return mensaje;
    }

    @Override
    public List<Mensaje> findByReceiver(Usuario receiver) {
        List<Mensaje> mensaje = new ArrayList<>();
        Optional<List<Mensaje>> resultMensaje = mensajeDAO.findByReceiver(receiver);
        if(resultMensaje.isPresent()) mensaje = resultMensaje.get();
        return mensaje;
    }
}
