package de.saig.podio;


public enum Category {
	ME_AND_MY_SHADOW, PRODUCT_BOX, SPEED_BOAT, PRUNE_THE_PRODUCT_TREE;
	
	
	public int getId () {
		int x = 0;
		if(this==ME_AND_MY_SHADOW){x=1;}
		if(this==PRODUCT_BOX){x=2;}
		if(this==SPEED_BOAT){x=3;}
		if(this==PRUNE_THE_PRODUCT_TREE){x=4;}
		
		return x;
	}

	public static Category getEnum (int x) {
		Category c = null;
		if(x==1)	{c = ME_AND_MY_SHADOW;}
		if(x==2)	{c = PRODUCT_BOX;}
		if(x==3)	{c = SPEED_BOAT;}
		if(x==4)	{c = PRUNE_THE_PRODUCT_TREE;}
		
		return c;
	}
	
	
}
