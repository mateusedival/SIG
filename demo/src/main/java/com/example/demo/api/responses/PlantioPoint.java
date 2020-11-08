package com.example.demo.api.responses;

import java.util.Objects;

/**
 * PlantioPoint
 */
public class PlantioPoint {

    private double pontoX;
    private double pontoY;


    public PlantioPoint() {
    }

    public PlantioPoint(double pontoX, double pontoY) {
        this.pontoX = pontoX;
        this.pontoY = pontoY;
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

    public PlantioPoint pontoX(double pontoX) {
        this.pontoX = pontoX;
        return this;
    }

    public PlantioPoint pontoY(double pontoY) {
        this.pontoY = pontoY;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlantioPoint)) {
            return false;
        }
        PlantioPoint plantioPoint = (PlantioPoint) o;
        return pontoX == plantioPoint.pontoX && pontoY == plantioPoint.pontoY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pontoX, pontoY);
    }

    @Override
    public String toString() {
        return "{" +
            " pontoX='" + getPontoX() + "'" +
            ", pontoY='" + getPontoY() + "'" +
            "}";
    }


}