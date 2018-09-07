package com.training.todo_list.activities.todo_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.training.todo_list.R;
import com.training.todo_list.model.models.Todo;

import java.util.Date;
import java.util.UUID;

public class ActivityTodoCreate extends AppCompatActivity {
    EditText descriptionET;
    CheckBox doneCB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_create);
        descriptionET = (EditText) findViewById(R.id.et_description);
        doneCB = (CheckBox) findViewById(R.id.cb_done);
    }

    public void createTodo(View v){
        if(TextUtils.isEmpty(descriptionET.getText())){
            descriptionET.setError("description is required");
        } else {
            Date creationDate = new Date();
            UUID id = UUID.randomUUID();
            Todo todo = new Todo(
                    descriptionET.getText().toString(),
                    creationDate,
                    null,
                    doneCB.isChecked(), id);
        }
    }

}
