package com.github.wrightm.tutorial.algorithms.unionfind;

import java.util.Scanner;

/**
 *  For M pairs > N objects. The quick union algorithm could take more than MN / 2 instructions to solve a connectivity problem.
 * @author wrightm
 *
 */
public class QuickUnion {

	private final int id[];
	
	public QuickUnion(final int numberOfNodes){
		id = new int[numberOfNodes];
		for (int i = 0; i < numberOfNodes ; i++) {
			id[i] = i; 
		}
	}
	
	/**
	 * 
	 * Find operation for N objects is N - 1 for the first N, with an average of N-1/2 for all other nodes
	 * 
	 * @param value
	 * @return
	 */
	public int findID(final int value){
		int i;
		for(i = value; i != id[i]; i = id[i] );
		return i;
	}
	
	public boolean connected(final int p, final int q){
		return p == q;
	}
	
	public void union(final int p, final int q){
		id[p] = q;
	}
	
	public String convertIdToString(){
		String idString = "";
		for(int i = 0; i < id.length; ++i){
			idString += id[i] + " ";
		}
		return idString;
	}
	
	public static void main(String args[]){
		
		QuickUnion quickUnion = new QuickUnion(10);
		
		final Scanner input = new Scanner(System.in);
		while(input.hasNextInt()){ 
			final int p = input.nextInt(), q = input.nextInt();
			int pid = quickUnion.findID(p) , qid = quickUnion.findID(q);
			
			if(quickUnion.connected(pid, qid)){
				System.out.println("p "+ p + " and q " + q + " are connected");
				System.out.println("current view of id array: " + quickUnion.convertIdToString());
				continue;
			}
			System.out.println("p "+ p + " and q " + q + " are not connected");
			quickUnion.union(pid, qid);
			final String sid = quickUnion.convertIdToString();
			System.out.println("current view of id array: " + quickUnion.convertIdToString());
		}
		input.close();
	}
}
