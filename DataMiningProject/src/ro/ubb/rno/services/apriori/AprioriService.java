package ro.ubb.rno.services.apriori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ro.ubb.rno.model.Product;
import ro.ubb.rno.model.Transaction;

public class AprioriService {

	public static void main(String[] args) {
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

		AprioriService service = new AprioriService();
		service.executeApriori(products, transactions);
	}

	public void executeApriori(List<Product> productsList, List<Transaction> transactionsList) {

		List<List<Product>> frequentItemsSets = new ArrayList<>();
		
		//initial items set
		List<List<Product>> candidateProducts = generateInitialItemSets(productsList);
		int i = 1;
		while (!candidateProducts.isEmpty()) {
			Map<List<Product>, Integer> frequentCandidatesFrequencyMap = computeFrequenctCandidateItemSetFrquency(candidateProducts,
					transactionsList);
			List<List<Product>> frequrentCandidates = computeFrequentItemSet(frequentCandidatesFrequencyMap, 0.5,
					transactionsList.size());
			frequentItemsSets.addAll(frequrentCandidates);
			candidateProducts = combineItemSets(frequrentCandidates, i);
			i++;
		}

		for (List<Product> itemsSet : frequentItemsSets) {
			String line = "";
			for(Product prod : itemsSet){
				line += prod + " ";
			}
			System.out.println(line);
		}


	}

	private List<List<Product>> generateInitialItemSets(List<Product> productsList) {
		List<List<Product>> initialItemsSets = new ArrayList<>();
		for (Product product : productsList) {
			initialItemsSets.add(Arrays.asList(product));
		}
		return initialItemsSets;
	}

	private Map<List<Product>, Integer> computeFrequenctCandidateItemSetFrquency(List<List<Product>> itemsSet,
			List<Transaction> transactions) {
		Map<List<Product>, Integer> frequencyTable = new HashMap<>();

		for (List<Product> items : itemsSet) {
			frequencyTable.put(items, 0);
			for (Transaction transaction : transactions) {
				if (transaction.getProducts().containsAll(items)) {
					int frequency = frequencyTable.get(items) + 1;
					frequencyTable.put(items, frequency);
				}
			}
		}
		return frequencyTable;
	}

	private List<List<Product>> computeFrequentItemSet(Map<List<Product>, Integer> frequentCandidateItemsSet,
			double minSupport, int noOfTransactions) {
		List<List<Product>> frequentItemsSet = new ArrayList<>();

		for (List<Product> items : frequentCandidateItemsSet.keySet()) {
			int frequency = frequentCandidateItemsSet.get(items);
			if (computeSupport((double) frequency, noOfTransactions) >= minSupport) {
				frequentItemsSet.add(items);
			}
		}

		return frequentItemsSet;
	}

	private double computeSupport(double frequency, int maxFrequencyPossible) {
		return frequency / maxFrequencyPossible;
	}

	/**
	 * @param itemSets
	 *            A list, which contains the item sets, which should be combined
	 *            in order to create new item sets, as an instance of the type
	 *            {@link List} or an empty list, if no item sets are available
	 * @param currentItemsSetLength
	 *            The length of the given item sets as an {@link Integer} value
	 * @return A collection, which contains the item sets, which have been
	 *         created
	 */
	private List<List<Product>> combineItemSets(List<List<Product>> itemSets, final int currentItemsSetLength) {
		List<List<Product>> combinedItemSets = new LinkedList<>();

		for (int i = 0; i < itemSets.size(); i++) {
			for (int j = i + 1; j < itemSets.size(); j++) {
				List<Product> itemSet1 = itemSets.get(i);
				List<Product> itemSet2 = itemSets.get(j);
				Iterator<Product> iterator1 = itemSet1.iterator();
				Iterator<Product> iterator2 = itemSet2.iterator();
				Product itemToAdd = null;
				int index = 0;

				while (iterator1.hasNext() && iterator2.hasNext()) {
					Product item2 = iterator2.next();

					if (index < currentItemsSetLength - 1) {
						if (!item2.equals(iterator1.next())) {
							itemToAdd = null;
							break;
						}
					} else {
						itemToAdd = item2;
					}

					index++;
				}

				if (itemToAdd != null) {
					List<Product> combinedItemSet = new ArrayList<>(itemSet1);
					combinedItemSet.add(itemToAdd);
					combinedItemSets.add(combinedItemSet);
				}
			}
		}

		return combinedItemSets;
	}

}
