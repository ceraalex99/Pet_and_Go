package application.services;

import domain.models.Mensaje;
import domain.models.Usuario;

import java.util.List;

public interface MensajeServices {
    boolean altaMensaje(Mensaje mensaje);

    List<Mensaje> findBySender(Usuario sender);

    List<Mensaje> findByReceiver(Usuario receiver);
}
