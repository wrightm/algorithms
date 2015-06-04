package com.github.wrightm.tutorial.algorithms.unionfind;

import java.util.Scanner;

public class UnionFind {

	private int id[];
	
	public UnionFind(final int numberOfNodes) {
		id = new int[numberOfNodes];
		for (int i = 0; i < numberOfNodes ; i++) {
			id[i] = i; 
		}
	}
	
	// Constant
	public boolean connected(final int p, final int q){
		return id[p] == id[q];
	}
	
	// O(N)
	public void union(final int p, final int q){
		final int pid = id[p];
		final int qid = id[q];
		for(int i = 0; i < id.length; ++i){
			if(id[i] == pid){
				id[i] = qid;
			}
		}
	}
	
	public String convertIdToString(){
		String idString = "";
		for(int i = 0; i < id.length; ++i){
			idString += id[i] + " ";
		}
		return idString;
	}
	
	public static void main(String[] args) { 
		
		final UnionFind unionFind = new UnionFind(10);
		final Scanner input = new Scanner(System.in);
		while(input.hasNextInt()){ 
			final int p = input.nextInt(), q = input.nextInt();
			boolean isConnected = unionFind.connected(p, q);
			System.out.println("Is " + p + " connected to " + q + " " + isConnected);
			if(!isConnected){
				System.out.println(p + " is not connected to " + q + " lets connect");
				unionFind.union(p, q);
				isConnected = unionFind.connected(p, q);
				System.out.println("Is " + p + " now connected to " + q + " " + isConnected);
			}
			System.out.println("current view of id array: " + unionFind.convertIdToString());
		}
		input.close();
	}
}
