package Classes;

import java.time.LocalDate;

public class Venda {
    private LocalDate dataVenda; 
    private String produtoVendido;
    private int quantidadeVendida;
    private Produto produto;
    
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
    @Override
    public String toString() {
        return "Venda [dataVenda=" + dataVenda + ", produto=" + produto + ", produtoVendido=" + produtoVendido
                + ", quantidadeVendida=" + quantidadeVendida + "]";
    }
    public String getProdutoVendido() {
        return produtoVendido;
    }
    public Venda(LocalDate dataVenda, String produtoVendido, int quantidadeVendida, Produto produto) {
        this.dataVenda = dataVenda;
        this.produtoVendido = produtoVendido;
        this.quantidadeVendida = quantidadeVendida;
        this.produto = produto;
    }
    public Venda(LocalDate parse, int quantidadeVenda, Produto produto2) {
    }
    public void setProdutoVendido(String produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}
