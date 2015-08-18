package com.thbono.jsffull.web.controller;

import com.thbono.jsffull.app.AlunoDelegate;
import com.thbono.jsffull.model.Aluno;
import com.thbono.jsffull.web.dto.AlunoDto;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * @author Tiago Bono
 * @since 2015-08-11
 */
@ManagedBean
@RequestScoped
public class AlunoController {

    private static final Logger LOG = Logger.getLogger(Aluno.class);

    private List<Aluno> alunos;

    private AlunoDto aluno;

    @Inject
    private AlunoDelegate delegate;

    @Inject
    private ControllerHelper helper;

    public AlunoController() {
    }

    @PostConstruct
    public void postConstruct() {
        alunos = Collections.emptyList();
        aluno = new AlunoDto();
    }

    public void listar() {
        alunos = delegate.findAll();
        if (!isPossuiAlunos()) {
            helper.addInfoMessage("Nenhum registro encontrado");
        }
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

    public boolean isPossuiAlunos() {
        return alunos != null && !alunos.isEmpty();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public AlunoDto getAluno() {
        return aluno;
    }
}
