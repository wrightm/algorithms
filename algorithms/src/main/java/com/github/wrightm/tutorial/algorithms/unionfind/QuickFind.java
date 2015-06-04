package com.github.wrightm.tutorial.algorithms.unionfind;

import java.util.Scanner;

/**
 * 
 * The quick find algorithm executes at least MN instructions to solve a connectivity problem
 * with N objects that involves M union operations.
 * 
 * @author wrightm
 *
 */
public class QuickFind {

	private int id[];
	
	public QuickFind(final int numberOfNodes) {
		id = new int[numberOfNodes];
		for (int i = 0; i < numberOfNodes ; i++) {
			id[i] = i; 
		}
	}
	
	// Constant 0(1)
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
		
		final QuickFind unionFind = new QuickFind(10);
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
