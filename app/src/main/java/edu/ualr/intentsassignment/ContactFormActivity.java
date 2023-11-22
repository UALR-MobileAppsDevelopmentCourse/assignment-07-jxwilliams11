package edu.ualr.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {

    // TODO 01. Create a new layout file to define the GUI elements of the ContactFormActivity.
    // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity

    private EditText
        firstName,
        lastName,
        phoneNumber,
        emailAddress,
        address,
        website;

    public static final String EXTRA_CONTACT = "ContactData";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_form_layout);
        MaterialButton saveButton = findViewById(R.id.saveButton);

        firstName = findViewById(R.id.firstNameField);
        lastName = findViewById(R.id.lastNameField);
        phoneNumber = findViewById(R.id.phoneField);
        emailAddress = findViewById(R.id.emailField);
        address = findViewById(R.id.addressField);
        website = findViewById(R.id.websiteField);
    }

    public void onButtonClick(View view){
        Intent intent = new Intent(this, ContactInfoActivity.class);
        Contact c = new Contact();

        c.setFirstName(firstName.getText().toString());
        c.setLastName(lastName.getText().toString());
        c.setPhoneNumber(phoneNumber.getText().toString());
        c.setEmailAddress(emailAddress.getText().toString());
        c.setAddress(address.getText().toString());
        c.setWebsite(website.getText().toString());

        intent.putExtra(EXTRA_CONTACT, c);
        startActivity(intent);
    }
}
