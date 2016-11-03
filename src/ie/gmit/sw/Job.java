package ie.gmit.sw;

public class Job {
	private String s1;
	private String s2;
	private String taskNumber;
	private int score;
	
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Job(String s1, String s2, String taskNumber) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.setTaskNumber(taskNumber);
	}
	public String getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	
	
}