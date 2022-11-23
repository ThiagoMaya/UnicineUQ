package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionRepositorio extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombre  from Funcion f where f.codigo = :codigoFuncion")
    String nombrePelicula(Integer codigoFuncion);

    @Query("select c from Funcion c where c.codigo = ?1")
    Funcion obtenerPorCodigo(Integer codigo);

    @Query("select c from Funcion c where c.horario = ?1")
    Funcion obtenerPorHorario(Horario horario);

    @Query("select c from Funcion c where c.sala = ?1")
    List<Funcion> obtenerPorSala(Sala sala);

    @Query("select f from Funcion f where f.sala.teatro.codigo = :codigoTeatro")
    List<Funcion> listarFuncionesTeatro(Integer codigoTeatro);

    @Query("select f from Sala s join s.funciones f where s.codigo =:codigoSala and f.horario=:horario")
    List<Funcion> obtenerFuncionesSala(Integer codigoSala, Horario horario);

    @Query("select distinct f.pelicula from Funcion f where f.sala.teatro.ciudad.codigo =:codigoCiudad and f.pelicula.estado=:estadoPelicula")
    List<Pelicula> listarPeliculasEstado(Integer codigoCiudad, EstadoPelicula estadoPelicula);

    @Query("select distinct f from Funcion f where f.pelicula.codigo = :idPelicula and f.pelicula.estado <> 'NO_DISPONIBLE'")
    List<Funcion> obtenerFuncionesPelicula(Integer idPelicula);

    @Query("select t from Teatro t where t.ciudad.codigo=:codigoCiudad")
    List<Teatro> listarTeatrosCiudad(Integer codigoCiudad);

}

