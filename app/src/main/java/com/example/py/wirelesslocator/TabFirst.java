package com.example.py.wirelesslocator;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


public class TabFirst extends Fragment {


    Button button, button1, button2;
    EditText editText1, editText2, editText3, url;
    String name, value;
    int count = 0;
    JSONObject obj = new JSONObject();
    JSONArray array = new JSONArray();
    String x, y, c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_first,
                container, false);
        Button button = view.findViewById(R.id.button);
        editText1 = view.findViewById(R.id.editText1);
        editText2 = view.findViewById(R.id.editText2);
        editText3 = view.findViewById(R.id.editText3);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                x = editText1.getText().toString();
                y = editText2.getText().toString();
                c = editText3.getText().toString();

                if (x.equals("") && y.equals("")==true) {
                    obj = null;
                    count = 0;
                    Toast.makeText(getContext(), "Enter the Co-ordinates first", Toast.LENGTH_LONG).show();
                }

                else {

                    Intent intent = new Intent(getContext(), SecondActivity.class);
                    intent.putExtra("x", x);
                    intent.putExtra("y", y);
                    intent.putExtra("c", c);
                    Toast.makeText(getActivity(), "else executed", Toast.LENGTH_LONG).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent,
                                ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                    }
                    getActivity().finish();
                }
            }
        });
        return view;
    }


    public interface OnFragmentInteractionListener {
    }
}
