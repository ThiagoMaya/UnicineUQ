package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class DetallePeliculaBean implements Serializable {

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Value("#{param['pelicula_id']}")
    private String peliculaCodigo;

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private  Ciudad ciudad;

    @Getter @Setter
    private List<Teatro> teatros;
    @Getter @Setter
    private Pelicula pelicula;
    @Getter @Setter

    private List<Funcion> funcionesPelicula;

    @PostConstruct
    public void inicializar(){

            try {
                if(peliculaCodigo!=null) {
                    pelicula = adminServicio.obtenerPelicula( Integer.valueOf(peliculaCodigo) );
                    funcionesPelicula = adminServicio.obtenerFuncionesPelicula( Integer.valueOf(peliculaCodigo) );
                    ciudades = adminTeatroServicio.listarCiudades();
                    teatros = adminTeatroServicio.listarTeatros();

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }

    public void elegirCiudad(){

        try {
            if(ciudad!= null) {
                teatros = adminTeatroServicio.listarTeatrosCiudad(ciudad.getCodigo());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String comprar(Funcion funcion) {
        if(funcion != null) {
            System.out.println(funcion.getCodigo());

            return "/cliente/compra.xhtml?faces-redirect=true&amp;funcion_id=" + funcion.getCodigo();
        }

        return "";
    }

}
