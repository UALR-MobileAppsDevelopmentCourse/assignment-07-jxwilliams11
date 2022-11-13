package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    // TODO 03. Create a new layout file to define the GUI elements of the ContactInfoActivity.
    // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step

    private TextView
            contactFullNameField,
            contactPhoneField,
            contactMailField,
            contactAddressField,
            contactWebsiteField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info_layout);

        contactFullNameField = findViewById(R.id.contactName);
        contactPhoneField = findViewById(R.id.phoneInfo);
        contactMailField = findViewById(R.id.emailInfo);
        contactAddressField = findViewById(R.id.addressInfo);
        contactWebsiteField = findViewById(R.id.websiteInfo);

        readContactInfo();
    }

    public void readContactInfo(){
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);

        assert c != null;
        contactFullNameField.setText(c.getFullName());
        contactPhoneField.setText(c.getPhoneNumber());
        contactMailField.setText(c.getEmailAddress());
        contactAddressField.setText(c.getAddress());
        contactWebsiteField.setText(c.getWebsite());
    }

    public void onCallButtonClick(View view){
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        assert c != null;
        String phoneNumberUri = "tel:" + c.getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumberUri));
        startActivity(intent);
    }

    public void onTextButtonClick(View view){
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        assert c != null;
        String phoneNumberUri = "smsto:" + c.getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(phoneNumberUri));
        startActivity(intent);
    }

    public void onMailButtonClick(View view){
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        assert c != null;
        String emailReceiverList[] = {c.getEmailAddress()};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        startActivity(intent);
    }

    public void onDirectionsButtonClick(View view){
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        assert c != null;
        String place = c.getAddress();
        String placeUri = String.format("geo:0,0?q=(%s)", place);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
        startActivity(intent);
    }

    public void onWebsiteButtonClick(View view){
        Contact c = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        assert c != null;
        String webUri = "";
        if(c.getWebsite().startsWith("https://")) {
            webUri = c.getWebsite();
        }
        else{
            webUri = "https://" + c.getWebsite();
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUri));
        startActivity(intent);
    }
}
