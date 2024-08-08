
package br.com.projeto_petshop.dto;

public class ServicoDTO {
    private String tipo_servico;
    private double valor_servico;
    private int id_servico;

    public String getTipo_servico() {
        return tipo_servico;
    }

    public void setTipo_servico(String tipo_servico) {
        this.tipo_servico = tipo_servico;
    }

    public double getValor_servico() {
        return valor_servico;
    }

    public void setValor_servico(double valor_servico) {
        this.valor_servico = valor_servico;
    }

    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
    }
    
    
}
