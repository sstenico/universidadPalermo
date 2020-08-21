package sis.logistica.Backend;

public class Inventario {
	
	private int    inventory_id;
	private String codigoarticulo;		
	private String descripcion;
	private int    stockcantidad;		
	private String unidadmedida;	
	private int    preciounitario;
	
	public int getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
	public String getCodigoarticulo() {
		return codigoarticulo;
	}
	public void setCodigoarticulo(String codigoarticulo) {
		this.codigoarticulo = codigoarticulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStockcantidad() {
		return stockcantidad;
	}
	public void setStockcantidad(int stockcantidad) {
		this.stockcantidad = stockcantidad;
	}
	public String getUnidadmedida() {
		return unidadmedida;
	}
	public void setUnidadmedida(String unidadmedida) {
		this.unidadmedida = unidadmedida;
	}
	public int getPreciounitario() {
		return preciounitario;
	}
	public void setPreciounitario(int preciounitario) {
		this.preciounitario = preciounitario;
	}	

	public Inventario (int inventory_id, String codigoarticulo, String descripcion,
					   int stockcantidad, String unidadmedida, 
					   int preciounitario) {
		
		this.inventory_id   = inventory_id;
		this.codigoarticulo = codigoarticulo;		
		this.descripcion    = descripcion;
		this.stockcantidad  = stockcantidad;		
		this.unidadmedida   = unidadmedida;	
		this.preciounitario = preciounitario;
	}

	public Inventario () {

	}

}