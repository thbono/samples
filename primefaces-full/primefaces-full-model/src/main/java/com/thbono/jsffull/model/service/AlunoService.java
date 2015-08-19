package com.thbono.jsffull.model.service;

import com.thbono.jsffull.model.Aluno;
import com.thbono.jsffull.model.repository.AlunoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;

/**
 * @author Tiago Bono
 * @since 2015-08-13
 */
@RequestScoped
public class AlunoService {

    @Inject
    private AlunoRepository repository;

    protected AlunoService() {
    }

    public Long saveOrUpdate(final Aluno aluno) {
        Objects.requireNonNull(aluno, "Informe o aluno");
        return repository.saveOrUpdate(aluno);
    }

    public void remove(final Long id) {
        Objects.requireNonNull(id, "Informe o id");
        repository.remove(id);
    }

}
