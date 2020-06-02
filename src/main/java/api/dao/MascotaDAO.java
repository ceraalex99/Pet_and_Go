package api.dao;

import entities.Mascota;
import entities.MascotaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("mascotarepository")
public interface MascotaDAO extends JpaRepository<Mascota, MascotaId> {

}
