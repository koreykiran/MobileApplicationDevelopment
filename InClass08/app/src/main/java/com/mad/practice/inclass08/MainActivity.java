package com.mad.practice.inclass08;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentInterface, DisplayFragment.OnFragmentInteractionListener,EditFragment.OnFragmentInteractionListenerEdit{
    static String STUDENT_KEY ="Student";
    //private RadioGroup rgDepartment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");

        getFragmentManager().beginTransaction()
                .add(R.id.container,new MainFragment(),"MainFragment")
                .commit();

    }


    @Override
    public void onBackPressed() {

        Log.d("demo", "MainActivity : onBackPressed: before pop"+getFragmentManager().getBackStackEntryCount());

        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }

        Log.d("demo", "MainActivity : onBackPressed: after pop"+getFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onFill(Student s) {

        DisplayFragment fragment = DisplayFragment.newInstance("", "",s);


        getFragmentManager().beginTransaction()
                .replace(R.id.container,fragment,"DisplayFragment")
                .commit();
    }

    @Override
    public void onFragmentInteraction(Student s1, int i) {
        EditFragment fragment = EditFragment.newInstance("", i,s1);


        getFragmentManager().beginTransaction()
                .replace(R.id.container,fragment,"EditFragment")
                .commit();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    @Override
    public void onFragmentInteractionEdit(Student s2) {
        DisplayFragment fragment = DisplayFragment.newInstance("", "",s2);

       /* DisplayFragment frg = null;
        frg = (DisplayFragment) getFragmentManager().findFragmentByTag("DisplayFragment");
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.commit();*/

        getFragmentManager().beginTransaction()
                .replace(R.id.container,fragment,"DisplayFragment")
                .commit();
    }
}
