package Application.Domain;

/**
 * This is the class for a booking to contain the information of a booking
 * @author 18206115 Guo Zikang
 */
public class booking {

	int bid;
	int cid;
	int tid;
	int gid;
	String date;
	int section;

	/**
	 * This is the constructor for this class
	 * @param bid the booking id
	 * @param cid the classroom id
	 * @param tid the teacher id
	 * @param gid the group id
	 * @param date the booking date
	 * @param section the booking section
	 */
	public booking(int bid,int cid,int tid,int gid,String date,int section){
		this.bid=bid;
		this.cid=cid;
		this.tid=tid;
		this.gid=gid;
		this.date=date;
		this.section=section;
	}

	/**
	 * This method is to return the classroom id inside a booking
	 * @return classroom id
	 */
	public int getcid() {
		return cid;
	}

	/**
	 * This method is to return the teacher id inside a booking
	 * @return teacher id
	 */
	public int gettid() {
		return tid;
	}

	/**
	 * This method is to return the class group id inside a booking
	 * @return class group id
	 */
	public int getgid() {
		return gid;
	}

	/**
	 * This method is to return the booking date inside a booking
	 * @return booking date
	 */
	public String getdate() {
		return date;
	}

	/**
	 * This method is to return the booking section inside a booking
	 * @return booking section
	 */
	public int getsection() {
		return section;
	}

	/**
	 * This method is to return the booking id inside a booking
	 * @return booking id
	 */
	public int getbid(){
		return bid;
	}

}
