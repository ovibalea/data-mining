package ro.ubb.rno.services.apriori.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AprioriCandidatesGenerator {

	public static <T> Set<List<T>> generateCandidates(List<T>  elements, int noOfItemsInSets){

		int noOfElements = elements.size();

		Set<List<T>> candidateItemsSet = new HashSet<>();
		
		if(noOfItemsInSets > noOfElements){
			System.out.println("Invalid input, K > N");
			return candidateItemsSet;
		}
		// calculate the possible combinations
		// e.g. c(4,2)
		//c(noOfElements, noOfItemsInSets);
		
		// get the combination by index 
		// e.g. 01 --> AB , 23 --> CD
		int combination[] = new int[noOfItemsInSets];
		
		// position of current index
		//  if (r = 1)				r*
		//	index ==>		0	|	1	|	2
		//	element ==>		A	|	B	|	C
		int r = 0;		
		int index = 0;
		
		while(r >= 0){
			// possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
			// possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"
			
			// for r = 0 ==> index < (4+ (0 - 2)) = 2
			if(index <= (noOfElements + (r - noOfItemsInSets))){
					combination[r] = index;
					
				// if we are at the last position print and increase the index
				if(r == noOfItemsInSets-1){

					//do something with the combination e.g. add to list or print
					List<T> candidateItems = getItems(combination, elements);
					candidateItemsSet.add(candidateItems);
					index++;				
				}
				else{
					// select index for next position
					index = combination[r]+1;
					r++;										
				}
			}
			else{
				r--;
				if(r > 0)
					index = combination[r]+1;
				else
					index = combination[0]+1;	
			}			
		}
		
		return candidateItemsSet;
	}
	

	
	public static int c(int n, int r){
		int nf=fact(n);
		int rf=fact(r);
		int nrf=fact(n-r);
		int npr=nf/nrf;
		int ncr=npr/rf; 
		
		System.out.println("C("+n+","+r+") = "+ ncr);

		return ncr;
	}
	
	public static int fact(int n)
	{
		if(n == 0)
			return 1;
		else
			return n * fact(n-1);
	}
	

	public static <T> List<T> getItems(int[] combination, List<T> elements){

		List<T> output = new ArrayList<>();
		for(int i = 0 ; i < combination.length;i++){
			output.add(elements.get(combination[i]));
		}
		return output;
	}
}
