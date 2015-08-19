package com.thbono.jsffull.web.dto;

import com.thbono.jsffull.model.Aluno;
import org.apache.commons.beanutils.BeanUtilsBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Tiago Bono
 * @since 2015-08-14
 */
public class AlunoDto {

    private Long id;

    @NotNull(message = "Informe o RA")
    @Min(value = 10000, message = "O RA deve ser maior ou igual a 10000")
    @Max(value = 99999, message = "O RA deve ser menor ou igual a 99999")
    private Integer ra;

    @NotNull(message = "Informe o nome")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    public AlunoDto() {
    }

    public AlunoDto(final Aluno aluno) throws ReflectiveOperationException {
        Objects.requireNonNull(aluno, "Informe o aluno");
        BeanUtilsBean.getInstance().getPropertyUtils().copyProperties(this, aluno);
    }

    public Aluno buildAluno() {
        return Aluno.newBuilder()
                .withId(id)
                .withRa(ra)
                .withNome(nome)
                .build();
    }

    public boolean isPersistido() {
        return id != null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRa() {
        return ra;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
