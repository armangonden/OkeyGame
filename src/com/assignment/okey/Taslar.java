package com.assignment.okey;

import java.util.ArrayList;

public class Taslar extends Oyun {
	public static void initializeTiles(ArrayList<Integer> tiles){
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 53; j++) {
				tiles.add(j);
			}
		}
	}
}
