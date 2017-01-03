/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author rpbeat
 */
public enum EstadoProcedimento {
    Concluido("Concluido"), Em_Curso("Em Curso"), A_iniciar("A iniciar"), Cancelado("Cancelado");

    private String value;

    EstadoProcedimento(final String value) {
        this.value = value;
    }

   @Override
    public String toString() {
        return this.value;
    }
}
