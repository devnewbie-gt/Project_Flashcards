package DTO;

public class User {
	private String ID, entryDate;
	boolean localUser;

	public User() {
		this.ID = "DummyUser";
		this.entryDate= "00/00/00";
		this.localUser = false;
	}
	public User(String ID, String entryDate) {
		super();
		this.ID = ID;
		this.entryDate = entryDate;
		this.localUser = false;
	}
		
	@Override
	public String toString() {
		return "User [ID=" + ID + ", entryDate=" + entryDate + ", localUser=" + localUser + "]";
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public boolean getIsLocalUser() {
		return localUser;
	}
	public User setLocalUser() {
		this.localUser = true;
		return this;
	}
	public User releaseLocalUser() {
		this.localUser = false;
		return this;
	}
}
