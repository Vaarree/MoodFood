package com.example.moodfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moodfood.data.Data;
import com.example.moodfood.data.FoodSearch;

public class activity_moodForm extends AppCompatActivity {

    String location = "", foodType = "", vibe = "";
    int selectedLoc = 0, selectedFood = 0, selectedVibe = 0;

    //taro pembentukan layout di method, terus call methodnya di oncreate. Oncreate itu kan cuman kalo activity mulai
    //kalo layoutnya ntar akan diganti sesuai dengan method yang di call.
    public void setFormLocation() {

        setContentView(R.layout.location_form);
        //inisialisasi radio group dan next button
        Button nextBtn = findViewById(R.id.btn_location_nextBtn);
        final RadioGroup locationGroup = findViewById(R.id.rg_location);
        //selected utk menyimpan selection kalo misalnya user kembali dari form selanjutnya.

        if (selectedLoc != 0) {
            RadioButton selectedBtn = findViewById(selectedLoc);
            selectedBtn.setChecked(true);
        }


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialisasi button yang terpilih berdasarkan button selected di radiogroup
                int selected_btn = locationGroup.getCheckedRadioButtonId();
                selectedLoc = selected_btn;
                RadioButton selectedBtn = findViewById(selected_btn);
                boolean nullP;
//boolean untuk validasi input radio button jadi gaada yang ga kepilih
                try {
                    location = selectedBtn.getText().toString();

                    nullP = false;
                } catch (NullPointerException e) {
                    Toast.makeText(activity_moodForm.this, "Please make a selection before continuing.", Toast.LENGTH_SHORT).show();
                    nullP = true;
                }

                if (!nullP) setFormWhatToEat();

            }
        });
    }

    public void setFormWhatToEat() {
        setContentView(R.layout.what_to_eat_form);
        Button nextBtn = findViewById(R.id.btn_whatToEat_next);
        Button backBtn = findViewById(R.id.btn_whatToEat_back);
        final RadioGroup foodGroup = findViewById(R.id.rg_foodType);
        if (selectedFood != 0) {
            RadioButton selectedBtn = findViewById(selectedFood);
            selectedBtn.setChecked(true);
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialisasi button yang terpilih berdasarkan button selected di radiogroup
                int selected_btn = foodGroup.getCheckedRadioButtonId();
                RadioButton selectedBtn = findViewById(selected_btn);
                selectedFood = selected_btn;
                Boolean nullP;
                //boolean untuk validasi input radio button jadi gaada yang ga kepilih
                try {
                    foodType = selectedBtn.getText().toString();

                    nullP = false;
                } catch (NullPointerException e) {
                    Toast.makeText(activity_moodForm.this, "Please make a selection before continuing.", Toast.LENGTH_SHORT).show();
                    nullP = true;
                }

                if (!nullP) setFormVibe();

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFormLocation();
            }
        });
    }

    public void setFormVibe() {
        setContentView(R.layout.restaurant_vibe_form);
        Button finalizeBtn = findViewById(R.id.btn_vibe_final);
        Button backBtn = findViewById(R.id.btn_vibe_back);
        final RadioGroup vibeGroup = findViewById(R.id.rg_vibe);
        if (selectedVibe != 0) {
            RadioButton selectedBtn = findViewById(selectedVibe);
            selectedBtn.setChecked(true);
        }
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFormWhatToEat();
            }
        });
        finalizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialisasi button yang terpilih berdasarkan button selected di radiogroup
                int selected_btn = vibeGroup.getCheckedRadioButtonId();
                RadioButton selectedBtn = findViewById(selected_btn);
                selectedVibe = selected_btn;
                Boolean nullP;
                //boolean untuk validasi input radio button jadi gaada yang ga kepilih
                try {
                    vibe = selectedBtn.getText().toString();

                    nullP = false;
                } catch (NullPointerException E) {
                    Toast.makeText(activity_moodForm.this, "Please make a selection before continuing.", Toast.LENGTH_SHORT).show();
                    nullP = true;
                }
                if (!nullP) setFormSummary();

            }
        });
    }

    public void setFormSummary() {
        setContentView(R.layout.summary_form);
        setSummaryData(location, foodType, vibe);
        Button backBtn = findViewById(R.id.btn_summary_back);
        Button searchBtn = findViewById(R.id.btn_summary_search);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFormVibe();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.retrieveDatabase(activity_moodForm.this, location, vibe, foodType);
                Intent restaurantList =new Intent(activity_moodForm.this, com.example.moodfood.restaurantList.class);
                //Toast.makeText(activity_moodForm.this, "Here are your results", Toast.LENGTH_SHORT).show();
                startActivity(restaurantList);
                finish();



            }
        });

    }

    public void setSummaryData(String location, String foodType, String vibe) {
        TextView location_dt = findViewById(R.id.textView_summary_location);
        TextView foodType_dt = findViewById(R.id.textView_summary_foodType);
        TextView vibe_dt = findViewById(R.id.textView_summary_vibe);
        location_dt.setText(location);
        foodType_dt.setText(foodType);
        vibe_dt.setText(vibe);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFormLocation();

    }


}
