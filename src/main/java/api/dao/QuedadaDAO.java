package api.dao;

import entities.Quedada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository("quedadarepository")
public interface QuedadaDAO extends JpaRepository<Quedada, Serializable> {

}
