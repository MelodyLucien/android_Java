package com.zhouhao2.annotation;

public class LitteHouse  implements House{

	@Schdule(breakfast="main food",lunch="main food",dinner="main food")
        @SuppressWarnings("deprecation")
	public void open() {
		System.out.println("open");
	}

	@Override
	public void openFrontDoor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openBackDoor() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new LitteHouse().open();
	}

}
