public interface Processavel {
    public void processarPedido() throws PedidoInvalidoException;
    public double calcularValorFinal();
}