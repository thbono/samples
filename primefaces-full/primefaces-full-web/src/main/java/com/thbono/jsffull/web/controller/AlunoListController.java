package com.thbono.jsffull.web.controller;

import com.google.common.collect.Lists;
import com.thbono.jsffull.app.AlunoDelegate;
import com.thbono.jsffull.model.Aluno;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Tiago Bono
 * @since 2015-08-11
 */
@ManagedBean
@RequestScoped
public class AlunoListController {

    private List<Aluno> alunos;

    @Inject
    private AlunoDelegate delegate;

    public AlunoListController() {
        alunos = Lists.newArrayList();
    }

    @PostConstruct
    public void postConstruct() {
        alunos = delegate.findAll();
    }

    public boolean isPossuiAlunos() {
        return !alunos.isEmpty();
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

}
