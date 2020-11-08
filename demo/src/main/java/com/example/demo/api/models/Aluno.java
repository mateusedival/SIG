package com.example.demo.api.models;

import java.util.Objects;

public class Aluno {

    private int id;
    private String nome;
    private int idade;
    private int serie;
    private int idCurso;

    public Aluno() {
    }

    public Aluno(int id, String nome, int idade, int serie, int idCurso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.serie = serie;
        this.idCurso = idCurso;
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

    public int getIdCurso() {
        return this.idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public Aluno id(int id) {
        this.id = id;
        return this;
    }

    public Aluno nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Aluno idade(int idade) {
        this.idade = idade;
        return this;
    }

    public Aluno serie(int serie) {
        this.serie = serie;
        return this;
    }

    public Aluno idCurso(int idCurso) {
        this.idCurso = idCurso;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Aluno)) {
            return false;
        }
        Aluno aluno = (Aluno) o;
        return id == aluno.id && Objects.equals(nome, aluno.nome) && idade == aluno.idade && serie == aluno.serie
                && idCurso == aluno.idCurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idade, serie, idCurso);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nome='" + getNome() + "'" + ", idade='" + getIdade() + "'"
                + ", serie='" + getSerie() + "'" + ", idCurso='" + getIdCurso() + "'" + "}";
    }

}
