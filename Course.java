
public class Course {

	private String department, name;
	private int credits, prereqYear, maxEnrollment, reservedSeats;
	private Student[] studentList = new Student[1];;
	private Student[] replacement = new Student[1];;
	private Faculty instructor;
	
	
	/**
	 * constructor
	 * @param department
	 * @param name
	 * @param credits
	 * @param prereqYear
	 * @param maxEnrollment
	 * @param reservedSeats
	 */
	public Course(String department, String name, int credits, int prereqYear, int maxEnrollment, int reservedSeats) {
		this.department = department;
		this.name = name;
		this.credits = credits;
		this.prereqYear = prereqYear;
		this.maxEnrollment = maxEnrollment;
		this.reservedSeats = reservedSeats;
	}
	
	/**
	 * this method adds the student to student list
	 * @param std
	 */
	public void addStudent(Student std) {
		studentList[studentList.length - 1] = std;
		Student[] tmp = new Student[studentList.length + 1];
		for (int i = 0; i < studentList.length; i++) {
			tmp[i] = studentList[i];
		}
		studentList = tmp;
	}
	
	/**
	 * this method adds the student to replacement list
	 * @param std
	 */
	public void addReplacement(Student std) {
		replacement[replacement.length - 1] = std;
		Student[] tmp1 = new Student[replacement.length + 1];
		for (int i = 0; i < replacement.length; i++) {
			tmp1[i] = replacement[i];
		}
		replacement = tmp1;
		
	}
	
	/**
	 * this method sets students' register information
	 * @param std
	 * @return
	 */
	public RegisterInfo registerCourse(Student std) {
		RegisterInfo reg = new RegisterInfo();
		if(std.getYear() >= prereqYear) {
			if(std.getMajor().equals(department) && (studentList.length - 1) < (maxEnrollment - reservedSeats)) {
				addStudent(std);
				reg.setCourse(this);
				reg.setRegisterID(studentList.length - 1);
				reg.setResult("APPROVED");
				reg.setRegisterMessage("REQUEST APPROVED");
				std.addRegisterInfo(reg);
				return reg;
			}
			else if(!std.getMajor().equals(department) && (((studentList.length - 1) / (maxEnrollment - reservedSeats)) * 100) < 70) {
				addStudent(std);
				reg.setCourse(this);
				reg.setRegisterID(studentList.length - 1);
				reg.setResult("APPROVED");
				reg.setRegisterMessage("REQUEST APPROVED");
				std.addRegisterInfo(reg);
				return reg;
			}
			else if((studentList.length - 1) + reservedSeats == maxEnrollment && reservedSeats > 0) {
				addReplacement(std);
				reg.setCourse(this);
				reg.setRegisterMessage("REQUEST WAITING - REPLACEMENT LIST");
				reg.setResult("WAITING");
				std.addRegisterInfo(reg);
				return reg;
			}
			else {
				reg.setCourse(this);
				reg.setResult("REJECTED");
			 	reg.setRegisterMessage("REQUEST REJECTED - QUATO");
			 	std.addRegisterInfo(reg);
				return reg;
			}
		}
		else {
			reg.setCourse(this);
			reg.setResult("REJECTED");
		 	reg.setRegisterMessage("REQUEST REJECTED - PREREQUEST");
		 	std.addRegisterInfo(reg);
			return reg;
		}
		
	}
	
	/**
	 * this method adds the instructor to this course and set the instructors' assign information
	 * @param instructor
	 * @param force
	 * @return
	 */
	public AssignInfo AssignInstructor(Faculty instructor, boolean force) {
		AssignInfo ass1 = new AssignInfo();
		if (instructor.getDepartmentName().equals(department)) {
			if (this.instructor == null) {
				this.instructor = instructor;
				ass1.setCourse(this);
				ass1.setAssignMessage("INSTRUCTOR " + instructor.getName() + " " + instructor.getSurname() + " ASSIGNED");
				ass1.setAssignResult("APPROVED");
				instructor.addAssignInfo(ass1);
				return ass1;
			}
			else if (force == true) {
				this.instructor.withdrawAssignInfo(this);
				this.instructor = instructor;
				ass1.setCourse(this);
				ass1.setAssignMessage("INSTRUCTOR " + instructor.getName() + " " + instructor.getSurname() + " ASSIGNED");
				ass1.setAssignResult("APPROVED");
				instructor.addAssignInfo(ass1);
				return ass1;
			}
			else {
				ass1.setCourse(this);
				ass1.setAssignMessage("ANOTHER INSTRUCTOR HAS ALREADY ASSIGNED");
				ass1.setAssignResult("REJECTED");
				return ass1;
				
			}
		}
		else {
			ass1.setCourse(this);
			ass1.setAssignMessage("DEPARTMENT MISMATCH");
			ass1.setAssignResult("REJECTED");
			return ass1;
		}
		

	}
	
	/**
	 * this method adds the student who is replacement list to student list
	 */
	public void RegisterReplacementList() {
		RegisterInfo reg = new RegisterInfo();
		for (int i = 0 ; i < replacement.length ; i++) {
			if (replacement[i] != null && (replacement[i].getMajor() == department && (maxEnrollment - (studentList.length - 1)) > 0)) {
				System.out.println(replacement[i].getName() + " " + replacement[i].getSurname() + " assigned to " + name + " from replacement list.");
				addStudent(replacement[i]);
				reg.setCourse(this);
				reg.setRegisterID(studentList.length - 1);
				reg.setResult("APPROVED");
				reg.setRegisterMessage("REQUEST APPROVED");
				replacement[i].removeRegisterInfo(this);
				replacement[i].addRegisterInfo(reg);
				replacement[i] = null;
			}
		}
		for (int i = 0 ; i < replacement.length ; i++) {
			if (replacement[i] != null && replacement[i].getMajor() != department && (maxEnrollment - (studentList.length - 1)) > 0) {
				System.out.println(replacement[i].getName() + " " + replacement[i].getSurname() + " assigned to " + name + " from replacement list.");
				addStudent(replacement[i]);
				reg.setCourse(this);
				reg.setRegisterID(studentList.length - 1);
				reg.setResult("APPROVED");
				reg.setRegisterMessage("REQUEST APPROVED");
				replacement[i].removeRegisterInfo(this);
				replacement[i].addRegisterInfo(reg);
				replacement[i] = null;
			}
			
		}
		int hold = 0;
		for (int i = 0 ; i < replacement.length ; i++) {
			if (replacement[i] == null) {
				hold++;
			}
		}
		if (hold == replacement.length) {
			System.out.println("NO STUDENT IN REPLACEMENT LIST");
		}
	}

	/**
	 * 
	 */
	public void printStudentList() {
		System.out.println("COURSE : " + name + "\tDeparment : " + department);
		for (int i = 0; i < studentList.length; i++) {
			if(studentList[i] != null) {
			System.out.println("STUDENT ID : " + studentList[i].getID() + "\tName : " + studentList[i].getName() + " "
					+ studentList[i].getSurname());
			}
		}
		System.out.println("Replacement List");

		for (int i = 0; i < replacement.length; i++) {
			if(studentList[i] != null) {
			System.out.println("STUDENT ID : " + studentList[i].getID() + "\tName : " + studentList[i].getName() + " "
					+ studentList[i].getSurname());
			}
		}

	}

	/**
	 * 
	 * @return
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * 
	 * @return
	 */
	public int getPrereqYear() {
		return prereqYear;
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxEnrollment() {
		return maxEnrollment;
	}
	/**
	 * 
	 * @return
	 */
	public int getReservedSeats() {
		return reservedSeats;
	}

	/**
	 * 
	 * @return
	 */
	public Student[] getStudentList() {
		return studentList;
	}

	/**
	 * 
	 * @return
	 */
	public Student[] getReplacement() {
		return replacement;
	}

	/**
	 * 
	 * @return
	 */
	public Faculty getInstructor() {
		return instructor;
	}

	/**
	 * 
	 * @param department
	 * @return
	 */
	public String setDepartment(String department) {
		return this.department = department;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String setName(String name) {
		return this.name = name;
	}

	/**
	 * 
	 * @param credits
	 * @return
	 */
	public int setCredits(int credits) {
		return this.credits = credits;
	}

	/**
	 * 
	 * @param prereqYear
	 * @return
	 */
	public int setPrereqYear(int prereqYear) {
		return this.prereqYear = prereqYear;
	}

	/**
	 * 
	 * @param maxEnrollment
	 * @return
	 */
	public int setMaxEnrollment(int maxEnrollment) {
		return this.maxEnrollment = maxEnrollment;
	}

	/**
	 * 
	 * @param reservedSeats
	 * @return
	 */
	public int setReservedSeats(int reservedSeats) {
		return this.reservedSeats = reservedSeats;
	}

	/**
	 * 
	 * @param studentList
	 * @return
	 */
	public Student[] setStudentList(Student[] studentList) {
		return this.studentList = studentList;
	}

	/**
	 * 
	 * @param replacement
	 * @return
	 */
	public Student[] setReplacement(Student[] replacement) {
		return this.replacement = replacement;
	}

	/**
	 * 
	 * @param instructor
	 * @return
	 */
	public Faculty setInstructor(Faculty instructor) {
		return this.instructor = instructor;
	}
}


