package ro.ubb.da;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.SeparatorUI;

import ro.ubb.rno.model.AssociationRule;
import ro.ubb.rno.model.Product;
import ro.ubb.rno.model.ProductsSet;
import ro.ubb.rno.model.RuleSet;
import ro.ubb.rno.model.Transaction;
import ro.ubb.rno.services.apriori.AprioriService;
import ro.ubb.rno.services.apriori.AssociationRulesGenerator;
import ro.ubb.rno.services.apriori.FrequentItemsGenerator;

public class DataAccessService {

	private List<Product> productsList;
	private RuleSet rules;
	private AprioriService aprioriService;

	public DataAccessService() {
		super();

		Product pr1 = new Product("banane1", "Dole");
		Product pr2 = new Product("portocale2", "citrus");
		Product pr3 = new Product("bicuiti3", "biscrem");
		Product pr4 = new Product("lamai4", "Citrus");
		Product pr5 = new Product("suc5", "Pfaner");
		List<Product> products = Arrays.asList(pr1, pr2, pr3, pr4, pr5);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction(1, Arrays.asList(pr1, pr3, pr4)));
		transactions.add(new Transaction(2, Arrays.asList(pr2, pr3, pr5)));
		transactions.add(new Transaction(3, Arrays.asList(pr1, pr2, pr3, pr5)));
		transactions.add(new Transaction(4, Arrays.asList(pr2, pr5)));

		aprioriService = new AprioriService();
		rules = aprioriService.generateAssociationRules(products, transactions);
		this.productsList = products;
	}

	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}

	public Set<Product> getRecommendations(List<Product> actualProductsInList) {
		return aprioriService.getRecommendations(rules, actualProductsInList);
	}

}
