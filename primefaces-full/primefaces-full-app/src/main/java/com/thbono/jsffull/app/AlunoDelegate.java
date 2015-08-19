package com.thbono.jsffull.app;

import com.thbono.jsffull.model.Aluno;
import com.thbono.jsffull.model.repository.AlunoRepository;
import com.thbono.jsffull.model.service.AlunoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Tiago Bono
 * @since 2015-08-13
 */
@RequestScoped
public class AlunoDelegate {

    @Inject
    private AlunoRepository repository;

    @Inject
    private AlunoService service;

    protected AlunoDelegate() {
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<Aluno> findOne(final Long id) {
        Objects.requireNonNull(id, "Informe o id");
        return repository.findOne(id);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Aluno> findAll() {
        return repository.findAll();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Long saveOrUpdate(final Aluno aluno) {
        Objects.requireNonNull(aluno, "Informe o aluno");
        return service.saveOrUpdate(aluno);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void remove(final Long id) {
        Objects.requireNonNull(id, "Informe o id");
        service.remove(id);
    }

}
