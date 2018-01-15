package ro.ubb.rno.services.apriori;

import ro.ubb.rno.model.AssociationRule;

public class AprioriUtils {

	public static double computeSupport(double frequency, int maxFrequencyPossible) {
		return frequency / maxFrequencyPossible;
	}
	
	 public static double computeConfidence(AssociationRule rule) {
	        double existingProductsSupport = rule.getExistingProducts().getSupport();
	        return existingProductsSupport > 0 ? rule.getSupport() / existingProductsSupport : 0;
	    }
}
