package com.thbono.jsffull.web.controller;

import com.thbono.jsffull.app.AlunoDelegate;
import com.thbono.jsffull.model.Aluno;
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
public class AlunoListController {

    private static final Logger LOG = Logger.getLogger(AlunoListController.class);

    private List<Aluno> alunos;

    @Inject
    private AlunoDelegate delegate;

    @Inject
    private ControllerHelper helper;

    public AlunoListController() {
    }

    @PostConstruct
    public void postConstruct() {
        alunos = Collections.emptyList();
    }

    public void listar() {
        alunos = delegate.findAll();
        if (!isPossuiAlunos()) {
            helper.addInfoMessage("Nenhum registro encontrado");
        }
    }

    public boolean isPossuiAlunos() {
        return alunos != null && !alunos.isEmpty();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

}
