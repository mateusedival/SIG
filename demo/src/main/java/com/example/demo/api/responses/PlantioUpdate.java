package com.example.demo.api.responses;

import java.util.Date;
import java.util.Objects;

public class PlantioUpdate {
    private int id;
    private String nome;
    private String nomeCientifico;
    private Date data;
    private int idAluno;


    public PlantioUpdate() {
    }


    public PlantioUpdate(int id, String nome, String nomeCientifico, Date data, int idAluno) {
        this.id = id;
        this.nome = nome;
        this.nomeCientifico = nomeCientifico;
        this.data = data;
        this.idAluno = idAluno;
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

    public String getNomeCientifico() {
        return this.nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdAluno() {
        return this.idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public PlantioUpdate id(int id) {
        this.id = id;
        return this;
    }

    public PlantioUpdate nome(String nome) {
        this.nome = nome;
        return this;
    }

    public PlantioUpdate nomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
        return this;
    }

    public PlantioUpdate data(Date data) {
        this.data = data;
        return this;
    }

    public PlantioUpdate idAluno(int idAluno) {
        this.idAluno = idAluno;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlantioUpdate)) {
            return false;
        }
        PlantioUpdate plantioUpdate = (PlantioUpdate) o;
        return id == plantioUpdate.id && Objects.equals(nome, plantioUpdate.nome) && Objects.equals(nomeCientifico, plantioUpdate.nomeCientifico) && Objects.equals(data, plantioUpdate.data) && idAluno == plantioUpdate.idAluno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nomeCientifico, data, idAluno);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", nomeCientifico='" + getNomeCientifico() + "'" +
            ", data='" + getData() + "'" +
            ", idAluno='" + getIdAluno() + "'" +
            "}";
    }

}
