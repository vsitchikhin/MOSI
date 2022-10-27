package com.example.second_lab;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class MainActivity extends Activity implements OnClickListener {

    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConvert = (Button) findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (btnConvert.equals(view)) {
            EditText userNumber = (EditText) findViewById(R.id.userNumber);
            String strUserNumber = userNumber.getText().toString();

            String switchedNumber = switchNumbers(strUserNumber);
            EditText swithedNumberField = (EditText) findViewById(R.id.switchedNumber);
            swithedNumberField.setText(switchedNumber);
        }
    }

    private String checkNumberSystemToSwitch() {
        RadioButton switchToSixteen = (RadioButton) findViewById(R.id.toSixteen);
        RadioButton switchToTen = (RadioButton) findViewById(R.id.toTen);
        RadioButton switchToTwo = (RadioButton) findViewById(R.id.toTwo);

        if (switchToSixteen.isChecked()) {
            return "sixteen";
        } else if (switchToTen.isChecked()) {
            return "ten";
        } else if (switchToTwo.isChecked()) {
            return "bin";
        }
        return "nothing checked";
    }

    private String checkNumberSystemFromSwitch() {
        RadioButton switchFromSixteen = (RadioButton) findViewById(R.id.fromSixteen);
        RadioButton switchFromTen = (RadioButton) findViewById(R.id.fromTen);
        RadioButton switchFromTwo = (RadioButton) findViewById(R.id.fromTwo);

        if (switchFromSixteen.isChecked()) {
            return "sixteen";
        } else if (switchFromTen.isChecked()) {
            return "ten";
        } else if (switchFromTwo.isChecked()) {
            return "bin";
        }
        return "nothing checked";
    }

    private String switchNumbers(String switchedNumber) {
        String systemFrom = checkNumberSystemFromSwitch();
        String systemTo = checkNumberSystemToSwitch();

        switch (systemFrom) {
            case ("bin"): {
                switch(systemTo) {
                    case ("ten"): {
                        return twoToTen(switchedNumber);
                    }
                    case ("sixteen"): {
                        return twoToSixteen(switchedNumber);
                    }
                }
            }
            case ("ten"): {
                switch(systemTo) {
                    case ("bin"): {
                        return tenToTwo(switchedNumber);
                    }
                    case ("sixteen"): {
                        return tenToSixteen(switchedNumber);
                    }
                }
            }
            case ("sixteen"): {
                switch(systemTo) {
                    case ("bin"): {
                        return sixteenToTwo(switchedNumber);
                    }
                    case ("ten"): {
                        return sixteenToTen(switchedNumber);
                    }
                }
            }
        }

        return "no";
    }

    private String sixteenToTen(String number) {
        number = number.toUpperCase();
        String digits = "0123456789ABCDEF";
        Integer val = 0;
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val.toString();
    }

    private String sixteenToTwo(String number) {
        String tenSystemNumber = sixteenToTen(number);
        return tenToTwo(tenSystemNumber);
    }

    private String tenToSixteen(String number) {
        return Integer.toHexString(Integer.parseInt(number));
    }

    private String tenToTwo(String number) {
        StringBuilder targetNumber = new StringBuilder();
        Integer intNumber = Integer.parseInt(number);
        while (intNumber != 0) {
            if (intNumber % 2 == 0) {
                targetNumber.insert(0, "0");
            }
            else {
                targetNumber.insert(0, "1");
            }
            intNumber /= 2;
        }
        return targetNumber.toString();
    }

    private String twoToSixteen(String number) {
        String tenSystemNumber = twoToTen(number);
        return tenToSixteen(tenSystemNumber);
    }

    private String twoToTen(String number) {
        char[] chars = number.toCharArray();
        Integer result = 0;
        int mult = 1;
        for (int i = number.length()-1; i >= 0; i--) {
            if (chars[i]=='1') {
                result += mult;
            }
            mult*=2;
        }
        return result.toString();
    }
}