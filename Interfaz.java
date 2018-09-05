//Estefania Pitol Martinez A01551688
public interface Interfaz{

	public void agregaArticulo(Articulo art);
	public void eliminaArticulo(int index);
	public int buscaArticulo(String nombre);
	public Articulo getArticulo(int index);
	public int sizeArticulos();
	
	public void agregaPedido(Pedido ped);
	public void eliminaPedido(int index);
	public int buscaPedido(String nombre);
	public Pedido getPedido(int index);
	public int sizePedidos();
}