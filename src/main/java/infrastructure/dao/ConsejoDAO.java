package infrastructure.dao;


import domain.models.Consejo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("consejorepository")
public interface ConsejoDAO extends JpaRepository<Consejo, Integer> {

}
