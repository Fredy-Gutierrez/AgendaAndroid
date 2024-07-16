package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class NewEvent extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //Code to add list items to dropbox
        Spinner eventTypeSpinner = findViewById(R.id.event_type_txt);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.event_types_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        eventTypeSpinner.setAdapter(adapter);

        //Code to add datepicker
        EditText eventstart = findViewById(R.id.event_start_date_txt);
        eventstart.setOnClickListener(this);

        //Code to add datepicker
        EditText eventend = findViewById(R.id.event_end_date_txt);
        eventend.setOnClickListener(this);

        //Code for save button
        Button saveBtn = findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(this);

        //Code for cancel button
        Button cancelBtn = findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.event_start_date_txt:
                showDatePickerDialog((EditText)view);
                break;
            case R.id.event_end_date_txt:
                showDatePickerDialog((EditText)view);
                break;
            case R.id.btn_save:
                saveBtnClic(view);
                break;
            case R.id.btn_cancel:
                cancelBtnClic(view);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    private void saveBtnClic(View view){
        EditText eventName = findViewById(R.id.event_name_txt);
        Spinner eventType = findViewById(R.id.event_type_txt);
        EditText eventStart = findViewById(R.id.event_start_date_txt);
        EditText eventEnd = findViewById(R.id.event_end_date_txt);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch duration = findViewById(R.id.event_duration);
        EditText eventDescription = findViewById(R.id.event_description_txt);

        String eventNameValue = eventName.getText().toString();
        String eventTypeValue = eventType.getSelectedItem().toString();
        String eventStartValue = eventStart.getText().toString();
        String eventEndValue = eventEnd.getText().toString();
        boolean eventDuration = duration.isChecked();
        String eventDescriptionValue = eventDescription.getText().toString();

        if(eventNameValue.trim().equals("")) {
            Toast.makeText(this, "El nombre del evento es requerido", Toast.LENGTH_LONG).show();
            return;
        }
        if(eventTypeValue.trim().equals("")) {
            Toast.makeText(this, "El tipo de evento es requerido", Toast.LENGTH_LONG).show();
            return;
        }
        if(eventStartValue.trim().equals("")) {
            Toast.makeText(this, "La fecha de inicio del evento es requerida", Toast.LENGTH_LONG).show();
            return;
        }
        if(!eventDuration){
            if(eventEndValue.trim().equals("")) {
                Toast.makeText(this, "La fecha de fin del evento es requerida", Toast.LENGTH_LONG).show();
                return;
            }
        }

        Intent intent = new Intent(view.getContext(), Sumary.class);
        view.getContext().startActivity(intent);
    }

    private void cancelBtnClic(View view){
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }

    private void showDatePickerDialog(EditText event) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                event.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}