package com.example.gamincoder.ip;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    public Fragment1() {
        // Required empty public constructor
    }
    List<String> Emails=new ArrayList<>();
    List<String> Passwords=new ArrayList<>();

    EditText instmail;
    EditText pass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Emails.add("abc@gmail.com");
        Emails.add("xyz@gmail.com");
        Emails.add("iiitkota@gmail.com");
        Passwords.add("123456");
        Passwords.add("123456");
        Passwords.add("123456");
        View view=inflater.inflate(R.layout.fragment_fragment1, container, false);
        String spinnerArray[]={"Hostel Name","Hostel 1(Parijat)","Hostel 2(Chaitanya)","Hostel 3(Satpura)","Hostel 4(Lohit)","Hostel 5(Brihaspathi)","Hostel 6(Kabir)","Hostel 7(Drona)","Hostel 8(Varun)","Aurobindo Hostel"};

        Spinner spinner=view.findViewById(R.id.spinner2);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
        instmail=view.findViewById(R.id.editText);
        pass=view.findViewById(R.id.editText2);
        Button button=view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail2=instmail.getText().toString();
                String pass2=pass.getText().toString();
                if(Emails.contains(mail2) && Passwords.contains(pass2)) {
                    int x = Emails.indexOf(mail2);
                    int y = Passwords.indexOf(pass2);
                    if (x == y) {
                        Toast.makeText(getActivity(), "LogIn Successfull!!!", Toast.LENGTH_LONG).show();
                        Fragment2 details = new Fragment2();
                        getFragmentManager().beginTransaction().replace(R.id.content1, details).commit();
                    } else {
                        Toast.makeText(getActivity(), "Invalid Credentials Specified!!!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"Email or Password Not Found!!!",Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;

    }


}
