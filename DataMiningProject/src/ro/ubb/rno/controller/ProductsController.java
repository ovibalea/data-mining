package ro.ubb.rno.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ro.ubb.da.DataAccessService;
import ro.ubb.rno.model.AssociationRule;
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
	
	public Set<Product> getRecommendations(List<Product> actualProductsInList){
		return dataAccessService.getRecommendations(actualProductsInList);
	}
	
}
