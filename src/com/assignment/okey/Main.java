package com.assignment.okey;


import java.util.ArrayList;


public class Main {
	
	public static ArrayList<Integer> TasListesi = new ArrayList<>();
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<ArrayList> OyuncuListesi = new ArrayList<>();
	
	/*public static ArrayList<Integer> Player1 = new ArrayList<>();
	public static ArrayList<Integer> Player2 = new ArrayList<>();
	public static ArrayList<Integer> Player3 = new ArrayList<>();
	public static ArrayList<Integer> Player4 = new ArrayList<>();
	*/
	public static Integer gosterge;
	public static Integer okey;
	public static Integer a=0;

	public static void main(String [] args){
		
		Taslar.initializeTiles(TasListesi);
		System.out.println("Tas Listesi = " + TasListesi);
		System.out.println("Tas Sayisi = " + TasListesi.size());
		System.out.println("Taslar Tanimlandi...");
		Oyuncular.initializePlayers(OyuncuListesi, Degiskenler.OyuncuSayisi);
		System.out.println("Oyuncu Listesi = " + OyuncuListesi);
		System.out.println("Oyuncu Sayisi = " + OyuncuListesi.size());	
		
		gosterge = Oyun.gostergeSec(TasListesi);
		System.out.println("Gosterge = " + gosterge);
		okey = Oyun.okeyBelirle(gosterge);
		System.out.println("Okey = " + okey);
		
		Oyun.taslariDagit(OyuncuListesi, TasListesi);
		System.out.println("Oyuncu Listesi = " + OyuncuListesi);
		
		
		int sonucaEnYakinOyuncu = Oyun.kazananOyuncu(OyuncuListesi);
		//------------------------ SONUC --------------------------------------
		System.out.println("------------------------ SONUC --------------------------------------");
		System.out.println("Sonuca En Yakin Oyuncu = " + sonucaEnYakinOyuncu);
		System.out.println(Oyuncular.getPlayerHand(sonucaEnYakinOyuncu));
		
		
	}
	
}
