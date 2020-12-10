package infrastructure.dao;

import domain.models.Mascota;
import domain.models.MascotaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("mascotarepository")
public interface MascotaDAO extends JpaRepository<Mascota, MascotaId> {

}
