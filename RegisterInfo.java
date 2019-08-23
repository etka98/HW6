
public class RegisterInfo {
	private String result, registerMessage;
	private int registerID;
	private Course course;

	public RegisterInfo() {

	}

	public String getResult() {
		return result;
	}

	public String getRegisterMessage() {
		return registerMessage;
	}

	public int getRegisterID() {
		return registerID;
	}

	public Course getCourse() {
		return course;
	}

	public String setResult(String result) {
		return this.result = result;
	}

	public String setRegisterMessage(String registerMessage) {
		return this.registerMessage = registerMessage;
	}

	public int setRegisterID(int registerID) {
		return this.registerID = registerID;
	}

	public Course setCourse(Course course) {
		return this.course = course;
	}
}
