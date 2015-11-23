package abolinaga.tander;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OptionsActivity extends Activity
{
	private TextView textView;
	
    @Override
    protected void onCreate(Bundle _bdSavedInstanceState)
    {
        super.onCreate(_bdSavedInstanceState);
        setContentView(R.layout.optionhandler);

        textView = (TextView) findViewById(R.id.textViewUserName);

        Bundle reicieveUserName = getIntent().getExtras();

        textView.setText("Welcome User " + reicieveUserName.getString("USER_NAME"));
    }
}
