public class PedidoRoupa extends Pedido implements Entregavel{

    public PedidoRoupa(int id, String nomeCliente, double valor) throws ValorInvalidoException {
        super(id, nomeCliente, valor, Categoria.ROUPA);
    }
    @Override 
    public double calcularValorFinal(){
        if(getValor() >= 200){
            return getValor() * 0.80;
        }
        return getValor();
    }

    @Override 
    public double calcularFrete(){
        double valorFrete;
        valorFrete = 10 + (getValor() * 0.05);
        return valorFrete;
    }
    
}
