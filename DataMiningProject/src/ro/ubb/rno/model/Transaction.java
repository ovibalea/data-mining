package ro.ubb.rno.model;

import java.util.List;

public class Transaction {

	private int transactionId;
	private List<Product> products;
	
	public Transaction(int transactionId, List<Product> products) {
		super();
		this.transactionId = transactionId;
		this.products = products;
	}

	/**
	 * @return the transactionId
	 */
	public int getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	
}
