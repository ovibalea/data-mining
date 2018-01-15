package ro.ubb.rno.services.apriori;

import java.util.List;
import java.util.Map;

import ro.ubb.rno.model.Product;
import ro.ubb.rno.model.ProductsSet;
import ro.ubb.rno.model.RuleSet;
import ro.ubb.rno.model.Transaction;

public class AprioriService {

	private FrequentItemsGenerator frquentItemsGenerator;
	private AssociationRulesGenerator associationsRulesGenarator;
	
	public AprioriService() {
		frquentItemsGenerator = new FrequentItemsGenerator();
		associationsRulesGenarator = new AssociationRulesGenerator();
	}
	
	public RuleSet generateAssociationRules(List<Product> productsList, List<Transaction> transactionsList) {
		Map<Integer, ProductsSet> frequentItemSets = frquentItemsGenerator.findFrequentItemsSets(productsList, transactionsList);
		return associationsRulesGenarator.generateAssociationRules(frequentItemSets, 0.5);
	}
	

}
