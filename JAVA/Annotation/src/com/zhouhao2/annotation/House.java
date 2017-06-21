package com.zhouhao2.annotation;

public interface House {
     /**
     * @deprecated use of open 
     * is discouraged, use
     * openFrontDoor or 
     * openBackDoor instead.
     */
	@Deprecated
    void open();
    void openFrontDoor();
    void openBackDoor();
}
