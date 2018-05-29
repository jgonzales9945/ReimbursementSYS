package com.revature.pojos;

public class ReimStatus {
	private int[] ID = {1,2,3,4};
	private String[] status = {"pending","accepted", "rejected","closed"};
	
	public int getID(String status) {
		for(int i = 0; i < 4; i++) {
			if(this.status[i].equals(status.toLowerCase())) {
				return ID[i];
			}
		}
		return 4;
	}
	public String getStatus(int i) {
		for(int id  = 0; id < 4; id++) {
			if(ID[id] == i) return status[id];
		}
		return status[3];
	}
	public ReimStatus() {
		// TODO Auto-generated constructor stub
	}
	public int getID(int i) {
		return ID[i];
	}

}
