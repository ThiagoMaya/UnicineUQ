package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer> {

    @Query("select p from Pelicula p where LOWER(p.nombre) like concat('%', :nombre, '%')")
    List<Pelicula> buscarPeliculaNombre(String nombre);

    @Query("select p from Pelicula p where p.estado = :estado")
    List<Pelicula> buscarPeliculaEstado(EstadoPelicula estado);

    @Query("select p from Pelicula p where p.generos = :genero")
    List<Pelicula> buscarPeliculaGenero(List<Genero> genero);

    Optional<Pelicula> findByNombre(String nombre);
}

