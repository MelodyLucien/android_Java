package com.zhouhao2.annotation;

public class LitteHouse_another  implements House{

        @Deprecated
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
		new LitteHouse_another().open();
	}

}
