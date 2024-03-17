package com.degs.econtacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import java.util.regex.Pattern;

public class My_Utility {
    public void smsClicked(String number, Context context) {
        if (isValidMobile(number)) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO,
                    Uri.parse("sms:" + number));
            smsIntent.putExtra("sms_body", "Hello");
            smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(smsIntent);
        } else {
            Toast.makeText(context, "Invalid E-Mail Please Check Again", Toast.LENGTH_SHORT).show();
        }


    }

    public void composeEmail(String[] addresses, String subject, Context context) {
        for (int i = 0; i < addresses.length; i++) {
            if (isValidEmail(addresses[i])) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Invalid E-Mail Please Check Again", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void callClicked(String number, Context context) {
        if (isValidMobile(number)) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + number));
            callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(callIntent);
        } else {
            Toast.makeText(context, "Invalid Mobile Number Please Check Again", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isValidEmail(CharSequence email) {
        if (isValidEmail(email)) {
        }
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() == 10;
        }
        return false;
    }
    public void openWhatsApp(Context context,String phoneNumber) {
        if (isValidMobile(phoneNumber)){
            try {
                // Specify the number with the country code
                String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + "message";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle exceptions, such as when WhatsApp is not installed on the device
            }
        }
        else{
            Toast.makeText(context, "Invalid Mobile Number Please Check Again", Toast.LENGTH_SHORT).show();
        }

    }
    public void shareText(Context context,String textToShare) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textToShare);
        context.startActivity(Intent.createChooser(intent, "Share via"));
    }
}
