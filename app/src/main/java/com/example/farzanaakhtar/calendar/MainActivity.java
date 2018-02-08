package com.example.farzanaakhtar.calendar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener
{

    Button prev, next, back, front;
    TextView head;
    int year, month;
    String m;

    Button week[];
    Button days[][];
    LinearLayout table, daysLin;

    int date = Calendar.getInstance().get(Calendar.DATE);

    int origMonth , origYear;
    Button gotoday;

    EditText yr;
    Spinner mns;
    int a;
    String b;
    Button go;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prev = (Button)findViewById(R.id.prev);
        next = (Button)findViewById(R.id.next);
        back = (Button)findViewById(R.id.back);
        front = (Button)findViewById(R.id.front);
        head = (TextView)findViewById(R.id.head);
        table = (LinearLayout)findViewById(R.id.table);
        daysLin = (LinearLayout)findViewById(R.id.daysLin);
        gotoday = (Button)findViewById(R.id.gotoday);
        yr = (EditText)findViewById(R.id.yr);
        mns = (Spinner)findViewById(R.id.mns);
        go = (Button)findViewById(R.id.go);

        prev.setOnClickListener(this);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
        front.setOnClickListener(this);
        gotoday.setOnClickListener(this);
        go.setOnClickListener(this);

        prev.setText("◄"); // press Alt + 17
        char c;
        c = (char)171;
        back.setText(""+c);
        c = (char)187;
        front.setText(""+c);
        next.setText("►"); // press Alt + 16

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);

        origYear = year;
        origMonth = month;

        m = setMonth(month);

        head.setText(m + ", " + year);

        createMonth(month, year);
    }


    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.prev:
                month--;
                if(month == -1)
                {
                    month = 11;
                    year--;
                }
                m = setMonth(month);
                head.setText(m +", " + year);

                setDates(month, year);
                break;
            case R.id.next:
                month++;
                if(month==12)
                {
                    month = 0;
                    year++;
                }
                m = setMonth(month);
                head.setText(m +", " + year);

                setDates(month, year);
                break;
            case R.id.back:
                year--;
                head.setText(m +", "+year);

                setDates(month, year);
                break;
            case R.id.front:
                year++;
                head.setText(m+", "+year);

                setDates(month, year);
                break;

            case R.id.gotoday:
                month = origMonth;
                year = origYear;
                m = setMonth(month);
                head.setText(m+", "+year);
                setDates(month, year);
                Calendar now = Calendar.getInstance();
                Toast t = Toast.makeText(this, "Today is " + now.getTime(), Toast.LENGTH_LONG);
                t.show();
                break;

            case R.id.go:
                a = Integer.valueOf(yr.getText().toString());
                b = mns.getSelectedItem().toString();
                int c;
                switch (b)
                {
                    case "January":
                        c = 0;
                        break;
                    case "February":
                        c = 1;
                        break;
                    case "March":
                        c = 2;
                        break;
                    case "April":
                        c = 3;
                        break;
                    case "May":
                        c = 4;
                        break;
                    case "June":
                        c = 5;
                        break;
                    case "July":
                        c = 6;
                        break;
                    case "August":
                        c = 7;
                        break;
                    case "September":
                        c = 8;
                        break;
                    case "October":
                        c = 9;
                        break;
                    case "November":
                        c = 10;
                        break;
                    case "December":
                        c = 11;
                        break;
                    default:
                        c = -1;
                }
                head.setText(b+", "+a);
                month = c;
                year = a;
                setDates(month, year);
        }
    }

    public static String setMonth(int month)
    {
        String m = "";
        switch (month)
        {
            case 0:
                m = "January";
                break;
            case 1:
                m = "February";
                break;
            case 2:
                m = "March";
                break;
            case 3:
                m = "April";
                break;
            case 4:
                m = "May";
                break;
            case 5:
                m = "June";
                break;
            case 6:
                m = "July";
                break;
            case 7:
                m = "August";
                break;
            case 8:
                m = "September";
                break;
            case 9:
                m = "October";
                break;
            case 10:
                m = "November";
                break;
            case 11:
                m = "December";
                break;

        }
        return m;
    }

    public void createMonth(int month, int year)
    {
        int i, j;
        int dayCount = 1;
        week  = new Button[7];
        days = new Button[6][7];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        // set the week layout
        LinearLayout tr = new LinearLayout(this);
        tr.setOrientation(LinearLayout.HORIZONTAL);
        for(i=0; i<7; i++)
        {
            week[i] = new Button(this);
            week[i].setMinimumWidth(0);
            week[i].setWidth(width / 8);
            week[i].setTextSize(9);
            if(i==0)
                week[i].setTextColor(Color.parseColor("#ff0000"));
            week[i].setTypeface(null, Typeface.BOLD);
            week[i].setText(returnDay(i+1));

            tr.addView(week[i]);
        }
        table.addView(tr);

        // the dates start from here
        for (i = 0; i <6; i++)
        {
            LinearLayout trow = new LinearLayout(this);
            trow.setOrientation(LinearLayout.HORIZONTAL);
            for (j = 0; j <7; j++)
            {
                days[i][j] = new Button(this);
                days[i][j].setMinimumWidth(0);
                days[i][j].setWidth(width / 8);
                days[i][j].setTextSize(15);
                if (j == 0)
                    days[i][j].setTextColor(Color.parseColor("#ff0000"));
                days[i][j].setBackgroundColor(Color.parseColor("#ffffff"));

                trow.addView(days[i][j]);
                days[i][j].setId(dayCount * 1);
                dayCount++;
            }
            daysLin.addView(trow);
        }

        setDates(month, year);


    }

    public void setDates(int month, int year)
    {
        // add days to calendar - not to life or otherwise!
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        int maxNum = numOfDays(month, year); // number of days of given month in given year
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int first = cal.get(Calendar.DAY_OF_WEEK);
        Button b;
        int dayCount;
        int day = 1;

        dayCount = 1;
        while(dayCount<=42)
        {
            b = (Button)findViewById(dayCount * 1);
            b.setText("");
            dayCount++;
        }

        dayCount = 1;
        while(day<=maxNum)
        {
            if(dayCount==first)
            {
                b = (Button)findViewById(dayCount * 1);
                if(day==date&&month==origMonth&&year==origYear)
                {
                    b.setBackgroundColor(Color.parseColor("#66CD00"));
                    //b.setTextColor(Color.parseColor("#ffffff"));
                }
                else
                {
                    b.setBackgroundColor(Color.parseColor("#ffffff"));
                    //b.setTextColor(Color.parseColor("#000000"));
                }
                b.setText(""+day);
                first++;
                day++;
            }
            dayCount++;
        }
    }

    public int numOfDays(int month, int year)
    {
        int temp;
        switch (month)
        {
            case 0 : return 31;
            case 1 :
                temp = (leap(year)==true) ? 29 : 28;
                return temp;
            case 2:
                return 31;
            case 3:
                return 30;
            case 4:
                return 31;
            case 5:
                return 30;
            case 6:
                return 31;
            case 7:
                return 31;
            case 8:
                return 30;
            case 9:
                return 31;
            case 10:
                return 30;
            case 11:
                return 31;
            default:
                return -1;
        }
    }

    public boolean leap(int year)
    {
        if(((year%4==0)&&(year%100!=0))||(year%400==0))
            return true;
        else
            return false;
    }

    public static String returnDay(int i)
    {
        switch (i)
        {
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
            default:
                return "undef";
        }
    }

    public void visitHOLIDAYS(View v)
    {
        Intent i = new Intent(this, HOLIDAYS.class);
        startActivity(i);
    }


}
