package com.assignment.okey;

import java.util.ArrayList;

public class Oyuncular extends Oyun {
	@SuppressWarnings("rawtypes")
	public static void initializePlayers(ArrayList<ArrayList> Oyuncular, Integer numberOfPlayers){
		for (int i = 0; i < numberOfPlayers; i++) {
			Oyuncular.add(new ArrayList<>());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> getPlayerHand(int playerNumber){
		return Main.OyuncuListesi.get(playerNumber);
	}
	
	
}
