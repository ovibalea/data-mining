package ro.ubb.rno.model;

import java.util.ArrayList;
import java.util.Collection;

public class ProductsSet extends ArrayList<Product>{

	private static final long serialVersionUID = 1L;
	
	private double support;

	public ProductsSet(Product...products) {
		super();
		this.support = 0;
		for(Product prod : products) {
			this.add(prod);
		}
	}
	
	public ProductsSet() {
		this.support = 0;
	}
	
	public ProductsSet(double support, Collection<Product> products) {
		super();
		this.support = support;
		this.addAll(products);
	}
	
	public ProductsSet(double support) {
		super();
		this.support = support;
	}

	public double getSupport() {
		return support;
	}

	public void setSupport(double support) {
		this.support = support;
	}
	
	@Override
	public ProductsSet clone() {
		ProductsSet productSet = new ProductsSet(support, this);
		return productSet;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductsSet other = (ProductsSet) obj;
		if (Double.doubleToLongBits(support) != Double.doubleToLongBits(other.support))
			return false;
		return true;
	}
	
	
	
}
