package Application.Domain;

/**
 * This a entity class for class rooms to contain information of a classroom
 * @author 18206115 Guo Zikang
 */
public class Classroom {
    private int id;
    private String place;
    private int people;

    /**
     * The constructor of this class.
     * @param id the id of a classroom
     * @param place the place of a classroom
     * @param people the people number of a classroom
     */
    public Classroom(int id, String place, int people) {
        this.id = id;
        this.place = place;
        this.people = people;
    }

    /**
     * This method is to return the id of a class room
     * @return the id of a classroom
     */
    public int getId() {
        return id;
    }

}
