package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
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

        //Code to add hourpicker
        EditText eventHourStart = findViewById(R.id.event_hour_start_txt);
        eventHourStart.setOnClickListener(this);

        //Code to add hourpicker
        EditText eventHourEnd = findViewById(R.id.event_hour_end_txt);
        eventHourEnd.setOnClickListener(this);

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
            case R.id.event_end_date_txt:
                showDatePickerDialog((EditText)view);
                break;
            case R.id.event_hour_start_txt:
            case R.id.event_hour_end_txt:
                timePicker((EditText)view);
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

    private void cancelBtnClic(View view){
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }

    private void saveBtnClic(View view){
        EventDTO event = getEventData();
        if(evenIsValid(event)) {
            Intent intent = new Intent(view.getContext(), Summary.class);
            intent.putExtra("EVENT_OBJ", event);
            view.getContext().startActivity(intent);
        }
    }

    private EventDTO getEventData(){
        EventDTO event = new EventDTO();
        EditText eventName = findViewById(R.id.event_name_txt);
        Spinner eventType = findViewById(R.id.event_type_txt);
        EditText eventStart = findViewById(R.id.event_start_date_txt);
        EditText eventEnd = findViewById(R.id.event_end_date_txt);
        EditText eventStartHour = findViewById(R.id.event_hour_start_txt);
        EditText eventEndHour = findViewById(R.id.event_hour_end_txt);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch duration = findViewById(R.id.event_duration);
        EditText eventDescription = findViewById(R.id.event_description_txt);

        event.setEventName(eventName.getText().toString());
        event.setEventType(eventType.getSelectedItem().toString());
        event.setEventStart(eventStart.getText().toString());
        event.setEventEnd(eventEnd.getText().toString());
        event.setEventStartHour(eventStartHour.getText().toString());
        event.setEventEndHour(eventEndHour.getText().toString());
        event.setAllDayDuration(duration.isChecked());
        event.setEventDescription(eventDescription.getText().toString());

        return event;
    }

    private boolean evenIsValid(EventDTO event){
        if(event.getEventName().trim().equals("")) {
            Toast.makeText(this, "El nombre del evento es requerido", Toast.LENGTH_LONG).show();
            return false;
        }
        if(event.getEventType().trim().equals("")) {
            Toast.makeText(this, "El tipo de evento es requerido", Toast.LENGTH_LONG).show();
            return false;
        }
        if(event.getEventStart().trim().equals("")) {
            Toast.makeText(this, "La fecha de inicio del evento es requerida", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!event.isAllDayDuration()){
            if(event.getEventStartHour().trim().equals("")) {
                Toast.makeText(this, "La hora de inicio del evento es requerida", Toast.LENGTH_LONG).show();
                return false;
            }
            if(event.getEventEnd().trim().equals("")) {
                Toast.makeText(this, "La fecha de fin del evento es requerida", Toast.LENGTH_LONG).show();
                return false;
            }
            if(event.getEventEndHour().trim().equals("")) {
                Toast.makeText(this, "La hora de fin del evento es requerida", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
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

    private void timePicker(EditText txtTime){
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (TimePicker view, int hourOfDay,int minute) -> {
                        txtTime.setText(hourOfDay + ":" + minute);
                },
                mHour, mMinute, false);
        timePickerDialog.show();
    }

}