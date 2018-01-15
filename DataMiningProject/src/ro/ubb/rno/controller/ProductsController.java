package ro.ubb.rno.controller;

import java.util.List;

import ro.ubb.da.DataAccessService;
import ro.ubb.rno.model.Product;

public class ProductsController {
	
	private DataAccessService dataAccessService;
	
	public ProductsController(DataAccessService dataAccessService) {
		super();
		this.dataAccessService = dataAccessService;
	}

	public List<Product> getProductsList(){
		return dataAccessService.getProductsList();
	}
	
}
