package ca.yorku.eecs.mcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcalc_layout);
    }
    public void buttonClicked(View v) {
        EditText principleView = (EditText) findViewById(R.id.principleBox);
        String principle = principleView.getText().toString();
        EditText amortizationView = (EditText) findViewById(R.id.amortizatioBox);
        String amortization = amortizationView.getText().toString();
        EditText interestView = (EditText) findViewById(R.id.interestBox);
        String interest = interestView.getText().toString();

        MortgageModel myModel = new MortgageModel(principle, amortization, interest );
        String myMortgage = myModel.getPayment();

        ((TextView) findViewById(R.id.answer)).setText(myMortgage);

    }
}
