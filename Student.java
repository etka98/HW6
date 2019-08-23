
public class Student {
	private String name, surname, major;
	private int ID, year;
	private RegisterInfo[] registerList = new RegisterInfo[1];
	/**
	 * constructor
	 * @param name
	 * @param surname
	 * @param major
	 * @param ID
	 * @param year
	 */
	public Student(String name, String surname, String major, int ID, int year) {
		this.name = name;
		this.surname = surname;
		this.major = major;
		this.ID = ID;
		this.year = year;
	}
	/**
	 * this method removes the register information 
	 * @param registerInfo
	 */
	public void removeRegisterInfoList(RegisterInfo registerInfo) {
		for(int i = 0; i < registerList.length; i++) {
			if(registerList[i] == registerInfo) {
				for(int j = i; j < registerList.length; j++) {
					if(j != registerList.length-1)
						registerList[j] = registerList[j+1];
				}
			}
		}
   		RegisterInfo[] tmp1 = new RegisterInfo[registerList.length-1];
		for(int j = 0; j < tmp1.length; j++) {
			tmp1[j] = registerList[j];
		}
			registerList = tmp1;	
	}
	
	/**
	 * this method adds the register info to student's register information list
	 * @param registerInfo
	 */
	public void addRegisterInfo(RegisterInfo registerInfo) {
		registerList[registerList.length-1] = registerInfo;
		RegisterInfo[] tmp = new RegisterInfo[registerList.length+1];
		for(int i = 0; i < registerList.length; i++) {
			tmp[i] = registerList[i];
		}
		registerList = tmp;
	}
	
	/**
	 * this method checks if the given course removed or not
	 * @param course
	 * @return a boolean
	 */
	public boolean removeRegisterInfo(Course course) {
		for(int i = 0; i < registerList.length; i++) {
			if(course != null && (registerList[i].getCourse().getName().equals(course.getName()) && registerList[i].getResult().equals("WAITING"))) {
				removeRegisterInfoList(registerList[i]);
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * 
	 */
	public void printCourseList() {
		System.out.print("STUDENT : " + name + " " + surname);
		System.out.print(" MAJOR : " + major);
		System.out.println();
		for(int i = 0; i < registerList.length; i++) {
			if(registerList[i] != null) {
			System.out.print("COURSE : " + registerList[i].getCourse().getName());
			System.out.print(" STATUS : " + registerList[i].getRegisterMessage());
			
			}
		}
		System.out.println();
		System.out.println("TOTAL CREDITS : " + getTotalCredit());
		
	
	}
	
	/**
	 * this method adds the total credits
	 * @return a int 
	 */
	public int getTotalCredit() {
		int sum = 0;
		for(int i = 0; i < registerList.length; i++) {
			if(registerList[i] != null && (registerList[i].getResult().equals("APPROVED") || registerList[i].getResult().equals("WAITING")))
				sum += registerList[i].getCourse().getCredits();
		}
		return sum;
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
	public String getSurname() {
		return surname;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMajor() {
		return major;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * 
	 * @return
	 */
	public RegisterInfo[] getRegisterList() {
		return registerList;
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
	 * @param surname
	 * @return
	 */
	public String setSurname(String surname) {
		return this.surname = surname;
	}
	
	/**
	 * 
	 * @param major
	 * @return
	 */
	public String setMajor(String major) {
		return this.major = major;
	}
	
	/**
	 * 
	 * @param ID
	 * @return
	 */
	public int setID(int ID) {
		return this.ID = ID;
	}
	
	/**
	 * 
	 * @param year
	 * @return
	 */
	public int setYear(int year) {
		return this.year = year;
	}
	
	/**
	 * 
	 * @param registerList
	 */
	public void setRegisterList(RegisterInfo[] registerList) {
		this.registerList = registerList;
	}
}
