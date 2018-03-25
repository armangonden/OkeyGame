package com.assignment.okey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Oyun {
	
	public static int gostergeSec(ArrayList<Integer> tasListesi){
		int gosterge = 0;
		boolean isValid = false;
		while(!isValid){
			Random rand = new Random(); 
			int randomValue = rand.nextInt(tasListesi.size()); 
			gosterge = tasListesi.get(randomValue);

			//Sahte Okey Kontrolü
			if(gosterge < 52){
				tasListesi.remove(randomValue);
				isValid = true;
			}
		}
		return gosterge;
	}
	
	public static int okeyBelirle(Integer gosterge){
		int mod = gosterge%13;
		int div = gosterge/13;
		int okey = 0;
		
		//Gosterge'nin 13 olmasý durumu kontrolü
		if(mod == 12){
			okey = gosterge - 12;
		}
		else{
			okey = gosterge + 1;
		}
		
		return okey;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void taslariDagit(ArrayList<ArrayList> oyuncuListesi, ArrayList<Integer> tasListesi){
		Random rand = new Random();
		int fazlaTasAlacakOyuncu = 0;
		fazlaTasAlacakOyuncu = rand.nextInt(oyuncuListesi.size());
		for (int i = 0; i < oyuncuListesi.size(); i++) {
			if(i == fazlaTasAlacakOyuncu){
				taslariDagit(15,oyuncuListesi.get(i), tasListesi);
			}
			else{
				taslariDagit(14,oyuncuListesi.get(i),tasListesi);
			}
		}
	}
	
	static void taslariDagit(Integer num, ArrayList<Integer> oyuncuListesi, ArrayList<Integer> tasListesi){
		Random rand = new Random(); 
		int value = 0;
		for (int i = 0; i < num; i++) {
			value = rand.nextInt(tasListesi.size()); 
			oyuncuListesi.add(tasListesi.get(value));
			tasListesi.remove(value);
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "null" })
	public static int[] sonucaEnYakinOyuncu(ArrayList<Integer> Sonuc){
		int min = 15;
		int index = 0;
		int[] result = new int[2];
		for (int i = 0; i < Sonuc.size(); i++) {
			if(Sonuc.get(i) < min){
				min = Sonuc.get(i);
				index = i;
			}
		}
		result[0] = index;
		result[1] = min;
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static int kazananOyuncu(ArrayList<ArrayList> oyuncuListesi){
		int result = 0;
		ArrayList<Integer> CiftSiralamaSonucu = ciftKontrol(oyuncuListesi);
		ArrayList<Integer> SeriSiralamaSonucu = TasKontrol(oyuncuListesi);
		
		
		int[] CiftSiralamaSonucaYakinOyuncu = sonucaEnYakinOyuncu(CiftSiralamaSonucu);
		int[] SeriSiralamaSonucaYakinOyuncu = sonucaEnYakinOyuncu(SeriSiralamaSonucu);
		
		
		if(CiftSiralamaSonucaYakinOyuncu[1] <= SeriSiralamaSonucaYakinOyuncu[1]){
			result = CiftSiralamaSonucaYakinOyuncu[0];
		}
		else{
			result = SeriSiralamaSonucaYakinOyuncu[0];
		}

		return result;
	}

	@SuppressWarnings("rawtypes")
	public static ArrayList<Integer> ciftKontrol(ArrayList<ArrayList> oyuncuListesi){
		ArrayList<Integer> ciftKontrolSonucu = new ArrayList<>();
		for (int i = 0; i < oyuncuListesi.size(); i++) {
			int ciftSayisi = 0;
			Set<Integer> s = new HashSet<>();
			for (Object asd : oyuncuListesi.get(i)) {
				if(s.add((Integer) asd) == false){
					ciftSayisi++;
				}
			}
			ciftKontrolSonucu.add(oyuncuListesi.get(i).size() - (ciftSayisi*2));
		}
		return ciftKontrolSonucu;
	}
	
	static ArrayList<Integer> tekilleþtirme(ArrayList<Integer> Liste){
		ArrayList<Integer> distinct = new ArrayList<>();
		Set<Integer> s = new HashSet<>();
		for (Object asd : Liste) {
			if(s.add((Integer) asd) == true){
				distinct.add((Integer) asd);
			}
		}
		return distinct;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<Integer> TasKontrol(ArrayList<ArrayList> oyuncuListesi){
		
		ArrayList<ArrayList> qwe = new ArrayList<>();
		ArrayList<Integer> pair = new ArrayList<>(); // pairlarin yazýldýðý arraylist
		ArrayList<Integer> pairKontrolSonucu = new ArrayList<>();
				
		int olumluTasSayisi = 0;
		int olumsuzTasSayisi = 0;
		
		//Oyuncular
		for (int i = 0; i < oyuncuListesi.size(); i++) {

			// oyuncularýn taþlarýný küçükten büyüðe sýralama
			Collections.sort(oyuncuListesi.get(i));
			olumluTasSayisi = 0;
			olumsuzTasSayisi = 0;
			int pairKontrol = 0; // sýralý taþlarýn per olabilmesi için en az 3 tane olmalý.
			
			
			//sýralý per kontrolü
			//bir oyuncunun ýstakasýndaki tüm taþlarý dolaþýr.
			for (int j = 0; j < oyuncuListesi.get(i).size()-1; j++) {
					if(Main.okey.equals(oyuncuListesi.get(i).get(j))){
						olumluTasSayisi+=2;
						olumsuzTasSayisi-=2;
					}else{
						//eðer bir sonraki taþ ardýþýk ise bu if e girer.
						if((int) oyuncuListesi.get(i).get(j) == (int) oyuncuListesi.get(i).get(j+1) - 1){
							//bir sonraki taþ için renk kontrolü
							if((int) oyuncuListesi.get(i).get(j)%13 == ((int) oyuncuListesi.get(i).get(j+1)%13)-1){
								//eðer iki sonraki taþ ardýþýk veya bir sonrakine eþit ise
								if( j < oyuncuListesi.get(i).size()-2 && (int) oyuncuListesi.get(i).get(j) >= (int) oyuncuListesi.get(i).get(j+2) - 2){
									pairKontrol++;
									pair.add((Integer) oyuncuListesi.get(i).get(j));
								}
								else{
									if(pairKontrol >= 1){
										pairKontrol++;
										pair.add((Integer) oyuncuListesi.get(i).get(j));
									}
									else{
										olumsuzTasSayisi++;
										pair.clear();
										pairKontrol = 0;
									}
								}
							}
						}
						else{
							if((int) oyuncuListesi.get(i).get(j) != (int) oyuncuListesi.get(i).get(j+1)){
								if(pairKontrol >= 2){
									pairKontrol++;
									pair.add((Integer) oyuncuListesi.get(i).get(j));
									qwe.add(pair);
									olumluTasSayisi += pairKontrol;
									pairKontrol = 0;

								}
								else{
									olumsuzTasSayisi++;
									pair.clear();
									pairKontrol = 0;
								}
							}
							else{
								olumsuzTasSayisi++;
							}
						}
						
					}
				// Farklý renk ayný rakam (Erkek) pair kontrolü
				int count = 0;
				ArrayList<Integer> zxc = new ArrayList<>();
				for (int j2 = 0; j2 < oyuncuListesi.get(i).size(); j2++) {
					if((int) oyuncuListesi.get(i).get(j2) %13 == j){
						zxc.add((Integer) oyuncuListesi.get(i).get(j2));
						count++;

					}
				}
				if(count >=3 && tekilleþtirme(zxc).size() >=3){
					
					olumluTasSayisi += count;
					olumsuzTasSayisi -= count;
					count = 0;
					qwe.add(tekilleþtirme(zxc));
				}
			}


			if(oyuncuListesi.get(i).size() > olumluTasSayisi + olumsuzTasSayisi ){
				olumsuzTasSayisi += oyuncuListesi.get(i).size() - (olumluTasSayisi + olumsuzTasSayisi);
			}
			
			pairKontrolSonucu.add(olumsuzTasSayisi);
			qwe.clear();
		}
		
		return pairKontrolSonucu;
	}
	
}
