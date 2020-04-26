package api.dao;

import entities.Mascota;
import entities.MascotaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("mascotarepository")
public interface MascotaDAO extends JpaRepository<Mascota, MascotaId> {

}
