package ro.ubb.rno.model;

public class AssociationRule {

	private ProductsSet existingProducts;
	
	private ProductsSet targetProducts;
	
	private double support;

	public AssociationRule(ProductsSet existingProducts, ProductsSet targetProducts, double support) {
		super();
		this.existingProducts = existingProducts;
		this.targetProducts = targetProducts;
		this.support = support;
	}

	public ProductsSet getExistingProducts() {
		return existingProducts;
	}

	public void setExistingProducts(ProductsSet existingProducts) {
		this.existingProducts = existingProducts;
	}

	public ProductsSet getTargetProducts() {
		return targetProducts;
	}

	public void setTargetProducts(ProductsSet targetProducts) {
		this.targetProducts = targetProducts;
	}

	public double getSupport() {
		return support;
	}

	public void setSupport(double support) {
		this.support = support;
	}
	
	
}
