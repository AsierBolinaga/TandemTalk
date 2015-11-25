package abolinaga.tander;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import abolinaga.tander.custom.CustomActivity;
import abolinaga.tander.utils.Const;

public class MainLoginActivity extends CustomActivity
{
    private EditText editTextLogingUserName;
    private EditText editTextLogingPassword;

    private Button buttonGoToRegister;
    private Button buttonLogin;
    
    @Override
    protected void onCreate(Bundle _bndSavedInstanceState) 
    {
        super.onCreate(_bndSavedInstanceState);
        setContentView(R.layout.login);
        
        editTextLogingUserName = (EditText) findViewById(R.id.username);
        editTextLogingPassword = (EditText) findViewById(R.id.password);
        
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonGoToRegister = (Button) findViewById(R.id.buttonGoToRegister);
        
        //Setting listeners to button
        buttonLogin.setOnClickListener(this);
        buttonGoToRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View _v) 
    {
        if(_v == buttonGoToRegister)
        {                    
			startActivity(new Intent(MainLoginActivity.this, RegisterActivity.class));
        }
        else if(_v == buttonLogin)
        {
        	loginUser();
        }
        else
        {
        	/* Do nothing */
        }
    }
    
    private void loginUser()
    {
    	final String strUsername = editTextLogingUserName.getText().toString().trim();       
    	String strPassword = editTextLogingPassword.getText().toString().trim();
    	
    	 class LoginUserClass extends AsyncTask<String,Void,String>
    	 {
    		ProgressDialog pdLoading;
			
    		@Override            
    		protected void onPreExecute() 
    		{                
    			super.onPreExecute();                
    			pdLoading = ProgressDialog.show(MainLoginActivity.this,"Please Wait",null,true,true);            
    		}
    		
    		@Override
    		protected void onPostExecute(String _strResult) 
    		{
    			super.onPostExecute(_strResult);
    			String strId = "";
    			
    			pdLoading.dismiss();
    			if(_strResult.contains("id"))
    			{      
    				Toast.makeText(MainLoginActivity.this,"successfully logged",Toast.LENGTH_LONG).show();
    				
    				JSONObject jsonObject = null;
    				
    				try
    				{
    					jsonObject = new JSONObject(_strResult);
    					JSONArray jsonResult = jsonObject.getJSONArray(Const.TAG_JSON_ARRAY);
    					
    					for(int iLoop = 0; iLoop<jsonResult.length(); iLoop++)
    					{
    		                JSONObject jo = jsonResult.getJSONObject(iLoop);
    		                strId = jo.getString(Const.TAG_ID);
    		            }
    				}
    		        catch(JSONException e)
    				{
    					 e.printStackTrace();
    				}
    				
    				Intent intent = new Intent(MainLoginActivity.this, OptionsActivity.class);    
    				
    				intent.putExtra("USER_USERNAME", strUsername);
    				intent.putExtra("USER_ID", strId);
    				startActivity(intent); 
    				finish();
    			}
    			else
    			{                    
    				Toast.makeText(MainLoginActivity.this,_strResult,Toast.LENGTH_LONG).show();
    			}
    		}
			
			protected String doInBackground(String... _strParams) 
			{
				HashMap<String,String> hmData = new HashMap<String,String>();
				hmData.put("username",_strParams[0]);
				hmData.put("password",_strParams[1]);
				
				RequestHandler rh = new RequestHandler();
				
				String strResult = rh.sendPostRequest(Const.URL_LOGGIN_USER, hmData);
				
				return strResult;
			}
    		 
    	 }
    	 
    	 LoginUserClass luc = new LoginUserClass();
    	 luc.execute(strUsername,strPassword);
    }
}
