package com.example.karfai;

public class Calculator {
	public static double billCal(double wat) {
		double bill = 0;
		double tmp = 0;

		if (wat < 50) {
			bill = 0;
		} else if (wat < 150) {
			if (wat > 100) {
				tmp = wat - 100;
				wat = wat - tmp;
				bill = bill + tmp * 2.2734;
			}
			if (wat > 35) {
				tmp = wat - 35;
				wat = wat - tmp;
				bill = bill + tmp * 2.1800;
			}
			if (wat > 25) {
				tmp = wat - 25;
				wat = wat - tmp;
				bill = bill + tmp * 1.7968;
			}
			if (wat > 16) {
				tmp = wat - 16;
				wat = wat - tmp;
				bill = bill + tmp * 1.5445;
			}
			if (wat > 6) {
				tmp = wat - 6;
				wat = wat - tmp;
				bill = bill + tmp * 1.3576;
			}
			bill = bill + 8.19;
		} else if(wat>150){
			if(wat>400){
				tmp = wat - 400;
				wat = wat - tmp;
				bill = bill + tmp * 2.9780;
			}
			if(wat>150){
				tmp = wat - 150;
				wat = wat - tmp;
				bill = bill + tmp * 2.7781;
			}
			tmp = wat;
			bill = bill + tmp * 1.8047;
			bill = bill + 40.90;

		}
			return bill/1000.0;
	}
	
	
	public static double watCal(Data data){
		double totalWat = 0;
		double time = (data.getTime()*(data.getDay()));// hour
		totalWat = time  * data.getAmount()*data.getWat();
		return totalWat;
	}

}
