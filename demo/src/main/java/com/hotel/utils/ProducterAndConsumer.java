package com.hotel.utils;


class SharePool{//
	int cars ;//20
	public synchronized void producterCar() {
		notify();
		if(cars<20) {
			cars++ ;
			System.out.println("生产车...现在共计车辆："+cars);
		}
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void cosumerCar() {
		//等待通知，共享车中 一旦有车，则激活该方法
		notify();//a 
		if(cars>0) {
			cars-- ;//1->0
			System.out.println("========消费...现在共计车辆："+cars);
		}
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class CarProducter implements Runnable{
	SharePool pool ;
	public CarProducter(SharePool pool) {
		this.pool = pool ;
	}
	@Override
	public void run() {
			while(true) {
				pool.producterCar(); 
			}
	}
		
}

class CarCosumer implements Runnable{
	SharePool pool ;
	public CarCosumer(SharePool pool) {
		this.pool = pool ;
	}
	@Override
	public void run() {
		while(true) {
			pool.cosumerCar();
		}
	}
}



public class ProducterAndConsumer {
	public static void main(String[] args) {
		SharePool pool = new SharePool() ;
		
		CarProducter producter = new CarProducter(pool);
		CarCosumer cosumer = new CarCosumer(pool) ;
		
		
		
		new Thread(producter).start();
		new Thread(producter).start();
		
		
		new Thread(cosumer).start();
		new Thread(cosumer).start();
		
		
		//产生start();
		
		//消费start();
		
	}
}
