package com.example.demo.api.models;

import java.util.Date;
import java.util.Objects;

import org.opengis.geometry.primitive.Point;

public class Plantio {
    
    private int id;
    private String nome;
    private String nomeCientifico;
    private Point ponto;
    private Date data;
    private int idAluno;

    public Plantio() {
    }

    public Plantio(int id, String nome, String nomeCientifico, Point ponto, Date data, int idAluno) {
        this.id = id;
        this.nome = nome;
        this.nomeCientifico = nomeCientifico;
        this.ponto = ponto;
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

    public Point getPonto() {
        return this.ponto;
    }

    public void setPonto(Point ponto) {
        this.ponto = ponto;
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

    public Plantio id(int id) {
        this.id = id;
        return this;
    }

    public Plantio nome(String nome) {
        this.nome = nome;
        return this;
    }

    public Plantio nomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
        return this;
    }

    public Plantio ponto(Point ponto) {
        this.ponto = ponto;
        return this;
    }

    public Plantio data(Date data) {
        this.data = data;
        return this;
    }

    public Plantio idAluno(int idAluno) {
        this.idAluno = idAluno;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Plantio)) {
            return false;
        }
        Plantio shapefile = (Plantio) o;
        return id == shapefile.id && Objects.equals(nome, shapefile.nome) && Objects.equals(nomeCientifico, shapefile.nomeCientifico) && Objects.equals(ponto, shapefile.ponto) && Objects.equals(data, shapefile.data) && idAluno == shapefile.idAluno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nomeCientifico, ponto, data, idAluno);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", nomeCientifico='" + getNomeCientifico() + "'" +
            ", ponto='" + getPonto() + "'" +
            ", data='" + getData() + "'" +
            ", idAluno='" + getIdAluno() + "'" +
            "}";
    }
    
    
}
