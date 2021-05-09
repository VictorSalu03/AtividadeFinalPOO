package Classes;

import java.time.LocalDate;

public class Produto {
    private String nome;
    private int codigo;
    private double valor;
    private int quantidadeEstoque;
    private LocalDate dataCadastro;

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public Produto(int cod, String nomeProduto, double valorProduto, int quantidadeProduto) {
        this.codigo = cod;
        this.nome = nomeProduto;
        this.valor = valorProduto;
        this.quantidadeEstoque = quantidadeProduto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    @Override
    public String toString() {
        return "Produto -> Código =\s" + codigo + ", Nome =\s" + nome + ", Valor =\s" + valor + ", Quantidade em estoque =\s" + quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int compareTo(Produto produto) {
        return produto.getNome().compareTo(getNome());
    }

    
}
