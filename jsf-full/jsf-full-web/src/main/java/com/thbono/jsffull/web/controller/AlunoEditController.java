package com.thbono.jsffull.web.controller;

import com.thbono.jsffull.app.AlunoDelegate;
import com.thbono.jsffull.model.Aluno;
import com.thbono.jsffull.web.dto.AlunoDto;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

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
    }

    @PostConstruct
    public void postConstruct() {
        aluno = new AlunoDto();
    }

    public String salvar() {
        try {
            delegate.saveOrUpdate(Aluno.newBuilder()
                    .withRa(aluno.getRa())
                    .withNome(aluno.getNome())
                    .build());

            helper.addInfoMessage("Aluno salvo com sucesso");
            return "list";
        } catch (IllegalArgumentException | NullPointerException e ) {
            LOG.warn(e.getMessage(), e);
            helper.addWarnMessage(String.format("Dados inválidos (%s)", e.getMessage()));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            helper.addErrorMessage("Falha ao salvar aluno, contate o administrador do sistema");
        }

        return null;
    }

    public AlunoDto getAluno() {
        return aluno;
    }

}
