import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("\n===== MENU DE PEDIDOS =====");
        System.out.println("1. Cadastrar pedido");
        System.out.println("2. Alterar o status de um pedido");
        System.out.println("3. Processar pedido (com Iterator)");
        System.out.println("4. Remover cancelados (com Iterator)");
        System.out.println("5. Exibir lista de pedidos");
        System.out.println("6. Exibir pedidos entregáveis");
        System.out.println("7. Encerrar");
        System.out.print("Escolha uma opção: ");
    }
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        ArrayList<Pedido> pedidos = new ArrayList<>();
        int tipoPedido;
        int opcao = 0;
        while(opcao != 7){
        System.out.println("Bem vindo ao site de produtos");
        System.out.println("Digite o que você desejar fazer: ");
        menu();
        opcao = entrada.nextInt();
        entrada.nextLine();
        if(opcao == 1){
            System.out.println("Primeiro, escolha a categoria do pedido: ");
            System.out.println("1 - Eletrônico");
            System.out.println("2 - Roupa");
            System.out.println("3 - Alimento");
            tipoPedido = entrada.nextInt();
            entrada.nextLine();
            System.out.println("Digite o ID do pedido: ");
            int id = entrada.nextInt();
            entrada.nextLine();

            System.out.println("Digite seu nome: ");
            String nomeCliente = entrada.nextLine();
            
            System.out.println("Digite o valor do pedido: ");
            double valor = entrada.nextDouble();
            entrada.nextLine();

            try{
                if(tipoPedido == 1){
                pedidos.add(new PedidoEletronico(id, nomeCliente, valor));
                System.out.println("Produto cadastrado com sucesso");
            }else if(tipoPedido == 2){
                pedidos.add(new PedidoRoupa(id, nomeCliente, valor));
                System.out.println("Produto cadastrado com sucesso");
            }else if(tipoPedido == 3){
                pedidos.add(new PedidoAlimento(id, nomeCliente, valor));
                System.out.println("Produto cadastrado com sucesso");
            }else{
                System.out.println("Categoria Inválida");
            }
            }catch(ValorInvalidoException e){
                System.out.println(e.getMessage());
            }
            }
        else if(opcao == 2){
            System.out.println("Digite o ID do pedido: ");
            int idBusca = entrada.nextInt();
            Pedido pedidoEncontrado = null;
            entrada.nextLine();
            for (int i = 0; i < pedidos.size(); i++) {
                if(idBusca == pedidos.get(i).getId()){
                    pedidoEncontrado = pedidos.get(i);
                }
            }
            if(pedidoEncontrado != null){
                System.out.println("Pedido encontrado");
                System.out.println("Qual será o novo status do pedido? ");
                System.out.println("1 - PENDENTE");
                System.out.println("2 - PROCESSANDO");
                System.out.println("3 - ENVIADO");
                System.out.println("4 - ENTREGUE");
                System.out.println("5 - CANCELADO");
                int numStatus = entrada.nextInt();
                entrada.nextLine();
                StatusPedido novoStatus = null;
                if(numStatus == 1){
                    novoStatus = StatusPedido.PENDENTE;
                }
                else if(numStatus == 2){
                    novoStatus = StatusPedido.PROCESSANDO;
                }
                else if(numStatus == 3){
                    novoStatus = StatusPedido.ENVIADO;
                }
                else if(numStatus == 4){
                    novoStatus = StatusPedido.ENTREGUE;
                }
                else if(numStatus == 5){
                    novoStatus = StatusPedido.CANCELADO;
                }
                else{
                    System.out.println("Status inválido");
                }
                if (novoStatus != null){
                    try{
                        pedidoEncontrado.alterarStatus(novoStatus);
                        System.out.println("Status alterado com êxito");
                    }catch (PedidoInvalidoException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            else{
                System.out.println("Pedido não encontrado");
            }
            
        }
        else if(opcao == 3){
            Iterator<Pedido> it = pedidos.iterator();
            while(it.hasNext()){
                Pedido pedido = it.next();
                    try{
                        pedido.processarPedido();
                        System.out.println("Pedido processado com êxito");
                    }
                    catch (PedidoInvalidoException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        else if(opcao == 4){
            Iterator<Pedido> it = pedidos.iterator();
            while(it.hasNext()){
                Pedido pedido = it.next();
                if(pedido.getStatus() == StatusPedido.CANCELADO){
                    it.remove();
                    System.out.println("Pedido cancelado foi removido");
                }
            }
        }
        else if(opcao == 5){
            for (int i = 0; i < pedidos.size(); i++) {
                if(pedidos.get(i).getStatus() != StatusPedido.CANCELADO){
                    System.out.println("ID do pedido: "+pedidos.get(i).getId());
                    System.out.println("Nome do cliente: "+pedidos.get(i).getNomeCliente());
                    System.out.println("Valor do pedido: "+pedidos.get(i).getValor());
                    System.out.println("Status do pedido: "+pedidos.get(i).getStatus());
                    System.out.println("Categoria do pedido: "+pedidos.get(i).getCategoria());
                    System.out.println("-----------------------------------------");
                }
            }
        }
        else if(opcao == 6){
            for (int i = 0; i < pedidos.size(); i++) {
                if(pedidos.get(i) instanceof Entregavel){
                    System.out.println("ID do Pedido: "+pedidos.get(i).getId());
                    System.out.println("Nome do cliente: "+pedidos.get(i).getNomeCliente());
                    System.out.println("Valor do pedido: "+pedidos.get(i).getValor());
                    System.out.println("Status do pedido: "+pedidos.get(i).getStatus());
                    System.out.println("Categoria do pedido: "+pedidos.get(i).getCategoria());
                    System.out.println("------------------------------------------");
                }
            }
        }
        else if(opcao == 7){
            System.out.println("Programa Finalizado!");
        }
        else{
            System.out.println("Opção inválida");
        }

    }                
    }
}
