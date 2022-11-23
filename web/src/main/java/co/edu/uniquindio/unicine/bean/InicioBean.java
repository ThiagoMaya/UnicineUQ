package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable{



    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Pelicula> peliculasCartelera;

    @Getter @Setter
    private  Ciudad ciudad;

    @Getter @Setter
    private boolean teatroSeleccionado;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Pelicula> peliculasProximas;

    @Getter @Setter
    private List<String> imagenes;


    @PostConstruct
    public void  inicializar(){

        try {
            imagenes=new ArrayList<>();

            peliculasCartelera = clienteServicio.buscarPeliculaEstado(EstadoPelicula.CARTELERA);
            peliculasProximas = clienteServicio.buscarPeliculaEstado(EstadoPelicula.PREVENTA);
            ciudades = adminTeatroServicio.listarCiudades();

            imagenes.add("https://res.cloudinary.com/thiagomaya/image/upload/v1668564849/unicine/peliculas/dbz_ejgqhj.jpg");
            imagenes.add("https://res.cloudinary.com/thiagomaya/image/upload/v1668564843/unicine/peliculas/scarface_cytiwk.jpg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void elegirCiudad(){

        try {
            if(ciudad!= null) {
                peliculasCartelera = clienteServicio.listarPeliculasEstado(ciudad.getCodigo(), EstadoPelicula.CARTELERA);
                peliculasProximas = clienteServicio.listarPeliculasEstado(ciudad.getCodigo(), EstadoPelicula.PREVENTA);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void seleccionarTeatro() {
        if(teatro != null) {
            teatroSeleccionado = true;

        }
    }

}
