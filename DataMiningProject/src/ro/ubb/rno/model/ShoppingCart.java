package ro.ubb.rno.model;

import java.util.List;

public class ShoppingCart {
	
	private List<Product> productsInCart;

	public List<Product> getProductsInCart() {
		return productsInCart;
	}

	public void setProductsInCart(List<Product> productsInCart) {
		this.productsInCart = productsInCart;
	}
	
	public void AddProductInCart(Product product) {
		productsInCart.add(product);
	}
}
