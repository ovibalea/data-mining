package ro.ubb.rno.services.apriori;

import java.util.Map;

import ro.ubb.rno.model.AssociationRule;
import ro.ubb.rno.model.Product;
import ro.ubb.rno.model.ProductsSet;
import ro.ubb.rno.model.RuleSet;

public class AssociationRulesGenerator {
	
	public final RuleSet generateAssociationRules(
			Map<Integer, ProductsSet> frequentItemSets, double minConfidence) {

		RuleSet ruleSet = new RuleSet();

		for (ProductsSet productSet : frequentItemSets.values()) {
			if (productSet.size() > 1) {
				generateRules(productSet, frequentItemSets, ruleSet, minConfidence);
			}
		}

		return ruleSet;
	}

	private void generateRules(ProductsSet itemSet, Map<Integer, ProductsSet> frequentItemSets,
			RuleSet ruleSet, final double minConfidence) {
		generateRules(itemSet, frequentItemSets, ruleSet, itemSet, null, minConfidence);
	}

	private void generateRules(ProductsSet itemSet,
			Map<Integer, ProductsSet> frequentItemSets,
			RuleSet ruleSet, 
			final ProductsSet body,
			ProductsSet head, 
			final double minConfidence) {
		
		for (Product item : body) {
			
			ProductsSet headItemSet = head != null ? head.clone() : new ProductsSet();
			headItemSet.add(item);
			ProductsSet bodyItemSet = body.clone();
			bodyItemSet.remove(item);
			double existingSupport = frequentItemSets.get(bodyItemSet.hashCode()) != null ? frequentItemSets.get(bodyItemSet.hashCode()).getSupport() : 0.0;
			bodyItemSet.setSupport(existingSupport);
			double targetSupport = frequentItemSets.get(headItemSet.hashCode()) != null ? frequentItemSets.get(headItemSet.hashCode()).getSupport() : 0.0;
            headItemSet.setSupport(targetSupport);
			double support = itemSet.getSupport();
			AssociationRule rule = new AssociationRule(bodyItemSet, headItemSet, support);
			double confidence = AprioriUtils.computeConfidence(rule);

			if (confidence >= minConfidence) {
				ruleSet.add(rule);

				if (bodyItemSet.size() > 1) {
					generateRules(itemSet, frequentItemSets, ruleSet, bodyItemSet, headItemSet, minConfidence);
				}
			}
		}
	}

}
