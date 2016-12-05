package ie.gmit.sw.servelet;

public class Request {
	private String s1;
	private String s2;
	private String taskNumber;
	private String algo;

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

	public Request(String s1, String s2, String taskNumber, String algo) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.setTaskNumber(taskNumber);
		this.setAlgo(algo);
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

}
