package co.edu.uniquindio.unicine.bean;


import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {


    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, contrasena;

    @Getter @Setter
    private Persona personaActiva;

    @Getter @Setter
    private Cliente clienteIngresado;

    @Getter @Setter
    private Administrador administradorIngresado;

    @Getter @Setter
    private AdministradorTeatro administradorTeatroIngresado;
    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private AdministradorTeatro administradorTeatro;

    @Getter @Setter
    private Administrador administrador;

    private Map<String, String> imagenPerfil;

    // 0: Admin, 1: AdminTeatro, 2: Cliente
    @Getter @Setter
    private int tipoSesion;

    @PostConstruct
    public void init() {
        autenticado = false;

    }

    public String iniciarSesionCliente() {
        try {
            personaActiva = clienteServicio.inciarSesion(email, contrasena);

            if(personaActiva != null) {
                tipoSesion = 2;
                autenticado = true;
                cliente = (Cliente) personaActiva;

                if(ciudad != null)
                    return "/index?faces-redirect=true&amp;city=" + ciudad.getCodigo();
                else
                    return "/index?faces-redirect=true";
            }

        } catch (Exception e) {

        }
        return "";
    }
    public String iniciarSesionAdmins() {
        try {

            personaActiva = adminServicio.iniciarSesion(email, contrasena);
            if(personaActiva != null) {
                tipoSesion = 0;
                administrador = (Administrador) personaActiva;
                autenticado = true;

                return "/admin/index_admin?faces-redirect=true";
            }
            else {
                personaActiva = adminTeatroServicio.iniciarSesion(email, contrasena);
                if(personaActiva != null) {
                    tipoSesion = 1;
                    administradorTeatro = (AdministradorTeatro) personaActiva;
                    ciudad = adminServicio.obtenerCiudad(1);
                    autenticado = true;

                    return "/admin_teatro/index_admin_teatro?faces-redirect=true";
                }

            }

        } catch (Exception e) {
            mostrarError(e);
        }

        return "";
    }


    public String cerrarSesion() {
        int tipoSesion = getTipoSesion();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        if(tipoSesion == 2)
            return "/index?faces-redirect=true";

        return "/login_admin?faces-redirect=true";
    }


    public String seleccionarCiudad(Ciudad ciudad){
        if(ciudad != null) {
            this.ciudad = ciudad;

            return "/index?faces-redirect=true&amp;city=" + ciudad.getCodigo();
        }

        return "";
    }

    public String restablecerCiudad() {
        ciudad = null;
        return "/index?faces-redirect=true";
    }

    public void cambiarContrasena() {
        try {
            if(email == null || email.isEmpty())
                mostrarError( new Exception("Ingresa un correo para restablecer tu contraseña") );
            else {
                clienteServicio.cambiarContrasena(email);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmacion", "Te hemos enviado un email de confirmacion. Revisalo para cambiar tu contraseña.");
                PrimeFaces.current().dialog().showMessageDynamic(fm);
            }
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public void actualizarImagenPerfil(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "usuarios");
            imagenPerfil.clear();
            imagenPerfil.put( resultado.get("public_id").toString(), resultado.get("url").toString() );
        } catch(Exception e) {
            mostrarError(e);
        }
    }

    private File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();

        return file;
    }

    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        PrimeFaces.current().dialog().showMessageDynamic(fm);
    }





}
