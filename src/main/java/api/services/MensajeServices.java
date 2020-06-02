package api.services;

import entities.Mensaje;
import entities.Usuario;

import java.util.List;

public interface MensajeServices {
    boolean altaMensaje(Mensaje mensaje);
    List<Mensaje> findBySender(Usuario sender);
    List<Mensaje> findByReceiver(Usuario receiver);
}
