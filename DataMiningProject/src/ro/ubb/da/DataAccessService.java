package ro.ubb.da;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ro.ubb.rno.model.Product;
import ro.ubb.rno.model.ProductsSet;
import ro.ubb.rno.model.RuleSet;
import ro.ubb.rno.model.Transaction;
import ro.ubb.rno.services.apriori.AssociationRulesGenerator;
import ro.ubb.rno.services.apriori.FrequentItemsGenerator;

public class DataAccessService {
	
	private List<Product> productsList;

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

		FrequentItemsGenerator frequentItemsGenerator = new FrequentItemsGenerator();
		AssociationRulesGenerator associationRulesGenerator = new AssociationRulesGenerator();
		Map<Integer, ProductsSet> frequentItemsSets = frequentItemsGenerator.findFrequentItemsSets(products, transactions);
		RuleSet rules = associationRulesGenerator.generateAssociationRules(frequentItemsSets, 0.5);
		this.productsList = products;
	}

	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}
	
}
