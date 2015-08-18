package com.thbono.jsffull.model.repository;

import com.thbono.jsffull.model.Aluno;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Objects;

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

    public List<Aluno> findAll() {
        final CriteriaQuery<Aluno> criteria = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        criteria.select(criteria.from(Aluno.class));
        return entityManager.createQuery(criteria).getResultList();
    }

    public void saveOrUpdate(final Aluno aluno) {
        Objects.requireNonNull(aluno, "Informe o aluno");
        LOG.info("Salvando " + aluno);
        entityManager.persist(aluno);
    }

}
