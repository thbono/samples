package com.thbono.jsffull.web.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Objects;

/**
 * @author Tiago Bono
 * @since 2015-08-11
 */
@ApplicationScoped
public class ControllerHelper {

    protected ControllerHelper() {
    }

    public void addInfoMessage(final String message) {
        Objects.requireNonNull(message, "Informe a mensagem");
        final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public void addWarnMessage(final String message) {
        Objects.requireNonNull(message, "Informe a mensagem");
        final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public void addErrorMessage(final String message) {
        Objects.requireNonNull(message, "Informe a mensagem");
        final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

}
