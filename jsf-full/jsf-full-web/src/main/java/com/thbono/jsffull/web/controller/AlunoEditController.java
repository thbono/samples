package com.thbono.jsffull.web.controller;

import com.thbono.jsffull.app.AlunoDelegate;
import com.thbono.jsffull.model.Aluno;
import com.thbono.jsffull.web.dto.AlunoDto;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.Optional;

/**
 * @author Tiago Bono
 * @since 2015-08-11
 */
@ManagedBean
@RequestScoped
public class AlunoEditController {

    private static final Logger LOG = Logger.getLogger(AlunoEditController.class);

    private AlunoDto aluno;

    @Inject
    private AlunoDelegate delegate;

    @Inject
    private ControllerHelper helper;

    public AlunoEditController() {
        aluno = new AlunoDto();
    }

    @PostConstruct
    public void postConstruct() {
        tratarParametroMensagemSucesso();
        tratarParametroId();
    }

    public String salvar() {
        try {
            final Long id = delegate.saveOrUpdate(aluno.buildAluno());
            return redirecionarParaEdicao(id);
        } catch (IllegalArgumentException | NullPointerException e) {
            LOG.warn(e.getMessage(), e);
            helper.addWarnMessage(e.getMessage());
        } catch (Exception e) {
            Optional<ConstraintViolationException> c = helper.findConstraintViolationException(e);
            if (c.isPresent()) {
                LOG.warn(e.getMessage(), e);
                helper.addWarnMessage(String.format("Já existe um aluno cadastrado com o RA (%d)", aluno.getRa()));
            } else {
                LOG.error(e.getMessage(), e);
                helper.addErrorMessage(
                        "Falha ao salvar aluno, por favor entre em contato com o administrador do sistema");
            }
        }

        return null;
    }

    public String excluir() {
        try {
            delegate.remove(aluno.getId());
            return redirecionarParaEdicao("new");
        } catch (IllegalArgumentException | NullPointerException e) {
            LOG.warn(e.getMessage(), e);
            helper.addWarnMessage(e.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            helper.addErrorMessage(
                    "Falha ao excluir aluno, por favor entre em contato com o administrador do sistema");
        }

        return null;
    }

    public AlunoDto getAluno() {
        return aluno;
    }

    private void tratarParametroMensagemSucesso() {
        final Optional<String> msg = helper.getUrlParam("r");
        if (msg.isPresent() && msg.get().equals("true")) {
            helper.addInfoMessage("Operação efetuada com sucesso");
        }
    }

    private void tratarParametroId() {
        final Optional<String> id = helper.getUrlParam("id");
        if (!id.isPresent() || id.get().equals("new")) {
            return;
        }

        try {
            final Optional<Aluno> aluno = delegate.findOne(Long.valueOf(id.get()));
            if (aluno.isPresent()) {
                this.aluno = new AlunoDto(aluno.get());
            }
        } catch (NumberFormatException e) {
            LOG.warn(e.getMessage(), e);
        } catch (ReflectiveOperationException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private String redirecionarParaEdicao(final Object id) {
        return String.format("edit?id=%s&r=true&faces-redirect=true", id.toString());
    }

}
