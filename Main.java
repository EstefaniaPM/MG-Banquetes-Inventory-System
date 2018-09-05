//Estefania Pitol Martinez A01551688
import java.io.*;
import javax.swing.JOptionPane;
public class Main{
	public static void main(String[] args) {
		Inventario inv=new Inventario();
		String menu="MENU\n1. Agregar articulo al inventario\n2. Eliminar articulo del inventario\n3. Aumentar inventario\n4. Ver inventario\n5. Ingresar pedido\n6. Eliminar pedido\n7. Devolucion de pedido\n8. Ver pedidos\n9. Ver perdidas\n10. Ordenar entidades\n11. Guardar informacion\n12.Cargar informacion guardada\n13. Buscar\n0. Salir";
		int op=-1;
		do{
			try{
				String opp=JOptionPane.showInputDialog(null, menu);
				if(opp==null) break;
				op=Integer.parseInt(opp);
				switch(op){
					case 1:
						String nombre=JOptionPane.showInputDialog(null, "Nombre del articulo:");
						if(nombre==null) break;				
						String id=JOptionPane.showInputDialog(null, "ID del articulo:");
						if(id==null) break;	
						if(inv.sizeArticulos()!=0){
							if(inv.validId(id)==false){
								JOptionPane.showMessageDialog(null, "Ya existe un articulo con el ese ID");
								break;
							}
						}
						String descripcion=JOptionPane.showInputDialog(null, "Descripcion del articulo:");
						if(descripcion==null) break;				
						int cantidad=Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad del articulo:"));
						int precioUn=Integer.parseInt(JOptionPane.showInputDialog(null, "Precio unitario del articulo:"));
						inv.agregaArticulo(new Articulo(id, nombre, descripcion, cantidad, precioUn));
						JOptionPane.showMessageDialog(null, "Se ha aniadido tu articulo "+nombre);
						break;
					case 2:
						String articulos="Que articulo quieres eliminar:\n";
						for(int i=0; i<inv.sizeArticulos(); i++)
							articulos+=(i+1)+". "+inv.getArticulo(i).getNombre()+"\n";
						int opcion=Integer.parseInt(JOptionPane.showInputDialog(null, articulos));
						inv.eliminaArticulo(opcion-1);
						JOptionPane.showMessageDialog(null, "Se ha eliminado el articulo");
						break;
					case 3:
						articulos="A que articulo le quieres aumentar:\n";
						for(int i=0; i<inv.sizeArticulos(); i++)
							articulos+=(i+1)+". "+inv.getArticulo(i).getNombre()+"\n";
						opcion=Integer.parseInt(JOptionPane.showInputDialog(null, articulos));
						int inc=Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos vas a agergar"));
						inv.getArticulo(opcion-1).incrementaCantidad(inc);			
						JOptionPane.showMessageDialog(null, "Se han agregado los articulos");			
						break;
					case 4:
						String str="";
						for(int i=0; i<inv.sizeArticulos(); i++)
							str+=inv.getArticulo(i)+"\n";
						JOptionPane.showMessageDialog(null, "Cuentas con:\n"+str);
						break;
					case 5:
						String cliente=JOptionPane.showInputDialog(null, "Nombre del cliente:");
						if(cliente==null) break;
						else{
							boolean flag=false;
							int cases=Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos articulos distintos pidio:"));
							int arrIndex[]=new int[cases];
							int arrCant[]=new int[cases];
							for(int i=0; i<cases; i++){				
								articulos="Seleccione el articulo "+(i+1)+":\n";
								for(int j=0; j<inv.sizeArticulos(); j++)
									articulos+=(j+1)+". "+inv.getArticulo(j).getNombre()+"\n";
								opcion=Integer.parseInt(JOptionPane.showInputDialog(null, articulos));
								int cant=Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos pidio:"));
								if(cant<=inv.getArticulo(opcion-1).getCantidad()){
									inv.arrPedido(inv.getArticulo(opcion-1).getNombre(), cant);
									arrIndex[i]=opcion-1;
									arrCant[i]=cant;				
								}
								else{
									JOptionPane.showMessageDialog(null, "No hay suficientes "+inv.getArticulo(opcion-1).getNombre());
									flag=true;
									break;
								}
							}
							if(flag=false){
								for(int i=0; i<cases; i++)
									inv.getArticulo(arrIndex[i]).incrementaCantidad(-(arrCant[i]));							
								inv.agregaPedido(new Pedido(cliente, inv.getArrPedido()));
								inv.clearArrPedido();
							}
						}						
						break;
					case 6:
						String pedidos="Que pedido quiere eliminar:\n";
						for(int i=0; i<inv.sizePedidos(); i++)
							pedidos+=(i+1)+". "+inv.getPedido(i).getNombre()+"\n";
						opcion=Integer.parseInt(JOptionPane.showInputDialog(null, pedidos));
						inv.eliminaPedido(opcion-1);
						JOptionPane.showMessageDialog(null, "Se ha eliminado el pedido");
						break;
					case 7:
						pedidos="Quien regreso el pedido:\n";
						for(int i=0; i<inv.sizePedidos(); i++)
							pedidos+=(i+1)+". "+inv.getPedido(i).getNombre()+"\n";
						opcion=Integer.parseInt(JOptionPane.showInputDialog(null, pedidos));					
						for(int i=0; i<inv.getPedido(opcion-1).sizeArti(); i++){
							String nomb=inv.getPedido(opcion-1).getArti(i).getNombre();
							int canti=Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos "+nomb+" regreso?:"));
							int indexArti=inv.buscaArticulo(nomb);
							inv.getArticulo(indexArti).incrementaCantidad(canti);
							int perdida=inv.getPedido(opcion-1).getArti(i).getCantidad()-canti;
							int perdidaTot=inv.getArticulo(indexArti).getPrecioUn()*perdida;
							JOptionPane.showMessageDialog(null, "Las perdidas fueron de $"+perdidaTot);								
							if(perdidaTot!=0){
								int indexPerd=inv.buscaPerdida(nomb);
								if(indexPerd>=0){
									inv.getPerdida(indexPerd).incrementaCantidad(perdida);
									inv.getPerdida(indexPerd).incrementaPerd(perdidaTot);
								}
								else
									inv.agregaPerdida(new Perdidas(inv.getArticulo(indexArti).getNombre(), perdida, perdidaTot));											
							}
						}
						inv.eliminaPedido(opcion-1);
						break;
					case 8:
						String stri="";
						for(int i=0; i<inv.sizePedidos(); i++)
							stri+=inv.getPedido(i)+"\n";
						JOptionPane.showMessageDialog(null, stri);
						break;
					case 9:
						String st="";
						for(int i=0; i<inv.sizePerdidas(); i++)
							st+=inv.getPerdida(i)+"\n";
						JOptionPane.showMessageDialog(null, st);
						break;
					case 10:
						String opppp=JOptionPane.showInputDialog(null, "Que entidad quiere ordenar?\n1. Articulos\n2. Pedidos");
						if(opppp==null) break;
						else{
							int oppp=Integer.parseInt(opppp);
							int criterio;
							if(oppp==1){
								criterio=Integer.parseInt(JOptionPane.showInputDialog(null, "Por que criterio deseas ordenarlos?\n1. Nombre\n2. Cantidad\n3. Precio\n4. ID"));
								switch(criterio){
									case 1:
										inv.ordenaNombreArt();
										JOptionPane.showMessageDialog(null, "Han sido ordenados");
										break;
									case 2:
										inv.ordenaCantidadArt();
										JOptionPane.showMessageDialog(null, "Han sido ordenados");
										break;
									case 3:
										inv.ordenaPrecio();
										JOptionPane.showMessageDialog(null, "Han sido ordenados");
										break;
									case 4:
										inv.ordenaID();
										JOptionPane.showMessageDialog(null, "Han sido ordenados");
										break;
								}							
							}
							else{
								if(oppp==2){
									criterio=Integer.parseInt(JOptionPane.showInputDialog(null, "Por que criterio deseas ordenarlos?\n1. Nombre del cliente\n2. Dimension del pedido"));
									if(criterio==1){
										inv.ordenaNombrePed();
										JOptionPane.showMessageDialog(null, "Han sido ordenados");
									}
									else
										if(criterio==2){
											inv.ordenaCantidadPed();
											JOptionPane.showMessageDialog(null, "Han sido ordenados");
										}
								}
							}
						}
						break;												
					case 11:
						String temp;
						File alARTICULOS=new File("ALArticulos");
						try{     				
	        				BufferedWriter writer = new BufferedWriter(new FileWriter(alARTICULOS));
							temp= Integer.toString(inv.sizeArticulos());						
							writer.write(temp);
							writer.newLine();
							for(int i=0; i<inv.sizeArticulos(); i++){
								writer.write(inv.getArticulo(i).getId());
				            	writer.newLine();
				            	writer.write(inv.getArticulo(i).getNombre());
				            	writer.newLine();
				            	writer.write(inv.getArticulo(i).getDescripcion());
				            	writer.newLine();
				            	temp=Integer.toString(inv.getArticulo(i).getCantidad());							
								writer.write(temp);
								writer.newLine();
								temp=Integer.toString(inv.getArticulo(i).getPrecioUn());							
								writer.write(temp);
				            	writer.newLine();
							}
							writer.close();
						}
						catch(IOException e){
							JOptionPane.showMessageDialog(null, e);
						}
						File alPEDIDOS=new File("ALPedidos");
						try{     				
	        				BufferedWriter writer2 = new BufferedWriter(new FileWriter(alPEDIDOS));
							temp=Integer.toString(inv.sizePedidos());						
							writer2.write(temp);
							writer2.newLine();
							for(int i=0; i<inv.sizePedidos(); i++){
								writer2.write(inv.getPedido(i).getNombre());
				            	writer2.newLine();
				            	temp=Integer.toString(inv.getPedido(i).sizeArti());
				            	writer2.write(temp);
				            	writer2.newLine();
				            	for(int j=0; j<inv.getPedido(i).sizeArti(); j++){
				            		writer2.write(inv.getPedido(i).getArti(j).getNombre());
				            		writer2.newLine();
				            		temp=Integer.toString(inv.getPedido(i).getArti(j).getCantidad());
				            		writer2.write(temp);
				            		writer2.newLine();
				            	}
							}
							writer2.close();
						}
						catch(IOException e){
							JOptionPane.showMessageDialog(null, e);
						}
						File alPERDIDAS=new File("ALPerdidas");
						try{     				
	        				BufferedWriter writer3 = new BufferedWriter(new FileWriter(alPERDIDAS));
							temp=Integer.toString(inv.sizePerdidas());						
							writer3.write(temp);
							writer3.newLine();
							for(int i=0; i<inv.sizePerdidas(); i++){
								writer3.write(inv.getPerdida(i).getNombre());
								writer3.newLine();
								temp=Integer.toString(inv.getPerdida(i).getCantidad());						
								writer3.write(temp);
								writer3.newLine();
								temp=Integer.toString(inv.getPerdida(i).getPerd());						
								writer3.write(temp);
								writer3.newLine();
							}
							writer3.close();
						}
						catch(IOException e){
							JOptionPane.showMessageDialog(null, e);
						}
						JOptionPane.showMessageDialog(null, "La informacion ha sido guardada");
						break;
					case 12:
						int resp=Integer.parseInt(JOptionPane.showInputDialog(null, "Se borrara la informacion de esta ejecucion\nDesea continuar?\n1. Si\n2. No"));
						if(resp==1){
							inv.clearTodo();
							String line;
							try{
								BufferedReader in=new BufferedReader(new FileReader("ALArticulos"));
								line=in.readLine();
								int artsize=Integer.parseInt(line);									
								for(int i=0; i<artsize; i++){
									String idd=in.readLine();
        							String name=in.readLine();
        							String desc=in.readLine();
        							int can=Integer.parseInt(in.readLine());
        							int pre=Integer.parseInt(in.readLine());
        							inv.agregaArticulo(new Articulo(idd, name, desc, can, pre));
        						}
								in.close();
							}
							catch(IOException e){
								JOptionPane.showMessageDialog(null, e);
							}
							try{
								BufferedReader in2=new BufferedReader(new FileReader("ALPedidos"));
								line=in2.readLine();
								int pedsize=Integer.parseInt(line);								
								for(int i=0; i<pedsize; i++){
									String name2=in2.readLine();
									line=in2.readLine();
									int artsize2=Integer.parseInt(line);
									for(int j=0; j<artsize2; j++){
										String nombre2=in2.readLine();
										line=in2.readLine();
										int cuan=Integer.parseInt(line);
										inv.arrPedido(nombre2, cuan);
									}
									inv.agregaPedido(new Pedido(name2, inv.getArrPedido()));
									inv.clearArrPedido();					
								}
								in2.close();
							}
							catch(IOException e){
								JOptionPane.showMessageDialog(null, e);
							}
							try{
								BufferedReader in3=new BufferedReader(new FileReader("ALPerdidas"));
								line=in3.readLine();
								int perdsize=Integer.parseInt(line);									
								for(int i=0; i<perdsize; i++){
									String namee=in3.readLine();            					
        							int cann=Integer.parseInt(in3.readLine());
        							int perd=Integer.parseInt(in3.readLine());
        							inv.agregaPerdida(new Perdidas(namee, cann, perd));        											
								}
								in3.close();
							}
							catch(IOException e){
								JOptionPane.showMessageDialog(null, e);
							}
						}
						else
							break;
						break;
					case 13:
						String numm=JOptionPane.showInputDialog(null, "Que quieres buscar?\n1. Articulo\n2. Pedido");
						if(numm==null) break;
						else{
							String palabra, coincidencias;
							int num=Integer.parseInt(numm);
							if(num==1){
								String crit=JOptionPane.showInputDialog(null, "Por que criterio quieres buscar el articulo?\n1. Nombre\n2. ID\n3. Precio\n4. Cantidad");
								if(crit==null) break;								
								else{
									int crite=Integer.parseInt(crit);
									switch(crite){
										case 1:
											palabra=JOptionPane.showInputDialog("Escribe el nombre del articulo a buscar:");
											if(palabra==null) break;
											else{
												coincidencias=inv.coincideArtNombre(palabra);
												JOptionPane.showMessageDialog(null, coincidencias);
											}
											break;
										case 2:
											palabra=JOptionPane.showInputDialog("Escribe el ID del articulo a buscar:");
											if(palabra==null) break;
											else{
												coincidencias=inv.coincideArtId(palabra);
												JOptionPane.showMessageDialog(null, coincidencias);
											}
											break;
										case 3:
											palabra=JOptionPane.showInputDialog("Escribe el precio del articulo a buscar:");
											if(palabra==null) break;
											else{
												coincidencias=inv.coincideArtPrecio(palabra);
												JOptionPane.showMessageDialog(null, coincidencias);
											}
											break;
										case 4:
											palabra=JOptionPane.showInputDialog("Escribe el cantidad del articulo a buscar:");
											if(palabra==null) break;
											else{
												coincidencias=inv.coincideArtCant(palabra);
												JOptionPane.showMessageDialog(null, coincidencias);
											}
											break;
									}
								}
							}
							else
								if(num==2){
									palabra=JOptionPane.showInputDialog("Escribe el nombre del cliente a buscar:");
									coincidencias=inv.coincidePedNombre(palabra);
									JOptionPane.showMessageDialog(null, coincidencias);
								}
						}
						break;
				}
			}
			catch(NumberFormatException e){
				inv.clearArrPedido();
				JOptionPane.showMessageDialog(null, "Escribe un numero");
			}
		}while(op!=0);
		JOptionPane.showMessageDialog(null, "PROYECTO FINAL\nProgramacion Orientada a Objetos\nEstefania Pitol Martinez A01551688");
	}
}