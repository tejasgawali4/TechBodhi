package cj46.tejas.com.techbodhi;

import android.nfc.Tag;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Carl_johnson on 12/5/2016.
 */
public class Config
{
        //Address of our scripts of the CRUD


/*
    public static final String URL_ADD_USER = "http://PLATFORMX.CO.IN/tbAndroidApp/Registration.php";
    public static final String URL_ADD_POST = "http://PLATFORMX.CO.IN/tbAndroidApp/addPost.php";
    public static final String URL_CHECK_USER ="http://PLATFORMX.CO.IN/tbAndroidApp/checkUser.php";
    public static final String URL_VIEW_POST_BYID = "http://PLATFORMX.CO.IN/tbAndroidApp/ViewPostById.php?jid=";
    public static final String URL_VIEW_USERS = "http://PLATFORMX.CO.IN/tbAndroidAppviewUsers.php";
    public static final String URL_VIEW_POSTS = "http://PLATFORMX.CO.IN/tbAndroidApp/viewPost.php";
    public static final String URL_APPROVAL_USER = "http://PLATFORMX.CO.IN/tbAndroidApp/viewUserApprovals.php";
    public static final String URL_APPROVE_user = "http://PLATFORMX.CO.IN/tbAndroidApp/ApproveUser.php";
    public static final String URL_APPROVAL_POST = "http://PLATFORMX.CO.IN/tbAndroidApp/viewPostApprovals.php";
    public static final String URL_APPROVE_post = "http://PLATFORMX.CO.IN/tbAndroidApp/ApprovePost.php";
    public static final String URL_UPDATE_JOBPOST = "http://PLATFORMX.CO.IN/tbAndroidApp/updateJobPost.php";
    public static final String URL_USER_PANEL_VIEWPOST = "http://PLATFORMX.CO.IN/tbAndroidApp/UserPanelViewJobPost.php";
    public static final String URL_VIEWPOSTS_BY_ID = "http://PLATFORMX.CO.IN/tbAndroidApp/UserPanelViewJobPostBYId.php?pid=";
    public static final String URL_APPLY_FOR_JOB = "http://PLATFORMX.CO.IN/tbAndroidApp/UserPanelApplyJob.php?pid=";

*/

    //http://platformx.co.in/tbAndroidApp/viewUsers.php

    public static final String URL_ADD_USER="http://192.168.1.4/TBS_PORTAL/API/Registration.php";
    public static final String URL_ADD_POST = "http://192.168.1.4/TBS_PORTAL/API/addPost.php";
    public static final String URL_CHECK_USER ="http://192.168.1.4/TBS_PORTAL/API/checkUser.php";
    public static final String URL_VIEW_POST_BYID="http://192.168.1.4/TBS_PORTAL/API/ViewPostById.php?jid=";
    public static final String URL_VIEW_USERS = "http://192.168.1.4/TBS_PORTAL/API/viewUsers.php";
    public static final String URL_VIEW_POSTS = "http://192.168.1.4/TBS_PORTAL/API/viewPost.php";
    public static final String URL_APPROVAL_USER = "http://192.168.1.4/TBS_PORTAL/API/viewUserApprovals.php";
    public static final String URL_APPROVE_user = "http://192.168.1.4/TBS_PORTAL/API/ApproveUser.php";
    public static final String URL_APPROVAL_POST = "http://192.168.1.4/TBS_PORTAL/API/viewPostApprovals.php";
    public static final String URL_APPROVE_post = "http://192.168.1.4/TBS_PORTAL/API/ApprovePost.php";
    public static final String URL_UPDATE_JOBPOST= "http://192.168.1.4/TBS_PORTAL/API/updateJobPost.php";
    public static final String URL_USER_PANEL_VIEWPOST= "http://192.168.1.4/TBS_PORTAL/API/UserPanelViewJobPost.php";
    public static final String URL_VIEWPOSTS_BY_ID="http://192.168.1.4/TBS_PORTAL/API/UserPanelViewJobPostBYId.php?pid=";
    public static final String URL_APPLY_FOR_JOB="http://192.168.1.4/TBS_PORTAL/API/UserPanelApplyJob.php?pid=";


    //Keys that will be used to send the request to php scripts

    public static final String KEY_USER_FIRSTNAME = "u_fisrtname";
    public static final String KEY_USER_LASTNAME = "u_lastname";
    public static final String KEY_USERNAME ="u_username";
    public static final String KEY_PASSWORD ="u_password";
    public static final String KEY_EMAIL ="u_email";
    public static final String KEY_DOB ="u_dob";
    public static final String KEY_GENDER="u_gender";
    public static final String KEY_ADDRESS = "u_address";
    public static final String KEY_MOBILE = "u_contact";
    public static final String KEY_SSC_PER = "u_ssc_per";
    public static final String KEY_SSC_PASSING_YEAR = "u_ssc_passingyear";
    public static final String KEY_HSC_PER = "u_hsc_per";
    public static final String KEY_HSC_PASSING_YEAR = "u_hsc_passingyear";
    public static final String KEY_HSC_STREAM = "u_hsc_stream";
    public static final String KEY_DIPLOMA_PER = "u_diploma_per";
    public static final String KEY_DIPLOMA_STREAM = "u_diploma_stream";
    public static final String KEY_DIPLOMA_PASSING_YEAR = "u_diploma_passingyear";
    public static final String KEY_GRADUATION_PER = "u_graduation_per";
    public static final String KEY_GRADUATION_STREAM = "u_graduation_stream";
    public static final String KEY_GRADUATION_PASSING_YEAR = "u_graduation_passingyear";
    public static final String KEY_POST_GRAD_PER = "u_postGrad_per";
    public static final String KEY_POST_GRAD_STREAM = "u_postGrad_stream";
    public static final String KEY_POST_GRAD_PASSING_YEAR = "u_post_passingyear";




    //job post veriable keys

    public static final String KEY_COMPANYNAME = "p_companyName";
    public static final String KEY_NOTE = "p_note";
    public static final String KEY_DEADLINE ="p_deadline";
    public static final String KEY_COMPANYPROFILE ="p_companyProfile";
    public static final String KEY_COMPANYCODE ="p_companyCode";
    public static final String KEY_JOBDISCRIPTION ="p_jobDescription";
    public static final String KEY_OTHERSKILLS="p_otherSkills";
    public static final String KEY_RESPONSIBILITY = "p_responsibility";
    public static final String KEY_SKILLREQUIRES = "p_skillsRequired";
    public static final String KEY_PERCENTAGE = "p_percentageCriteria";
    public static final String KEY_SALARYRANGE = "p_salaryRange";
    public static final String KEY_INTERVIEWPROCESS ="p_interviewProcess";
    public static final String KEY_JOBLOCATION ="p_jobLocation";
    public static final String KEY_TESTLOCATION ="p_testLocation";


    public static final String KEY_USER_STATUS = "status";
    public static final String KEY_POST_STATUS = "status";

    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "techbodhi";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "id";

    public static final String KEY_POST_ID ="p_id";


    public static final String JSON_ARRAY="result";
    //User id to pass with intent
    public static final String USER_ID = "u_id";
    public static final String TAG_USER_ID="2";

}
