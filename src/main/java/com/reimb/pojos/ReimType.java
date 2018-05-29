package com.revature.pojos;

public class ReimType {
	
	private int[] ID = {1,2,3,4};
	private String[] type = {"lodging","travel","food","other"};
	
	public ReimType() {
		// TODO Auto-generated constructor stub
	}
	public int getID(String type) {
		for(int i = 0; i < 4; i++) {
			if(this.type[i].equals(type.toLowerCase())) {
				return ID[i];
			}
		}
		return 4;
	}
	public String getType(int i) {
		for(int id  = 0; id < 4; id++) {
			if(ID[id] == i) return type[id];
		}
		return type[3];
	}
	public int getID(int type2) {
		type2--;
		return ID[type2];
	}

}
