package infrastructure.dao;

import domain.models.Mensaje;
import domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository("mensajerepository")
public interface MensajeDAO extends JpaRepository<Mensaje, Serializable> {

    Optional<List<Mensaje>> findBySender(Usuario sender);

    Optional<List<Mensaje>> findByReceiver(Usuario receiver);
}
