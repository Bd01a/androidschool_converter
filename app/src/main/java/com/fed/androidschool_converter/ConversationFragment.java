package com.fed.androidschool_converter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class ConversationFragment extends Fragment {
    private static final String ARG_PARAM = "param1";

    private Conversion mConversion;

    private EditText mValue1;
    private EditText mValue2;
    private int mPositionSpinner1 = 0;
    private int mPositionSpinner2 = 0;



    static ConversationFragment newInstance(Conversion conversion) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, conversion);

        ConversationFragment fragment = new ConversationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextWatcher mTextWatcher1 = new TextWatcher() {
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
    private TextWatcher mTextWatcher2 = new TextWatcher() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);

        mValue1 = view.findViewById(R.id.edit_text_value1);
        mValue2 = view.findViewById(R.id.edit_text_value2);



        mConversion = (Conversion) getArguments().getSerializable(ARG_PARAM);



        Spinner spinner1 = spinnerInitial(view,R.id.firsr_spinner);
        Spinner spinner2 = spinnerInitial(view,R.id.second_spinner);

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


        return view;
    }


    private void convert(int positionSpinner, int positionOtherSpinner,
                         EditText editableEditText, EditText resultEditText, TextWatcher resultTextWatcher) {
        resultEditText.removeTextChangedListener(resultTextWatcher);
        try {
            resultEditText.setText(
                    String.format(getResources().getString(R.string.value_format),
                            Double.parseDouble(editableEditText.getText().toString())
                    * mConversion.units.get(positionSpinner).conversionToBase
                    * mConversion.units.get(positionOtherSpinner).conversionFromBase));
//            }
        }
        catch (NumberFormatException exception){
            exception.printStackTrace();
        }
        resultEditText.addTextChangedListener(resultTextWatcher);
    }


    private Spinner spinnerInitial(View parentView, int spinner_id) {
        Spinner spinner = parentView.findViewById(spinner_id);
        List<String> unitNames = new ArrayList<>();
        for(Unit unit : mConversion.units){
            unitNames.add(getResources().getString(unit.labelResource));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, unitNames);
        spinner.setAdapter(adapter);
        return spinner;
    }
}
