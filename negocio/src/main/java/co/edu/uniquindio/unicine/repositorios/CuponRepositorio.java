package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepositorio extends JpaRepository<Cupon, Integer> {


    @Query("select c from Cupon c where c.codigo= :codigo")
    Cupon obtener(Integer codigo);

    @Query("select c from Cupon c where c.criterio= :bienvenida")
    Cupon obtenerCuponBienvenida(String bienvenida );

}
