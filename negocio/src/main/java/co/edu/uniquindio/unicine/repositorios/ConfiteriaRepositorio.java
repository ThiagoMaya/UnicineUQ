package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiteriaRepositorio extends JpaRepository<Confiteria, Integer> {


    @Query("select c from Confiteria c where c.idProducto= :codigo")
    Confiteria obtener(Integer codigo);
}
