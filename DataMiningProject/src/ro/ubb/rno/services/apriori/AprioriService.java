package ro.ubb.rno.services.apriori;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ro.ubb.rno.model.AssociationRule;
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
	
	public Set<Product> getRecommendations(RuleSet rules, List<Product> actualProductsInList) {
		Set<Product> recomandationsSet = new HashSet<>();
		for (AssociationRule rule : rules) {
			if (rule.getExistingProducts().containsAll(actualProductsInList)) {
				recomandationsSet.addAll(rule.getTargetProducts());
			}
		}

		if (recomandationsSet.isEmpty()) {
			for (AssociationRule rule : rules) {
				if (rule.getExistingProducts().contains(actualProductsInList.get(actualProductsInList.size()-1))) {
					recomandationsSet.addAll(rule.getTargetProducts());
				}
			}
		}
		return recomandationsSet;
	}

}
