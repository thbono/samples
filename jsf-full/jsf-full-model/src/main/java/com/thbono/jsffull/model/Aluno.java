package com.thbono.jsffull.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Tiago Bono
 * @since 2015-08-11
 */
@Entity
@Table(name = "ALUNO")
public class Aluno {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RA", nullable = false)
    private Integer ra;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    public static Builder newBuilder() {
        return new Builder();
    }

    protected Aluno() {
    }

    public Aluno(final Integer ra, final String nome) {
        this.ra = Objects.requireNonNull(ra, "Informe o RA");
        this.nome = Objects.requireNonNull(nome, "Informe o nome");

        Preconditions.checkArgument(Range.closed(10000, 99999).contains(ra),
                "O RA deve estar entre 10000 e 99999");
        Preconditions.checkArgument(Range.closed(3, 100).contains(nome.length()),
                "O nome deve ter entre 3 e 100 caracteres");
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ra", ra)
                .add("nome", nome)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluno)) return false;

        final Aluno aluno = (Aluno) o;

        return id.equals(aluno.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Integer getRa() {
        return ra;
    }

    public String getNome() {
        return nome;
    }

    public static class Builder {
        private Integer ra;
        private String nome;

        private Builder() {
        }

        public Builder withRa(final Integer ra) {
            this.ra = ra;
            return this;
        }

        public Builder withNome(final String nome) {
            this.nome = nome;
            return this;
        }

        public Aluno build() {
            return new Aluno(ra, nome);
        }
    }

}
