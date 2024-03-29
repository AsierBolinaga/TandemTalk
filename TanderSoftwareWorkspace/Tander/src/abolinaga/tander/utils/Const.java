package abolinaga.tander.utils;

/**
 * The Class Const is a single common class to hold all the app Constants.
 */
public class Const
{
	/** Address of our scripts of the CRUD */    
	public static final String URL_REGISTER = "http://abtander.16mb.com/PhpScripts/registerUser.php";   
	public static final String URL_GET_ALL = "http://abtander.16mb.com/PhpScripts/getAll.php";   
	public static final String URL_LOGGIN_USER = "http://abtander.16mb.com/PhpScripts/logginUser.php";     
	public static final String URL_UPDATE_USERSTATUS = "http://abtander.16mb.com/PhpScripts/updateUserStatus.php";    
	public static final String URL_DELETE_USER = "http://abtander.16mb.com/PhpScripts/deleteUser.php"; 
	
	/** Keys that will be used to send the request to php scripts */   
	public static final String KEY_USER_ID = "id";    
	public static final String KEY_USER_NAME = "name";    
	public static final String KEY_USER_USERNAME = "username";    
	public static final String KEY_USER_PASSWORD = "password";
	public static final String KEY_USER_EMAIL = "email";
	public static final String KEY_USER_ONLINE = "online";
	
	/** JSON Tags */    
	public static final String TAG_JSON_ARRAY="result";    
	public static final String TAG_ID = "id";    
	public static final String TAG_NAME = "name";   
	public static final String TAG_USERNAME = "username";    
	public static final String TAG_PASSWORD = "password";
	public static final String TAG_EMAIL = "email";
	public static final String TAG_ONLINE = "online";
		
	/** user id to pass with intent */    
	public static final String USER_ID = "user_id";
	
	/** The Constant EXTRA_DATA. */
	public static final String EXTRA_DATA = "extraData";
}
