package Programa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import Classes.Produto;
import Classes.Venda;

public class Programa {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();

        Scanner ler = new Scanner(System.in);
        int opcao;

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do{
            System.out.println("\n----------- MENU ------------");
            System.out.println("1 - Cadastrar produtos");
            System.out.println("2 - Exibir relatórios");
            System.out.println("3 - Realizar Venda");
            System.out.println("0 - Sair");
            opcao = ler.nextInt();
            ler.nextLine();

            switch(opcao){
                case 1:
             do{
                System.out.println("\nCADASTRAMENTO DE PRODUTOS");
                System.out.println("------------- ------------");
                System.out.println("1 - Consultar produto");
                System.out.println("2 - Incluir produto");
                System.out.println("0 - Voltar ao menu anterior");
                opcao = ler.nextInt();
                ler.nextLine();

                switch(opcao){
                    case 1:
                     System.out.println("\nCONSULTA DE PRODUTOS");
                     System.out.println("----------- -----------");
                     System.out.println("\nDigite o nome do produto a consultar.");
                     String nomeConsulta = ler.nextLine(); 

                     if (produtos.isEmpty()){
                        System.out.println("\nNão há nenhum produto cadastrado com esse nome."); break;
                    } else
                     System.out.println("\n--------------------------");
                     System.out.println("-------- PRODUTOS ----------");
                     System.out.println("----------------------------");
                     System.out.println("\nProdutos encontrados:");
                     System.out.println("***********************");

                     produtos.stream()
                     .filter(p -> p.getNome().contains(nomeConsulta))
                     .forEach(System.out::println);
                    break;
                    case 2:
                     System.out.println("\n--------- INCLUSÃO DE PRODUTOS ----------");
                     System.out.println("-------------------------------------------");
                     int cod;
                     do{
                     System.out.println("\nDigite o código do produto a incluir ou -1 para sair.");
                     cod = ler.nextInt();
                     ler.nextLine();

                         if(cod != -1){
                             System.out.println("\nDigite o nome do produto.");
                             String nomeProduto = ler.nextLine();
                             System.out.println("\nDigite o valor do produto.");
                             double valorProduto = ler.nextDouble();
                             System.out.println("\nDigite a quantidade em estoque.");
                             int quantidadeProduto = ler.nextInt();
                             ler.nextLine();
                             produtos.add(new Produto(cod, nomeProduto, valorProduto, quantidadeProduto));
                         }

                     } while(cod != -1);
                     break;
                    case 0:
                     break;
                    default:
                    System.out.println("Opção inválida! Tente novamente ou volte ao menu inicial.");
                }

             } while(opcao != 0);
                   break;
                case 2:
                do{
                    System.out.println("\n----------------------------------");
                    System.out.println("----------- RELATÓRIOS -----------");
                    System.out.println("----------------------------------");
                    System.out.println("\n1 - Produtos");
                    System.out.println("2 - Venda por período - detalhado");
                    System.out.println("3 - Venda por período - consolidado");
                    System.out.println("0 - Voltar ao menu anterior");
                    opcao = ler.nextInt();
                    ler.nextLine();

                    switch(opcao){
                        case 1:
                        System.out.println("\n--------RELATÓRIO PRODUTOS--------");

                        if(produtos.isEmpty()){
                         System.out.println("\nNão há dados/produtos para a emissão do relatório"); break;
                        } else {
                            System.out.printf("\n%s\n","--------------------------------------------------------------------------------------------");
                            System.out.printf("\t%s\t%20s\t%15s\t%12s", "Código", "Nome", "Valor(R$)", "Estoque");
                            System.out.printf("\n%s\n","---------------------------------------------------------------------------------------------");

                          for (Produto produto : produtos) {
                            System.out.printf("\t%d\t%20s\t%15.2f\t%12d\n", produto.getCodigo(), produto.getNome(), produto.getValor(), produto.getQuantidadeEstoque());

                          }
                          
                            DoubleSummaryStatistics st = produtos.stream().collect(Collectors.summarizingDouble(Produto::getValor));
                            System.out.printf("%s\n","---------------------------------------------------------------------------------------------");
                            System.out.printf("Maior valor: R$%.2f\t Menor valor: R$%.2f\t Valor médio: R$%.2f\n", st.getMax(), st.getMin(), st.getAverage());
                         }

                        break;
                        case 2:
                        System.out.println("\n----------RELATÓRIO DE VENDAS (DETALHADO)----------");
                        System.out.println("Informe data de início [dd/MM/aaaa]:");
                        System.out.println("------------------------------------------------------");
                        String dataInicial1 = ler.nextLine();
                        System.out.println("\nInforme a data final [dd/MM/aaaa]:");
                        String dataFinal1 = ler.nextLine();

                        LocalDate dataInicial = LocalDate.parse(dataInicial1, df);
                        LocalDate dataFinal = LocalDate.parse(dataFinal1, df);

                         List<Venda> vendasFiltro =
                         vendas.stream().filter(v ->
                         v.getProduto().getDataCadastro().plusDays(1).isAfter(dataInicial) &&
                         v.getProduto().getDataCadastro().plusDays(-1).isBefore(dataFinal))
                         .collect(Collectors.toList());

                         System.out.println("\n----------------------------------------------------------------------------------------------------");
                         System.out.printf("%-10.10s\t%-12.12s\t%-20.20s\t%-15.15s\t%-10.10s\t%-15.15s\n", 
                         "Data", "Código", "Produto", "Valor(R$)", "Quantidade", "Valor Total");
                         System.out.println("\n----------------------------------------------------------------------------------------------------");
                         
                         vendasFiltro.forEach(v -> System.out.printf(
                                "%-10.10s\t%-12.12s\t%-20.20s\t%-15.15s\t%-10.10s\t%-15.15s\n", 
                                v.getDataVenda().format(df), v.getProduto().getCodigo(), v.getProduto().getNome(), v.getProduto().getValor(), v.getProduto().getQuantidadeEstoque(), v.getProduto().getValor()));

                                DoubleSummaryStatistics st = vendasFiltro.stream()
                                .collect(Collectors.summarizingDouble(v-> v.getProduto().getValor()));
                                System.out.println("\n----------------------------------------------------------------------------------------------------");
                                System.out.printf("Valor médio: R$ %.2f\n", st.getAverage());
                        break;
                        case 3:
                        System.out.println("\n----------------------------------------------------------------------------------------------------");
                        System.out.printf("%-10.10s\t%-12.12s\t%-20.20s\t%-15.15s\t%-10.10s\n", 
                        "Código", "Produto", "Valor(R$)", "Quantidade", "Valor Total");
                        System.out.println("\n----------------------------------------------------------------------------------------------------");

                        break;
                        case 0:
                        break;
                    }

                } while(opcao != 0);
                    break;
                case 3:
                 System.out.println("\n-------------------------");
                 System.out.println("-----REGISTRAR VENDA------");
                 System.out.println("--------------------------");
                 System.out.println("Digite o código do produto:");
                 int codigoVenda = ler.nextInt();
                 ler.nextLine();

                 for(Produto produto : produtos){
                     if(produto.getCodigo() == codigoVenda){
                         System.out.printf("Produto encontrado: [%s]\n" , produto.getNome());
                     } else
                       

                      if(produto.getQuantidadeEstoque() == 0){
                          System.out.println("\nNão será possível realizar a venda; produto com estoque zerado.");
                      }
                 }
                 String dataVenda;
                 dataVenda = df.format(LocalDateTime.now());
                 System.out.println("Data de hoje:" + dataVenda);
                 
                 
                 System.out.println("\nDigite a quantidade:");
                 int quantidadeVenda = ler.nextInt();
                 ler.nextLine();

                 for (Produto produto : produtos){

                    if(quantidadeVenda > produto.getQuantidadeEstoque()){
                        System.out.println("Não há produtos suficientes no estoque para realizar venda."); break;
                    } else 
                     if(quantidadeVenda < produto.getQuantidadeEstoque()){
                         System.out.println("Quantidade suficiente para realizar venda"); 
                    }
                    produto.setQuantidadeEstoque(produto.getQuantidadeEstoque()-quantidadeVenda);
                    System.out.println("\nVenda realizada com sucesso!!!");

                    Venda venda = new Venda(LocalDate.parse(dataVenda), quantidadeVenda, produto);
                    vendas.add(venda);
                 } 
                break;
                case 0:
                 System.out.println("Encerrando a aplicação...");
                break;
                default:
                 System.out.println("Opção inválida. Tente novamente!");
            }

        } while(opcao != -1);
        ler.close();

    }
}
