package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class TeatroBean implements Serializable {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Value(value = "#{seguridadBean.personaActiva}")
    private Persona personaSesion;

    @Getter
    @Setter
    private Teatro teatro;

    @Getter
    @Setter
    private AdministradorTeatro adminTeatro;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Teatro> teatrosSeleccionados;


    @Getter
    @Setter
    private List<Teatro> teatros;

    @Getter @Setter
    private boolean editar;


    @PostConstruct
    public void inicializar() {

        teatro = new Teatro();
        editar = false;
        teatrosSeleccionados = new ArrayList<>();
        ciudades = adminTeatroServicio.listarCiudades();
        teatros = adminTeatroServicio.listarTeatros();
    }


    public void crearTeatro() {
        try {


              if(!editar) {

                  adminTeatro = (AdministradorTeatro) personaSesion;


                  teatro.setAdminTeatro(adminTeatro);
                 Teatro registro = adminTeatroServicio.crearTeatro(teatro);
                 teatros.add(registro);

                 teatro = new Teatro();

                 FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro creado con éxito");
                 FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
              }else{
                  adminTeatroServicio.actualizarTeatro(teatro);
                  FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro Actualizado con éxito");
                  FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
              }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }


    }

    public void crearTeatroDialogo(){
        this.teatro = new Teatro();
        editar=false;
    }

    public void eliminarTeatros(){

        try {
            for(Teatro t : teatrosSeleccionados) {
                    adminTeatroServicio.eliminarTeatro(t.getCodigo());
                    teatros.remove(t);
            }

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro eliminado con éxito");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            teatrosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }



    public String getMsjBorrar(){
        if(teatrosSeleccionados.isEmpty()){
            return "Borrar";
        }
        if(teatrosSeleccionados.size()==1){
            return "Borrar " + teatrosSeleccionados.size() + " elemento";
        }
        else{
            return "Borrar " + teatrosSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeInterfaz(){
       return editar ? "Actualizar teatro" : "Crear teatro";
    }

    public void seleccionarTeatro(Teatro teatroSeleccionado){
        this.teatro = teatroSeleccionado;
        editar = true;

    }





}
