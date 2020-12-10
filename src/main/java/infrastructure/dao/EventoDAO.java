package infrastructure.dao;


import domain.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("eventorepository")
public interface EventoDAO extends JpaRepository<Evento, Integer> {

}
