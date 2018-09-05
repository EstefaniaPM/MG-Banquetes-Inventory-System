//Estefania Pitol Martinez A01551688
public class Perdidas extends Articulo{
	private String nombre;
	private int cantidad, perd;
	public Perdidas(String nombre, int cantidad, int perd){
		super(nombre, cantidad);
		this.perd=perd;
	}
	public int getPerd(){
		return perd;
	}
	public void incrementaPerd(int inc){
		perd+=inc;
	}
	public String toString(){
		return super.toString()+"| $"+perd;
	}
}