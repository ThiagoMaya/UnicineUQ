package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.entidades.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionRepositorio extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombre  from Funcion f where f.Codigo = :codigoFuncion")
    String nombrePelicula(Integer codigoFuncion);

    @Query("select c from Funcion c where c.Codigo = ?1")
    Funcion obtenerPorCodigo(Integer codigo);

    @Query("select c from Funcion c where c.horario = ?1")
    Funcion obtenerPorHorario(Horario horario);

    @Query("select c from Funcion c where c.sala = ?1")
    Funcion obtenerPorSala(Sala sala);

}
