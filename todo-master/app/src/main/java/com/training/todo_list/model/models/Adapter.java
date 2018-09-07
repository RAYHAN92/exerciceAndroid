package com.training.todo_list.model.models;

 import android.app.AlertDialog;
 import android.content.Context;
 import android.content.DialogInterface;
 import android.support.annotation.NonNull;
 import android.support.annotation.Nullable;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ArrayAdapter;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.LinearLayout;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.training.todo_list.R;
 import com.training.todo_list.activities.todo_list.ActivityTodoList;

 import java.util.ArrayList;
 import java.util.List;

public class Adapter extends ArrayAdapter<Todo> {

    private List<Todo> list = new ArrayList<>();

    public Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public Adapter(@NonNull Context context, List<Todo> list) {
        super(context,0,list);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item,parent, false);
        }
        TodoHolder viewHolder = (TodoHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TodoHolder();

            viewHolder.textView = (TextView) convertView.findViewById(R.id.todo);

            convertView.setTag(viewHolder);
        }

         final Todo  todoItem = getItem(position);


        viewHolder.textView.setText(todoItem.description());

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(todoItem);
            }
        });


        return convertView;
    }

    public void showAlertDialog(final Todo todo){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Edit todo");

        final EditText input = new EditText(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        input.setText(todo.description());


        alertDialog.setPositiveButton("Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String todotxt = input.getText().toString();

                        if(todotxt.equals("")){
                            Toast.makeText(getContext(), "Enter todo description", Toast.LENGTH_SHORT).show();
                        }else{
                            todo.setmSDescription(todotxt) ;
                            notifyDataSetChanged();
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



    public class TodoHolder{
        TextView textView;
    }
}
