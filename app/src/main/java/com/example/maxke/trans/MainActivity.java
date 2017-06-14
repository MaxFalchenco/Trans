package com.example.maxke.trans;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ClipboardListener clipboardListener = new ClipboardListener();
    //clipboardListener.ClipboardListener;

    public void registerBroadcastReceiver(View view) {
        this.registerReceiver(clipboardListener, new IntentFilter(
                "android.intent.action.TIME_TICK"));
        Toast.makeText(getApplicationContext(), "Приёмник включен",
                Toast.LENGTH_SHORT).show();
    }

    // Отменяем регистрацию
    public void unregisterBroadcastReceiver(View view) {
        this.unregisterReceiver(clipboardListener);

        Toast.makeText(getApplicationContext(), "Приёмник выключён", Toast.LENGTH_SHORT)
                .show();
    }


    String forNotif = "";

    public String Tra ()
    {
        String leng = item + "-" + item2;
        String translatedStr = "";

        Map<String, String> mapJson = new HashMap<String, String>();
        mapJson.put("key",KEY);
        mapJson.put("text",editText.getText().toString());
        mapJson.put("lang",leng);

        Call<Object> call = intf.translate(mapJson);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()){

                    String myStr = response.body().toString();


                    String [] arrForResponse  = new String[2];

                    arrForResponse = myStr.split(",");



                    myStr = arrForResponse[2];

                    String translatedStr = "";
                    boolean forBreck = false;

                    for(int i = 0; i < myStr.length() - 2; i++)
                    {
                        if(myStr.charAt(i) == '[')
                        {
                            forBreck = true;
                            i+=1;
                        }

                        if (forBreck == true)
                        {
                            translatedStr = translatedStr + myStr.charAt(i);
                        }
                    }

                    System.out.println("Translated   " + translatedStr);
                    Text.setText(translatedStr);




                } else {

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

        return translatedStr;

    }

    protected void Noti (String per)
    {
        final int NOTIFY_ID = 101;


        Context context = getApplicationContext();

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.books)
                // большая картинка


                .setContentTitle("")
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText(per); // Текст уведомления

        // Notification notification = builder.getNotification(); // до API 16
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }

    private Gson gson = new GsonBuilder().create();

    String item = "ru";
    String item2 = "en";

    private final  String URL = "https://translate.yandex.net";
    private final  String KEY = "trnsl.1.1.20170417T085320Z.4283b8bec152696c.ae0acc3948b28e306a6da19e4bf7579acde070bc";


    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();

    private Link intf = retrofit.create(Link.class);

    TextView Text;
    EditText editText;
    Button button;
    Spinner spinner;
    Spinner spinner1;

    String[] data = {"ru", "en", "de", "it", "bg"};
    String[] data1 = {"en", "ru", "de", "it", "bg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      /*  registerReceiver(ClipboardListener, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));      */            //ACTION_BATTERY_CHANGED

        /*ClipboardManager clipBoard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        clipBoard.addPrimaryClipChangedListener( new ClipboardListener() );*/


        Text = (TextView) findViewById(R.id.Text);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner1 = (Spinner)findViewById(R.id.spinner1);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_selector_main, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.item_selector_main, data1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(0);

       // spinner.setPrompt("Title");
        // выделяем элемент
        //spinner.setSelection(2);
        // устанавливаем обработчик нажатия

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelected(true);
                // Получаем выбранный объект
                 item = (String)parent.getItemAtPosition(position);
                //spinner.set
                System.out.println("moi item   " + item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);


        AdapterView.OnItemSelectedListener itemSelectedListener1 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner1.setSelected(true);
                // Получаем выбранный объект
                 item2 = (String)parent.getItemAtPosition(position);
                //spinner.set
                System.out.println("moi item2   " + item2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner1.setOnItemSelectedListener(itemSelectedListener1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forNotif = Tra();

            }});}





   /* private BroadcastReceiver ClipboardListener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {

          //  TextView textView3 = (TextView) findViewById(R.id.textView);

           ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = clipboard.getPrimaryClip();
            if (clipData != null )
            {
               // forNotif = Tra();
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
                Text.setText("dffsdfsd");

                editText.setText(clipData.getItemAt(0).getText());


            }

        }
    };*/


}
