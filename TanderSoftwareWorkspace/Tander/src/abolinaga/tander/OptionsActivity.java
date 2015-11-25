package abolinaga.tander;

import java.util.HashMap;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import abolinaga.tander.custom.CustomActivity;
import abolinaga.tander.utils.Const;

public class OptionsActivity extends CustomActivity
{
	private TextView textView;
	
	private Button buttonFindTanderFriend;
    private Button buttonViewTanderFriends;
    
    /** The user. */
    private String strUserName;
    private String strUserId;
	public static ParseUser user;
	
	/**
	 * OnCreate function.
	 */
    @Override
    protected void onCreate(Bundle _bdSavedInstanceState)
    {
        super.onCreate(_bdSavedInstanceState);
        setContentView(R.layout.options);

        textView = (TextView) findViewById(R.id.textViewUserName);

        strUserName = getIntent().getStringExtra("USER_USERNAME");
        strUserId = getIntent().getStringExtra("USER_ID");
		final ParseUser pu = new ParseUser();
		pu.setUsername(strUserName);
		pu.setPassword("");
		pu.signUpInBackground(new SignUpCallback() 
		{
			@Override
			public void done(ParseException arg0) 
			{
				user = pu;
			}
		});
   
        textView.setText("Welcome " + strUserName);
        
        buttonFindTanderFriend = (Button) findViewById(R.id.buttonFindTanderFriend);
        buttonViewTanderFriends = (Button) findViewById(R.id.buttonViewTanderFriends);
        
        GetFriendsList();
        
        //Setting listeners to button
        buttonFindTanderFriend.setOnClickListener(this);
        buttonViewTanderFriends.setOnClickListener(this);
        
        updateUserStatus(true);
    }
    
    /**
	 * onDestroy function.
	 */
    @Override
    protected void onDestroy()
    {
    	super.onDestroy();
    	updateUserStatus(false);
    }
    
    /**
	 * Click listener.
	 */
    @Override
	public void onClick(View _v) 
	{
		if(_v == buttonFindTanderFriend)
        {
        	/* TODO: Friends Finder */
        }
		else if(_v == buttonViewTanderFriends)
		{
			Intent intent = new Intent(OptionsActivity.this, TanderFriendsList.class);                  
			startActivity(intent);
		}
		else
		{
			/* Do Nothing */
		}
	}
    
    /**
	 * Update user status.
	 * 
	 * @param online true if user is online
	 */
	private void updateUserStatus(boolean _bOnline)
	{
		class UpdateUserStateClass extends AsyncTask<String,Void,String>
		{
			@Override
			protected void onPostExecute(String _strResult) 
			{
				if(_strResult.equalsIgnoreCase("success"))
				{
					 /* Do Nothing */
				}
				else
				{
					Toast.makeText(OptionsActivity.this,_strResult,Toast.LENGTH_LONG).show(); 
				}
				super.onPostExecute(_strResult);
			}

			@Override
			protected String doInBackground(String... _strParams) 
			{
				HashMap<String,String> hmData = new HashMap<String,String>();
				hmData.put("id",_strParams[0]);
				hmData.put("online",_strParams[1]);
				
				RequestHandler rh = new RequestHandler();
				
				String strResult = rh.sendPostRequest(Const.URL_UPDATE_USERSTATUS, hmData);
				
				return strResult;
			}
		}
		
		UpdateUserStateClass uusc = new UpdateUserStateClass();
		
		if(true == _bOnline)
		{
			uusc.execute(strUserId, "1");
		}
		else
		{
			uusc.execute(strUserId, "0");
		}
	}
    
	
    private void GetFriendsList()
    {
    	
    }
}
