package ro.ubb.rno.model;

public class Product {

	private String productName;
	private String brandName;
	
	public Product(String productName, String brandName) {
		super();
		this.productName = productName;
		this.brandName = brandName;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", brandName=" + brandName + "]";
	}
	
	
}
