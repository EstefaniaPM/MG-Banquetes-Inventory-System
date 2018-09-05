//Estefania Pitol Martinez A01551688
public class Articulo extends Entidades implements Comparable<Articulo>{
	private String id, descripcion;
	private int precioUn;
	public Articulo(String id, String nombre, String descripcion, int cantidad, int precioUn){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.cantidad=cantidad;
		this.precioUn=precioUn;		
	}
	public Articulo(String nombre, int cantidad){
		this.nombre=nombre;
		this.cantidad=cantidad;
	}
	public void incrementaCantidad(int cant){
		cantidad+=cant;
	}
	public int getPrecioUn(){
		return precioUn;
	}
	public String getId(){
		return id;
	}
	public String getDescripcion(){
		return descripcion;
	}
	public String toString(){
		return cantidad+" "+nombre;
	}
	@Override
	public int compareTo(Articulo art){
		if(nombre.compareToIgnoreCase(art.getNombre())>0)
			return 1;
		else
			if(nombre.compareToIgnoreCase(art.getNombre())==0)
				return 0;
			else
				return -1;
	}
}