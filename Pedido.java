//Estefania Pitol Martinez A01551688
import java.util.ArrayList;
public class Pedido extends Entidades implements Comparable<Pedido>{
	private ArrayList<Articulo> articulos=new ArrayList<Articulo>();
	public Pedido(String nombre, ArrayList<Articulo> articulos){
		this.nombre=nombre;
		this.articulos=articulos;
		cantidad=articulos.size();
	}
	public int getCantidad(){
		cantidad=articulos.size();
		return cantidad;
	}
	public Articulo getArti(int index){
		return articulos.get(index);
	}
	public int sizeArti(){
		return articulos.size();
	}
	public int buscaArti(String nombre){
		for(int i=0; i<articulos.size(); i++){
			if(articulos.get(i).getNombre().toLowerCase().indexOf(nombre.toLowerCase())>=0)
				return i;
		}
		return -1;
	}
	public String toString(){
		String str="";
		for(int i=0; i<articulos.size(); i++)
			str+=articulos.get(i)+"\n";
		return nombre+":\n"+str;
	}
	@Override
	public int compareTo(Pedido ped){
		if(nombre.compareToIgnoreCase(ped.getNombre())>0)
			return 1;
		else
			if(nombre.compareToIgnoreCase(ped.getNombre())==0)
				return 0;
			else
				return -1;
	}
}