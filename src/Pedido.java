public abstract class Pedido implements Processavel{
    private int id;
    private String nomeCliente;
    private double valor;
    private StatusPedido status;
    private Categoria categoria;
        
    
    public Pedido(int id, String nomeCliente, double valor, Categoria categoria)
        throws ValorInvalidoException {
            if (valor <= 0) {
                throw new ValorInvalidoException(
                    "O valor do pedido deve ser maior que zero"
                );
            }

        this.id = id;
        this.nomeCliente = nomeCliente;
        this.valor = valor;
        this.status = StatusPedido.PENDENTE;
        this.categoria = categoria;
    }
    @Override 
    public void processarPedido() throws PedidoInvalidoException{
        if (status == StatusPedido.CANCELADO){
            throw new PedidoInvalidoException(
                "Não é possível processar um pedido cancelado"
            );
        }
        status = StatusPedido.PROCESSANDO;
    }

    public void alterarStatus(StatusPedido novoStatus)
            throws PedidoInvalidoException {

        if (status == StatusPedido.ENTREGUE) {
            throw new PedidoInvalidoException(
                "Não é possível alterar o status de um pedido entregue"
            );
        }

        this.status = novoStatus;
    }
    @Override 
    public abstract double calcularValorFinal();





    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public StatusPedido getStatus() {
        return status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    

    
} 
