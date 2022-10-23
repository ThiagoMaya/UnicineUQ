package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepositorio extends JpaRepository<Horario, Integer> {

    @Query("select c from Horario c where c.Codigo = ?1")
    Horario obtenerPorCodigo(Integer codigo);

}
