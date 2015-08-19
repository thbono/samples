package com.thbono.jsffull.model.repository;

import com.thbono.jsffull.model.Aluno;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Tiago Bono
 * @since 2015-08-11
 */
@RequestScoped
public class AlunoRepository {

    private static final Logger LOG = Logger.getLogger(AlunoRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    protected AlunoRepository() {
    }

    public Optional<Aluno> findOne(final Long id) {
        Objects.requireNonNull(id, "Informe o id");
        return Optional.ofNullable(entityManager.find(Aluno.class, id));
    }

    public List<Aluno> findAll() {
        final CriteriaQuery<Aluno> criteria = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        criteria.select(criteria.from(Aluno.class));
        return entityManager.createQuery(criteria).getResultList();
    }

    public Long saveOrUpdate(final Aluno aluno) {
        Objects.requireNonNull(aluno, "Informe o aluno");
        LOG.info("Salvando " + aluno);

        if (aluno.isPersistido()) {
            entityManager.merge(aluno);
        } else {
            entityManager.persist(aluno);
        }

        return aluno.getId();
    }

    public void remove(final Long id) {
        Objects.requireNonNull(id, "Informe o id");
        LOG.info("Removendo aluno com id " + id);
        final Aluno aluno = entityManager.getReference(Aluno.class, id);
        entityManager.remove(aluno);
    }

}
