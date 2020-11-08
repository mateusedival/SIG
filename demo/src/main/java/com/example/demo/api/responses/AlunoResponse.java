package com.example.demo.api.responses;

import java.util.Objects;

import com.example.demo.api.dao.CursoDao;
import com.example.demo.api.models.Aluno;
import com.example.demo.api.models.Curso;

public class AlunoResponse {
    
    private int id;
    private String nome;
    private int idade;
    private int serie;
    private Curso curso;


    public AlunoResponse() {
    }

    public AlunoResponse(int id, String nome, int idade, int serie, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.serie = serie;
        this.curso = curso;
    }

    public AlunoResponse(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.serie = aluno.getSerie();
        this.curso = CursoDao.read(aluno.getIdCurso());
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getSerie() {
        return this.serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public AlunoResponse id(int id) {
        this.id = id;
        return this;
    }

    public AlunoResponse nome(String nome) {
        this.nome = nome;
        return this;
    }

    public AlunoResponse idade(int idade) {
        this.idade = idade;
        return this;
    }

    public AlunoResponse serie(int serie) {
        this.serie = serie;
        return this;
    }

    public AlunoResponse curso(Curso curso) {
        this.curso = curso;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AlunoResponse)) {
            return false;
        }
        AlunoResponse alunoResponse = (AlunoResponse) o;
        return id == alunoResponse.id && Objects.equals(nome, alunoResponse.nome) && idade == alunoResponse.idade && serie == alunoResponse.serie && Objects.equals(curso, alunoResponse.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idade, serie, curso);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", idade='" + getIdade() + "'" +
            ", serie='" + getSerie() + "'" +
            ", curso='" + getCurso() + "'" +
            "}";
    }

}
