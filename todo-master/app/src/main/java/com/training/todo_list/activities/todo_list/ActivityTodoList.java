package com.training.todo_list.activities.todo_list;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.training.todo_list.R;
import com.training.todo_list.model.models.Adapter;
import com.training.todo_list.model.models.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ActivityTodoList extends ListActivity {

    private ListView listView;
     List<Todo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_list);



        listView = (ListView) findViewById(android.R.id.list) ;
        listTodo();
    }

    public void listTodo(){


        Todo todo = new Todo("todo 1",new Date(1443693600000L), UUID.randomUUID(),false,UUID.randomUUID());
        list.add(todo);
        todo = new Todo("todo 2",new Date(1443693600000L), UUID.randomUUID(),false,UUID.randomUUID());
        list.add(todo);
        Adapter adapter = new Adapter(this,list);
        listView.setAdapter(adapter);

    }


    public void askAddTodo(View pView) {
//        Toast.makeText(this, "Ask add todo", Toast.LENGTH_SHORT).show();
        showAlertDialog();
    }


    public void askSurprise(View pView) {
        AlertDialog.Builder tBuilder = new AlertDialog.Builder(this);
        tBuilder.setTitle(R.string.dialog_title_surprise);
        tBuilder.setMessage(R.string.dialog_message_surprise);
        tBuilder.setPositiveButton(R.string.confirm, null);
        tBuilder.show();
    }


    public void showAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityTodoList.this);
        alertDialog.setTitle("Add todo");

        final EditText input = new EditText(ActivityTodoList.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);


        alertDialog.setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String todotxt = input.getText().toString();

                        if(todotxt.equals("")){
                             Toast.makeText(ActivityTodoList.this, "Enter todo description", Toast.LENGTH_SHORT).show();
                        }else{
                            Todo todo = new Todo(todotxt,new Date(1443693600000L), UUID.randomUUID(),false,UUID.randomUUID());
                            list.add(todo);
                        }

                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }





}
