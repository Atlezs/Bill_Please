package sg.edu.rp.c346.id2006248.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Button splitBtn;
    Button resetBtn;
    EditText amtInput;
    EditText paxInput;
    EditText discountInput;
    TextView totalBillDisplay;
    TextView splitDisplay;
    ToggleButton svsbtn;
    ToggleButton gstbtn;
    RadioGroup rgPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splitBtn = findViewById(R.id.splitButton);
        resetBtn = findViewById(R.id.resetButton);
        amtInput = findViewById(R.id.editTextAmt);
        paxInput = findViewById(R.id.editTextPax);
        discountInput = findViewById(R.id.editTextDiscount);
        totalBillDisplay = findViewById(R.id.textTotalBill);
        splitDisplay = findViewById(R.id.textSplit);
        svsbtn = findViewById(R.id.svsButton);
        gstbtn = findViewById(R.id.gstButton);
        rgPayment = findViewById(R.id.paymentRadioGroup);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amtInput.setText("");
                paxInput.setText("");
                discountInput.setText("");
                svsbtn.setChecked(false);
                gstbtn.setChecked(false);
                rgPayment.check(R.id.cashButton);
            }
        });

        /* Toast will appear on second click as first click is use for focus
        * I tried but I couldn't make it so that the button can only be clicked when all textEdit is filled*/
        amtInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amtInput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
                    splitBtn.setClickable(false);
                }
                else if (amtInput.getText().toString().length() > 0) {
                    splitBtn.setClickable(true);
                }
            }
        });

        paxInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paxInput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
                    splitBtn.setClickable(false);
                }
                else if (paxInput.getText().toString().length() > 0) {
                    splitBtn.setClickable(true);
                }
            }
        });

        discountInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (discountInput.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "It's empty", Toast.LENGTH_SHORT).show();
                    splitBtn.setClickable(false);
                }
                else if (discountInput.getText().toString().length() > 0) {
                    splitBtn.setClickable(true);
                }
            }
        });


        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amtInputString = amtInput.getText().toString();
                float amtInputFloat = Float.parseFloat(amtInputString);

                String paxInputString = paxInput.getText().toString();
                float paxInputFloat = Float.parseFloat(paxInputString);

                float total = 0;

                if(svsbtn.isChecked()){
                    total = (float) (amtInputFloat * 1.1);
                }
                else if(gstbtn.isChecked()){
                    total = (float) (amtInputFloat * 1.07);
                }
                else if(svsbtn.isChecked() && gstbtn.isChecked()){
                    total = (float) (amtInputFloat * 1.1 * 1.07);
                }
                else{
                    total = amtInputFloat;
                }

                if(TextUtils.isEmpty(discountInput.getText())){
                }
                else{
                    String discountInputString = discountInput.getText().toString();
                    float discountInputFloat = Float.parseFloat(discountInputString);

                    if(discountInputFloat > 0) {
                        total *= (1 - discountInputFloat / 100);
                    }
                }

                String splitamt;

                if(rgPayment.getCheckedRadioButtonId() == R.id.cashButton){
                    splitamt = String.format( "Each Pays: $%.2f in cash",total / paxInputFloat);
                }
                else{
                    splitamt = String.format( "Each Pays: $%.2f via PayNow to 912345678",total / paxInputFloat);
                }

                String totalamt;
                totalamt = String.format( "Total Bill: $%.2f",total);

                totalBillDisplay.setText(totalamt);
                splitDisplay.setText(splitamt);
            }
        });


    }
}