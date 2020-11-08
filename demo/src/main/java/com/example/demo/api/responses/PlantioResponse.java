package com.example.demo.api.responses;

import java.util.Date;
import java.util.Objects;

import com.example.demo.api.dao.AlunoDao;
import com.example.demo.api.models.Aluno;
import com.example.demo.api.models.Plantio;

public class PlantioResponse {

    private int id;
    private String nome;
    private String nomeCientifico;
    private Date data;
    private double pontoX;
    private double pontoY;
    private AlunoResponse aluno;

    public PlantioResponse() {
    }

    public PlantioResponse(int id, String nome, String nomeCientifico, Date data, double pontoX, double pontoY,
            AlunoResponse aluno) {
        this.id = id;
        this.nome = nome;
        this.nomeCientifico = nomeCientifico;
        this.data = data;
        this.pontoX = pontoX;
        this.pontoY = pontoY;
        this.aluno = aluno;
    }

    public PlantioResponse(Plantio plantio) {
        this.id = plantio.getId();
        this.nome = plantio.getNome();
        this.nomeCientifico = plantio.getNomeCientifico();
        this.data = plantio.getData();
        this.pontoX = plantio.getPonto().getCentroid().getCoordinate()[0];
        this.pontoY = plantio.getPonto().getCentroid().getCoordinate()[1];

        Aluno aluno = AlunoDao.read(plantio.getIdAluno());

        if (aluno == null) {
            this.aluno = null;
        } else {
            this.aluno = new AlunoResponse(AlunoDao.read(plantio.getIdAluno()));
        }

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

    public double getPontoX() {
        return this.pontoX;
    }

    public void setPontoX(double pontoX) {
        this.pontoX = pontoX;
    }

    public double getPontoY() {
        return this.pontoY;
    }

    public void setPontoY(double pontoY) {
        this.pontoY = pontoY;
    }

    public AlunoResponse getAluno() {
        return this.aluno;
    }

    public void setAluno(AlunoResponse aluno) {
        this.aluno = aluno;
    }

    public PlantioResponse id(int id) {
        this.id = id;
        return this;
    }

    public PlantioResponse nome(String nome) {
        this.nome = nome;
        return this;
    }

    public PlantioResponse nomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
        return this;
    }

    public PlantioResponse data(Date data) {
        this.data = data;
        return this;
    }

    public PlantioResponse pontoX(double pontoX) {
        this.pontoX = pontoX;
        return this;
    }

    public PlantioResponse pontoY(double pontoY) {
        this.pontoY = pontoY;
        return this;
    }

    public PlantioResponse aluno(AlunoResponse aluno) {
        this.aluno = aluno;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlantioResponse)) {
            return false;
        }
        PlantioResponse plantioResponse = (PlantioResponse) o;
        return id == plantioResponse.id && Objects.equals(nome, plantioResponse.nome)
                && Objects.equals(nomeCientifico, plantioResponse.nomeCientifico)
                && Objects.equals(data, plantioResponse.data) && pontoX == plantioResponse.pontoX
                && pontoY == plantioResponse.pontoY && Objects.equals(aluno, plantioResponse.aluno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nomeCientifico, data, pontoX, pontoY, aluno);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nome='" + getNome() + "'" + ", nomeCientifico='" + getNomeCientifico()
                + "'" + ", data='" + getData() + "'" + ", pontoX='" + getPontoX() + "'" + ", pontoY='" + getPontoY()
                + "'" + ", aluno='" + getAluno() + "'" + "}";
    }

}
