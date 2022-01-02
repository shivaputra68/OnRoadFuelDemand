package operations;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onroadfueldemand.Register;
import com.parse.ParseUser;

public class Registration extends AppCompatActivity {

    public void registerUser(String username,String password,String email)
    {
        ParseUser user = new ParseUser();
        // Set the user's username and password, which can be obtained by a forms
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        // user.put("contact number", contact.getText().toString());


        user.signUpInBackground(e -> {
            if (e == null) {
                Toast.makeText(getApplicationContext(), "signed in", Toast.LENGTH_SHORT).show();
            } else {
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
