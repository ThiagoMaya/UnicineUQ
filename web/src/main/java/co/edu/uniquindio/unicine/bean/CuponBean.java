package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
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

@ViewScoped
@Component
public class CuponBean implements Serializable {

    @Getter
    @Setter
    private Cupon cupon;

    @Autowired
    private AdminServicio adminServicio;

    @Getter
    @Setter
    private List<Cupon> cuponesSeleccionados;


    @Getter
    @Setter
    private List<Cupon> cupones;

    @Getter @Setter
    private boolean editar;

    @PostConstruct
    public void inicializar(){

        cupon = new Cupon();
        cuponesSeleccionados = new ArrayList<>();
        editar = false;

        cupones = adminServicio.listarCupones();

    }

    public void registrarCupon(){

        try {
            if (!editar) {

                    Cupon registro = adminServicio.crearCupon(cupon);
                    cupones.add(registro);

                    cupon = new Cupon();

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupón creado con éxito");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);



            } else {
                adminServicio.actualizarCupon(cupon);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon Actualizado con éxito");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);

            }
        }catch(Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void crearCuponDialogo(){
        this.cupon = new Cupon();
        editar=false;
    }


    public void eliminarCupones(){

        try {
            for(Cupon c : cuponesSeleccionados) {
                adminServicio.eliminarCupon(c.getCodigo());
                cupones.remove(c);
            }

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon eliminado con éxito");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            cuponesSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMsjBorrar(){

        if(cuponesSeleccionados.isEmpty()){
            return "Borrar";
        }
        if(cuponesSeleccionados.size()==1){
            return "Borrar " + cuponesSeleccionados.size() + " elemento";
        }
        else{
            return "Borrar " + cuponesSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeInterfaz(){ return editar ? "Actualizar Cupon" : "Crear Cupon";
    }

    public void seleccionarCupon(Cupon cupon){
        this.cupon = cupon;
        editar = true;

    }



}
