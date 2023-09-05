package entity;

public class Training {
	private String id;
	private String password;
	private int groupId;
	private String groupName;
	private int eventId;
	private String eventName;
	private int kg;
	private int reps;
	private int sets;
	private int count;
	private String dt;

	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public int getGroupId() {return groupId;}
	public void setGroupId(int groupId) {this.groupId = groupId;}
	public String getGroupName() {return groupName;}
	public void setGroupName(String groupName) {this.groupName = groupName;}
	public int getEventId() {return eventId;}
	public void setEventId(int eventId) {this.eventId = eventId;}
	public String getEventName() {return eventName;}
	public void setEventName(String eventName) {this.eventName = eventName;}
    public int getKg() {return kg;}
    public void setKg(int kg) {this.kg = kg;}
    public int getReps() {return reps;}
    public void setReps(int reps) {this.reps = reps;}
    public int getSets() {return sets;}
    public void setSets(int sets) {this.sets = sets;}
    public int getCount() {return count;}
    public void setCount(int count) {this.count = count;}
    public String getDt() {return dt;}
	public void setDt(String dt) {this.dt = dt;}
}
