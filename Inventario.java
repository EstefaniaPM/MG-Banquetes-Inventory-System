//Estefania Pitol Martinez A01551688
import java.io.*;
import java.util.ArrayList;
public class Inventario implements Interfaz{
	private ArrayList<Pedido> pedidos;
	private ArrayList<Articulo> articulos;
	private ArrayList<Articulo> alpedidos;
	private ArrayList<Perdidas> perdidas;
	public Inventario(){
		pedidos=new ArrayList<Pedido>();
		articulos=new ArrayList<Articulo>();
		alpedidos=new ArrayList<Articulo>();
		perdidas=new ArrayList<Perdidas>();		
	}
	public void agregaArticulo(Articulo art){
		articulos.add(art);
	}
	public void eliminaArticulo(int index){
		articulos.remove(index);
	}
	public int buscaArticulo(String nombre){
		for(int i=0; i<articulos.size(); i++){
			if(articulos.get(i).getNombre().equalsIgnoreCase(nombre))
				return i;
		}
		return (-1);
	}
	public boolean validId(String id){
		for(int i=0; i<articulos.size(); i++){
			if(articulos.get(i).getId().toLowerCase().indexOf(id.toLowerCase())>=0)
				return false;
		}
		return true;
	}
	public Articulo getArticulo(int index){
		return articulos.get(index);
	}
	public int sizeArticulos(){
		return articulos.size();
	}
	public void clearTodo(){
		articulos.clear();
		pedidos.clear();
		alpedidos.clear();
		perdidas.clear();
	}
	public void arrPedido(String nombre, int cantidad){
		alpedidos.add(new Articulo(nombre, cantidad));
	}
	public ArrayList<Articulo> getArrPedido(){
		ArrayList<Articulo> arr=new ArrayList<Articulo>();
		for(int i=0; i<alpedidos.size(); i++)
			arr.add(alpedidos.get(i));
		return arr;
	}
	public void clearArrPedido(){
		alpedidos.clear();
	}
	public void agregaPedido(Pedido ped){
		pedidos.add(ped);
	}
	public void eliminaPedido(int index){
		pedidos.remove(index);
	}
	public int buscaPedido(String nombre){
		for(int i=0; i<pedidos.size(); i++){
			if(pedidos.get(i).getNombre().equalsIgnoreCase(nombre))
				return i;
		}
		return -1;
	}	
	public Pedido getPedido(int index){
		return pedidos.get(index);
	}
	public int sizePedidos(){
		return pedidos.size();
	}
	public void agregaPerdida(Perdidas perd){
		perdidas.add(perd);
	}
	public Perdidas getPerdida(int index){
		return perdidas.get(index);
	}
	public int sizePerdidas(){
		return perdidas.size();	
	}
	public int buscaPerdida(String nombre){
		for(int i=0; i<perdidas.size(); i++){
			if(perdidas.get(i).getNombre().equalsIgnoreCase(nombre)){
				return i;
			}
		}
		return -1;
	}
	public String coincideArtNombre(String nombre){
		boolean flag=false;
		String coincidencias="";
		for(int i=0; i<articulos.size(); i++){
			if(articulos.get(i).getNombre().toLowerCase().indexOf(nombre.toLowerCase())>=0){
				coincidencias+=(articulos.get(i))+"\n";
				flag=true;
			}
		}
		if(flag==false)
			coincidencias="No se encontraron coincidencias";
		return coincidencias;
	}
	public String coincideArtId(String nombre){
		boolean flag=false;
		String coincidencias="";
		for(int i=0; i<articulos.size(); i++){
			if(articulos.get(i).getId().toLowerCase().indexOf(nombre.toLowerCase())>=0){
				coincidencias+=(articulos.get(i))+"\n";
				flag=true;
			}
		}
		if(flag==false)
			coincidencias="No se encontraron coincidencias";
		return coincidencias;
	}
	public String coincideArtPrecio(String nombre){
		String numero;
		boolean flag=false;
		String coincidencias="";
		for(int i=0; i<articulos.size(); i++){
			numero=Integer.toString(articulos.get(i).getPrecioUn());
			if(numero.toLowerCase().indexOf(nombre.toLowerCase())>=0){
				coincidencias+=(articulos.get(i))+"\n";
				flag=true;
			}
		}
		if(flag==false)
			coincidencias="No se encontraron coincidencias";
		return coincidencias;
	}
	public String coincideArtCant(String nombre){
		String numero;
		boolean flag=false;
		String coincidencias="";
		for(int i=0; i<articulos.size(); i++){
			numero=Integer.toString(articulos.get(i).getCantidad());
			if(numero.toLowerCase().indexOf(nombre.toLowerCase())>=0){
				coincidencias+=(articulos.get(i))+"\n";
				flag=true;
			}
		}
		if(flag==false)
			coincidencias="No se encontraron coincidencias";
		return coincidencias;
	}
	public String coincidePedNombre(String nombre){
		boolean flag=false;
		String coincidencias="";
		for(int i=0; i<pedidos.size(); i++){
			if(pedidos.get(i).getNombre().toLowerCase().indexOf(nombre.toLowerCase())>=0){
				coincidencias+=(pedidos.get(i))+"\n";
				flag=true;
			}
		}
		if(flag==false)
			coincidencias="No se encontraron coincidencias";
		return coincidencias;
	}
	public void ordenaNombreArt(){
		boolean swapped=true;
		Articulo temp;
		while(swapped==true){
			swapped=false;
			for(int i=0; i<articulos.size()-1; i++){
				if(articulos.get(i).compareTo(articulos.get(i+1))==1){
					temp=articulos.get(i);
					articulos.set(i, articulos.get(i+1));
					articulos.set(i+1, temp);
					swapped=true;
				}
			}
		}
	}
	public void ordenaCantidadArt(){
		boolean swapped=true;
		Articulo temp;
		while(swapped==true){
			swapped=false;
			for(int i=0; i<articulos.size()-1; i++){
				if(articulos.get(i).getCantidad()>articulos.get(i+1).getCantidad()){
					temp=articulos.get(i);
					articulos.set(i, articulos.get(i+1));
					articulos.set(i+1, temp);
					swapped=true;
				}
			}
		}
	}
	public void ordenaPrecio(){
		boolean swapped=true;
		Articulo temp;
		while(swapped==true){
			swapped=false;
			for(int i=0; i<articulos.size()-1; i++){
				if(articulos.get(i).getPrecioUn()>articulos.get(i+1).getPrecioUn()){
					temp=articulos.get(i);
					articulos.set(i, articulos.get(i+1));
					articulos.set(i+1, temp);
					swapped=true;
				}
			}
		}
	}
	public void ordenaID(){
		boolean swapped=true;
		Articulo temp;
		while(swapped==true){
			swapped=false;
			for(int i=0; i<articulos.size()-1; i++){
				if(articulos.get(i).getId().compareToIgnoreCase(articulos.get(i+1).getId())>0){
					temp=articulos.get(i);
					articulos.set(i, articulos.get(i+1));
					articulos.set(i+1, temp);
					swapped=true;
				}
			}
		}
	}
	public void ordenaNombrePed(){
		boolean swapped=true;
		Pedido temp;
		while(swapped==true){
			swapped=false;
			for(int i=0; i<pedidos.size()-1; i++){
				if(pedidos.get(i).compareTo(pedidos.get(i+1))==1){
					temp=pedidos.get(i);
					pedidos.set(i, pedidos.get(i+1));
					pedidos.set(i+1, temp);
					swapped=true;
				}
			}
		}
	}
	public void ordenaCantidadPed(){
		boolean swapped=true;
		Pedido temp;
		while(swapped==true){
			swapped=false;
			for(int i=0; i<pedidos.size()-1; i++){
				if(pedidos.get(i).getCantidad()>pedidos.get(i+1).getCantidad()){
					temp=pedidos.get(i);
					pedidos.set(i, pedidos.get(i+1));
					pedidos.set(i+1, temp);
					swapped=true;
				}
			}
		}
	}
}