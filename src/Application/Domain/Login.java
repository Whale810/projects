package Application.Domain;

import Application.Peresistency.loginMapper;

/**
 * This class is for login entity and contain the information of a login
 * @author 18206141 Gong Chenhan
 */
public class Login {

    private int userid;
    private String givenpassword;
    private String Truepass;

    /**
     * The constructor of this class
     * @param userid The id of a login user
     * @param pass The password of a login user
     */
    public Login(int userid,String pass){
        this.userid = userid;
        this.givenpassword = pass;
        Truepass = new loginMapper().getTruepassword(userid);
    }

    /**
     * This method is to check whether the enter password is correct
     * @return true or false of the entered password
     */
    public boolean loginchecker(){
        boolean checker = false;
        if(givenpassword.equals(Truepass)){
            checker = true;
        }
        return checker;
    }

}