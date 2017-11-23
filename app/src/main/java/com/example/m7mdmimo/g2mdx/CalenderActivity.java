package com.example.m7mdmimo.g2mdx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalenderActivity extends AppCompatActivity {
    @BindView(R.id.date_plan)
    DatePicker datePicker_plan;
    @BindView(R.id.text_view_date)
    TextView textView_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        ButterKnife.bind(this);
        datePicker_plan.setMinDate(System.currentTimeMillis() - 1000);

    }

    @OnClick(R.id.button_show_date)
    public void showDate(View view) {
        int day = datePicker_plan.getDayOfMonth();
        int month = datePicker_plan.getMonth();
        int year = datePicker_plan.getYear();
        textView_date.setText(day + "/" + month +"/"+ year);
    }
}
