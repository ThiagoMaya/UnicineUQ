package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeatroRepositorio extends JpaRepository<Teatro,Integer> {

    @Query("select t from Teatro t where t.ciudad.nombre=:nombreCiudad")
    List<Teatro> listar(String nombreCiudad);

    Optional<Teatro> findByNombre(String nombre);

/*
    @Query("select distinct f.pel from Funcion f where f.sala.teatro.ciudad.id = :idCiudad")
    List<Teatro> a(Integer codigoPelicula);

    @Query("select distinct  from Pelicula p where f.sala.teatro.ciudad.id = :idCiudad")
    List<Teatro> obtenerTeatrosCiudad(Integer codigoPelicula);
*/


}
