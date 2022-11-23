package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.Cupon;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class ConfiteriaBean implements Serializable {

    @Autowired
    private CloudinaryServicio cloudinaryServicio;
    @Getter
    @Setter
    private Confiteria confiteria;

    @Autowired
    private AdminServicio adminServicio;

    @Getter
    @Setter
    private List<Confiteria> confiteriasSeleccionadas;


    @Getter
    @Setter
    private List<Confiteria> confiterias;


    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private boolean editar;

    @PostConstruct
    public void inicializar(){

        confiteria = new Confiteria();
        confiteriasSeleccionadas = new ArrayList<>();
        editar = false;

        confiterias = adminServicio.listarConfiteria();

    }

    public void crearProductos(){

        try {
            if (!editar) {
                confiteria.setEstado("Disponible");
                Confiteria registro = adminServicio.crearComestible(confiteria);
                confiterias.add(registro);

                confiteria = new Confiteria();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto creado con éxito");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);



            } else {
                adminServicio.actualizarComestible(confiteria);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto Actualizado con éxito");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);

            }
        }catch(Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }


    public void crearConfiteriaDialogo(){
        this.confiteria = new Confiteria();
        editar=false;
    }


    public void eliminarProductos(){

        try {
            for(Confiteria c : confiteriasSeleccionadas) {
                adminServicio.eliminarComestible(c.getIdProducto());
                confiterias.remove(c);
            }

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon eliminado con éxito");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            confiteriasSeleccionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMsjBorrar(){

        if(confiteriasSeleccionadas.isEmpty()){
            return "Borrar";
        }
        if(confiteriasSeleccionadas.size()==1){
            return "Borrar " + confiteriasSeleccionadas.size() + " elemento";
        }
        else{
            return "Borrar " + confiteriasSeleccionadas.size() + " elementos";
        }
    }

    public String getMensajeInterfaz(){ return editar ? "Actualizar producto" : "Crear producto";
    }

    public void seleccionarCupon(Confiteria confiteria){
        this.confiteria = confiteria;
        editar = true;

    }

    public void subirImagenes(FileUploadEvent event){

        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile,"confiterias");
            confiteria.setImagen(resultado.get("url").toString());

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

