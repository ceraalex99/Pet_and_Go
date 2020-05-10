package api.services;

import entities.Mensaje;
import entities.Usuario;

import java.util.List;

public interface MensajeServices {
    boolean altaMensaje(Mensaje mensaje);
    List findBySender(Usuario sender);
    List findByReceiver(Usuario receiver);
}
