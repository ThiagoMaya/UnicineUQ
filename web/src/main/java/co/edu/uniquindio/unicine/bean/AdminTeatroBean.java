package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class AdminTeatroBean implements Serializable {

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter
    @Setter
    private AdministradorTeatro adminTeatro;

    @Getter @Setter
    private String confirmacionPassword;

    @Getter
    @Setter
    private List<AdministradorTeatro> administradoresSeleccionados;


    @Getter
    @Setter
    private List<AdministradorTeatro> administradoresTeatros;

    @Getter @Setter
    private boolean editar;

    @PostConstruct
    public void inicializar(){

        adminTeatro = new AdministradorTeatro();
        administradoresSeleccionados = new ArrayList<>();
        editar = false;

        administradoresTeatros = adminServicio.listarAdministradores();

    }



    public void registrarAdmin(){

        try {
            if (!editar) {
                if (adminTeatro.getContrasena().equals(confirmacionPassword)) {

                    AdministradorTeatro registro = adminServicio.crearAdministrador(adminTeatro);
                    administradoresTeatros.add(registro);

                    adminTeatro = new AdministradorTeatro();

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                } else {

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Las contraseñas no coinciden");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);

                }
            } else {
                adminServicio.actualizarAdministrador(adminTeatro);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Admin Actualizado con éxito");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);

            }
        }catch(Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void crearAdminDialogo(){
        this.adminTeatro = new AdministradorTeatro();
        editar=false;
    }


    public void eliminarAdministradores(){

        try {
            for(AdministradorTeatro a : administradoresSeleccionados) {
                adminServicio.eliminarAdministrador(a.getCedula());
                administradoresTeatros.remove(a);
            }

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Admin eliminado con éxito");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            administradoresSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMsjBorrar(){

        if(administradoresSeleccionados.isEmpty()){
            return "Borrar";
        }
        if(administradoresSeleccionados.size()==1){
            return "Borrar " + administradoresSeleccionados.size() + " elemento";
        }
        else{
            return "Borrar " + administradoresSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeInterfaz(){ return editar ? "Actualizar Administrador" : "Crear Administrador";
    }

    public void seleccionarAdministrador(AdministradorTeatro administradorTeatro){
        this.adminTeatro = administradorTeatro;
        editar = true;

    }


}



