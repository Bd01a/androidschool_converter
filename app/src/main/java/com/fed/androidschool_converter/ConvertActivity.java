package com.fed.androidschool_converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertActivity extends AppCompatActivity {

    private EditText mValue1;
    private EditText mValue2;
    private int mPositionSpinner1 = 0;
    private int mPositionSpinner2 = 0;

    Conversion mConversion;

    TextWatcher mTextWatcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            convert(mPositionSpinner1, mPositionSpinner2, mValue1, mValue2, mTextWatcher2);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher mTextWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            convert(mPositionSpinner2, mPositionSpinner1, mValue2, mValue1, mTextWatcher1);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_layout);
        mValue1 = findViewById(R.id.edit_text_value1);
        mValue2 = findViewById(R.id.edit_text_value2);


        Intent intent = getIntent();
        mConversion = (Conversion) intent.getSerializableExtra(getResources().getString(R.string.CONVERSATION_TAG));



        Spinner spinner1 = spinnerInitial(R.id.firsr_spinner);
        Spinner spinner2 = spinnerInitial(R.id.second_spinner);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPositionSpinner1=position;
                convert(mPositionSpinner1, mPositionSpinner2, mValue1, mValue2, mTextWatcher2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPositionSpinner2=position;
                convert(mPositionSpinner2, mPositionSpinner1, mValue2, mValue1, mTextWatcher1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mValue1.addTextChangedListener(mTextWatcher1);
        mValue2.addTextChangedListener(mTextWatcher2);

    }

    private void convert(int positionSpinner, int positionOtherSpinner,
                         EditText editableEditText, EditText resultEditText, TextWatcher resultTextWatcher) {
        resultEditText.removeTextChangedListener(resultTextWatcher);
        try {
                resultEditText.setText(Double.toString(Double.parseDouble(editableEditText.getText().toString())
                        * mConversion.units.get(positionSpinner).conversionToBase
                        * mConversion.units.get(positionOtherSpinner).conversionFromBase));
//            }
        }
        catch (NumberFormatException exception){

        }
        resultEditText.addTextChangedListener(resultTextWatcher);
    }


    private Spinner spinnerInitial(int spinner_id) {
        Spinner spinner = findViewById(spinner_id);
        List<String> unitNames = new ArrayList<>();
        for(Unit unit : mConversion.units){
            unitNames.add(getResources().getString(unit.labelResource));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, unitNames);
        spinner.setAdapter(adapter);
        return spinner;
    }
}
