package com.github.wrightm.tutorial.algorithms.unionfind;

import java.util.Scanner;

import com.github.wrightm.tutorial.algorithms.Helpers;

public class QuickUnionWeightWithHalvingPathCompression {
	
	private final int id[];
	private final int sz[];
	
	public QuickUnionWeightWithHalvingPathCompression(final int numberOfNodes){
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
		for(i = value; i != id[i]; i = id[i] ){
			id[i] = id[id[i]];
		}
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
	
	public boolean allNodesAreConnected(){
		for(int i = 0; i < id.length-1; ++i){
			if(id[i] != id[i+1]){
				return false;
			}
		}
		return true;
	}
	
	public static void quickUnionRandomSample(){
		
		QuickUnionWeightWithHalvingPathCompression quickUnion = new QuickUnionWeightWithHalvingPathCompression(10);
		int count = 0;
		
		while(!quickUnion.allNodesAreConnected()){
			count++;
			System.out.println(count+" ---------------------------------------------- "+count);
			final int p = Helpers.randInt(0, 9); 
			final int q = Helpers.randInt(0, 9);
			int pid = quickUnion.findID(p) , qid = quickUnion.findID(q);
			if(quickUnion.connected(pid, qid)){
				System.out.println("p "+ p + " with pid "+ pid +" and q " + q + " with qid " + qid + " are connected");
				System.out.println("current view of id array: " + quickUnion.convertIdToString());
				System.out.println("current view of sz array: " + quickUnion.convertSizeArrayToString());
				continue;
			}
			System.out.println("p "+ p + " with pid "+ pid +" and q " + q + " with qid " + qid + " are not connected");
			System.out.println("current view of id array before union: " + quickUnion.convertIdToString());
			System.out.println("current view of sz array before union: " + quickUnion.convertSizeArrayToString());
			quickUnion.union(pid, qid);
			final String sid = quickUnion.convertIdToString();
			System.out.println("current view of id array after union: " + quickUnion.convertIdToString());
			System.out.println("current view of sz array after union: " + quickUnion.convertSizeArrayToString());
		}
	}
	
	public static void quickUnionScanner(){
		QuickUnionWeightWithHalvingPathCompression quickUnion = new QuickUnionWeightWithHalvingPathCompression(10);
		
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
		//quickUnionRandomSample();
		quickUnionScanner();
	}
	

}
