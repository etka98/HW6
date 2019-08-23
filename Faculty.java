
public class Faculty {
	private String name, surname, departmentName;
	private int ID;
	private AssignInfo[] assignInfoList = new AssignInfo[1];

	/**
	 * constructor
	 * @param ID
	 * @param name
	 * @param surname
	 * @param departmentName
	 */
	public Faculty(int ID, String name, String surname, String departmentName) {
		this.name = name;
		this.surname = surname;
		this.departmentName = departmentName;
		this.ID = ID;
	}

	/**
	 * this method removes assign information
	 * @param assignInfo
	 */
	public void removeAssignInfo(AssignInfo assignInfo) {
		for (int i = 0; i < assignInfoList.length; i++) {
			if (assignInfoList[i] == assignInfo) {
				for (int j = i; j < assignInfoList.length; j++) {
					if (j != assignInfoList.length - 1)
						assignInfoList[j] = assignInfoList[j + 1];
				}
			}
		}
		AssignInfo[] tmp2 = new AssignInfo[assignInfoList.length - 1];
		for (int j = 0; j < tmp2.length; j++) {
			tmp2[j] = assignInfoList[j];
			}
		assignInfoList = tmp2;
	}

	/**
	 * this method removes instructors' assign information
	 * @param course
	 * @return
	 */
	public boolean withdrawAssignInfo(Course course) {
		if (course.getInstructor() == this) {
			for (int i = 0; i < assignInfoList.length; i++) {
			if (assignInfoList[i].getCourse() == course) {
				removeAssignInfo(assignInfoList[i]);
			}
		}
			return true;
	}
		else
			return false;

	}

	/**
	 * this method adds the assign information to assign information list
	 * @param assignInfo
	 */
	public void addAssignInfo(AssignInfo assignInfo) {
		assignInfoList[assignInfoList.length-1] = assignInfo;
		AssignInfo[] tmp3 = new AssignInfo[assignInfoList.length + 1];
		for (int i = 0; i < assignInfoList.length; i++) {
			tmp3[i] = assignInfoList[i];
		}
		assignInfoList = tmp3;
	}
		
	/**
	 * 
	 */
	public void printCourseList() {

		System.out.println("Instructor : " + name + " " + surname + " Department : " + departmentName);
		for (int i = 0; i < assignInfoList.length; i++) {
			if (assignInfoList[i] == null) {
				System.out.println("No Course Assigned");
			}
			else
			System.out.println("Course : " + assignInfoList[i].getCourse().getName());
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public int getID() {
		return ID;
	}

	public AssignInfo[] getAssignInfo() {
		return assignInfoList;
	}

	public String setName(String name) {
		return this.name = name;
	}

	public String setSurname(String surname) {
		return this.surname = surname;
	}

	public String setDeaprtmentName(String departmentName) {
		return this.departmentName = departmentName;
	}

	public int setID(int ID) {
		return this.ID = ID;
	}

	public AssignInfo[] setAssignInfo(AssignInfo[] assignInfoList) {
		return this.assignInfoList = assignInfoList;
	}

}
