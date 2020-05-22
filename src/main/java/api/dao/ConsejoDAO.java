package api.dao;


import entities.Consejo;
import entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("consejorepository")
public interface ConsejoDAO extends JpaRepository<Consejo, Integer> {

}
