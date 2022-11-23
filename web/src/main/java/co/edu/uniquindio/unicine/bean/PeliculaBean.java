package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter @Setter
    private Pelicula pelicula;

    @Getter
    @Setter
    private List<Pelicula> peliculasSeleccionadas;


    @Getter
    @Setter
    private List<Pelicula> peliculas;

    @Getter @Setter
    private boolean editar;

    @Getter @Setter
    private List<Genero> generos;

    private Map<String, String> imagenes;


    @PostConstruct
    public void inicializar(){

        pelicula = new Pelicula();
        generos= Arrays.asList(Genero.values());
        peliculasSeleccionadas = new ArrayList<>();
        editar = false;
        peliculas = adminServicio.listarPeliculas();
        imagenes= new HashMap<>();
    }


    public void crearPelicula()  {
        try {
            if(!editar) {
                if (!imagenes.isEmpty()) {
                    pelicula.setImagenes(imagenes);
                    pelicula.setEstado(EstadoPelicula.CARTELERA);
                    adminServicio.crearPelicula(pelicula);

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Película creada con éxito");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                } else {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario adjuntar al menos una imágen");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                }
            }
            else {
                adminServicio.actualizarPelicula(pelicula);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Película Actualizada con éxito");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            throw new RuntimeException(e);
        }
    }

    public void crearPeliculaDialogo(){
        this.pelicula = new Pelicula();
        editar=false;
    }

    public void eliminarPeliculas(){

        try {
            for(Pelicula p : peliculasSeleccionadas) {
                adminServicio.eliminarPelicula(p.getNombre());
                peliculas.remove(p);
            }

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Película eliminado con éxito");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            peliculasSeleccionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }


    public String getMsjBorrar(){
        if(peliculasSeleccionadas.isEmpty()){
            return "Borrar";
        }
        if(peliculasSeleccionadas.size()==1){
            return "Borrar " + peliculasSeleccionadas.size() + " elemento";
        }
        else{
            return "Borrar " + peliculasSeleccionadas.size() + " elementos";
        }
    }

    public String getMensajeInterfaz(){ return editar ? "Actualizar Película" : "Crear Película";
    }

    public void seleccionarPelícula(Pelicula pelicula1){
        this.pelicula = pelicula1;
        editar = true;

    }


    public void subirImagenes(FileUploadEvent event){

        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile,"peliculas");
            imagenes.put(resultado.get("public_id").toString(),resultado.get("url").toString());



        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }


    }

    private File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();
        return file;
    }



}
