package com.github.wrightm.tutorial.algorithms.unionfind;

import java.util.Scanner;

import com.github.wrightm.tutorial.algorithms.Helpers;

/**
*  For M pairs and N objects. The quick union weight algorithm takes at worst MLogN instructions to solve a connectivity problem.
* @author wrightm
*
*/
public class QuickUnionWeight {

	private final int id[];
	private final int sz[];
	
	public QuickUnionWeight(final int numberOfNodes){
		id = new int[numberOfNodes];
		sz = new int[numberOfNodes];
		for (int i = 0; i < numberOfNodes ; i++) {
			id[i] = i;
			sz[i] = 1;
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
		if(sz[p] < sz[q]){
			id[p] = q;
			sz[q] += sz[p];
		}
		else {
			id[q] = p;
			sz[p] += sz[q];
		}
	}
	
	public String convertIdToString(){
		String idString = "";
		for(int i = 0; i < id.length; ++i){
			idString += id[i] + " ";
		}
		return idString;
	}
	
	public String convertSizeArrayToString(){
		String szString = "";
		for(int i = 0; i < id.length; ++i){
			szString += sz[i] + " ";
		}
		return szString;
	}
	
	public static void quickUnionRandomSample(){
		
		QuickUnionWeight quickUnion = new QuickUnionWeight(10);
		final int sampleSize = 1000000;
		int[][] sample = Helpers.sample(sampleSize);
		for(int element = 0; element < sampleSize-1; ++element){
			final int p = sample[element][0]; 
			final int q = sample[element][1];
			int pid = quickUnion.findID(p) , qid = quickUnion.findID(q);
			System.out.println("current view of id array: " + quickUnion.convertIdToString());
			if(quickUnion.connected(pid, qid)){
				System.out.println("p "+ p + " and q " + q + " are connected");
				System.out.println("current view of id array: " + quickUnion.convertIdToString());
				continue;
			}
			System.out.println("p "+ p + " and q " + q + " are not connected");
			quickUnion.union(pid, qid);
			final String sid = quickUnion.convertIdToString();
			System.out.println("current view of id array after union: " + quickUnion.convertIdToString());
		}
	}
	
	public void quickUnionScanner(){
		QuickUnionWeight quickUnion = new QuickUnionWeight(10);
		
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
			System.out.println("current view of id array: " + quickUnion.convertIdToString());
			System.out.println("current view of sz array: " + quickUnion.convertSizeArrayToString());
			quickUnion.union(pid, qid);
			final String sid = quickUnion.convertIdToString();
			System.out.println("current view of id array after union: " + quickUnion.convertIdToString());
			System.out.println("current view of sz array after union: " + quickUnion.convertSizeArrayToString());
		}
		input.close();
	}
	
	public static void main(String args[]){
		quickUnionRandomSample();
	}
}
