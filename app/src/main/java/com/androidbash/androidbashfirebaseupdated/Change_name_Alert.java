package com.androidbash.androidbashfirebaseupdated;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.R;

public class Change_name_Alert extends Activity {
    final Context context = this;
    private Button button;
    private TextView final_text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.v("Activity:","Start Change_name_Alert");

        //Инициализируем элементы:
        button = (Button) findViewById(R.id.profile_edit_name_btn);
        final_text = (TextView) findViewById(R.id.profile_name);

        //Добавляем слушателя нажатий по кнопке Button:

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.change_name_alert_dlgs, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Вводим текст и отображаем в строке ввода на основном экране:
                                        final_text.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                //Создаем AlertDialog:
                AlertDialog alertDialog = mDialogBuilder.create();

                //и отображаем его:
                alertDialog.show();

            }
        });
    }


}
