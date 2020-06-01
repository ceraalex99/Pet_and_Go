package api.dao;

import entities.Quedada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@Repository("quedadarepository")
public interface QuedadaDAO extends JpaRepository<Quedada, Serializable> {

    @Query("SELECT q FROM Quedada as q WHERE q.fechaQuedada <= current_date and q.finalizada = false")
    List<Quedada> getPendientesFinalizar();

}
