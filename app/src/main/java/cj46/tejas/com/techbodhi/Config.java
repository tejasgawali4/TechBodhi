package cj46.tejas.com.techbodhi;

/**
 * Created by Carl_johnson on 12/5/2016.
 */
public class Config {
    //Address of our scripts of the CRUD

    public static final String URL_ADD_USER="http://192.168.1.120/Techbodhi/registerUser.php";
    public static final String URL_ADD_POST = "http://192.168.1.120/Techbodhi/addPost.php";
    public static final String URL_CHECK_USER ="http://192.168.1.120/Techbodhi/checkUser.php";
    public static final String URL_VIEW_USER_BYID="http://192.168.1.120/Techbodhi/ViewUserById.php?u_id=";
    public static final String URL_VIEW_USERS = "http://192.168.1.120/Techbodhi/viewUsers.php";
    public static final String URL_VIEW_POSTS = "http://192.168.1.120/Techbodhi/viewPost.php";
    public static final String URL_APPROVAL_USER = "http://192.168.1.120/Techbodhi/viewUserApprovals.php";
    public static final String URL_APPROVE = "http://192.168.1.120/Techbodhi/ApproveUser.php";
    public static final String URL_APPROVAL_POST = "http://192.168.1.120/Techbodhi/approvalPost.php?u_id=";



    //http://platformx.co.in/tbAndroidApp/viewUsers.php
/*
    public static final String URL_ADD_USER="http://platformx.co.in/tbAndroidApp/registerUser.php";
    public static final String URL_ADD_POST = "http://platformx.co.in/tbAndroidApp/addPost.php";
    public static final String URL_CHECK_USER ="http://platformx.co.in/tbAndroidApp/checkUser.php";
    public static final String URL_VIEW_USER_BYID="http://platformx.co.in/tbAndroidApp/ViewUserById.php?u_id=";
    public static final String URL_VIEW_USERS = "http://platformx.co.in/tbAndroidApp/viewUsers.php";
    public static final String URL_VIEW_POSTS = "http://platformx.co.in/tbAndroidApp/viewPost.php";
    public static final String URL_APPROVAL_USER = "http://platformx.co.in/tbAndroidApp/viewUserApprovals.php";
    public static final String URL_APPROVE = "http://platformx.co.in/tbAndroidApp/ApproveUser.php";
    public static final String URL_APPROVAL_POST = "http://platformx.co.in/tbAndroidApp/approvalPost.php?u_id=";*/

    //Keys that will be used to send the request to php scripts

    public static final String KEY_USER_FIRSTNAME = "firstname";
    public static final String KEY_USER_LASTNAME = "lastname";
    public static final String KEY_USERNAME ="username";
    public static final String KEY_PASSWORD ="password";
    public static final String KEY_DOB ="birthdate";
    public static final String KEY_GENDER="gender";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CITY = "city";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_USER_STATUS = "status";

    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "techbodhi";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "id";

    public static final String KEY_POST_ID ="p_id";
    public static final String KEY_HEADING = "Heading";
    public static final String KEY_CONTENT = "Content";

    //JSON Tags
    public static final String TAG_USER_FIRSTNAME = "firstname";
    public static final String TAG_USER_LASTNAME = "lastname";
    public static final String TAG_USERNAME ="username";
    public static final String TAG_PASSWORD ="password";
    public static final String TAG_DOB ="birthdate";
    public static final String TAG_GENDER="gender";
    public static final String TAG_ADDRESS = "address";
    public static final String TAG_CITY = "city";
    public static final String TAG_MOBILE = "mobile";
    public static final String TAG_USER_STATUS = "status";

    public static final String TAG_POST_ID ="p_id";
    public static final String TAG_HEADING = "Heading";
    public static final String TAG_CONTENT = "Content";

    public static final String JSON_ARRAY="result";
    //User id to pass with intent
    public static final String USER_ID = "u_id";
    public static final String TAG_USER_ID="2";


}
