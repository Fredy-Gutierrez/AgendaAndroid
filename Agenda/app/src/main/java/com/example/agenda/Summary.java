package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        EventDTO event = (EventDTO) getIntent().getSerializableExtra("EVENT_OBJ");

        //fill view
        setDataToView(event);

        Button btn_newevent = findViewById(R.id.btn_new_event);
        btn_newevent.setOnClickListener( (View view) -> {
                Intent intent = new Intent(view.getContext(), NewEvent.class);
                view.getContext().startActivity(intent);
            }
        );

        Button btn_go_home = (Button) findViewById(R.id.btn_go_home);
        btn_go_home.setOnClickListener( (View view) -> {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        );
    }

    private void setDataToView(EventDTO event){
        TextView event_name_txt = (TextView)findViewById(R.id.event_name_txt);
        TextView event_type_txt = (TextView)findViewById(R.id.event_type_txt);
        TextView event_start_txt = (TextView)findViewById(R.id.event_start_txt);
        TextView event_end_txt = (TextView)findViewById(R.id.event_end_txt);
        TextView event_description_txt = (TextView)findViewById(R.id.event_description_txt);

        event_name_txt.setText(event.getEventName());
        event_type_txt.setText(event.getEventType());

        String dateHourStart;
        String dateHourEnd;
        if(event.isAllDayDuration()) {
            dateHourStart = event.getEventStart() + "T00:00";
            dateHourEnd = event.getEventStart() + "T23:59";

            event_start_txt.setText(dateHourStart);
            event_end_txt.setText(dateHourEnd);
        }else {
            dateHourStart = event.getEventStart() + "T" + event.getEventStartHour();
            dateHourEnd = event.getEventEnd() + "T" + event.getEventEndHour();

            event_start_txt.setText(dateHourStart);
            event_end_txt.setText(dateHourEnd);
        }
        event_description_txt.setText(event.getEventDescription());
    }
}