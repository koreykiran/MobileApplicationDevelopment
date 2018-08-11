package com.mad.practice.inclass08;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    RadioGroup rgDepartment;
    FragmentInterface mlistner ;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mlistner = (FragmentInterface) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " Should implement FragmentInterface ");
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText etName = getView().findViewById(R.id.etName);
        final EditText etEmail = getView().findViewById(R.id.etMail);
        rgDepartment = getView().findViewById(R.id.rgDepartment);
        final Button btnSubmit = getView().findViewById(R.id.btnSubmit);
        final SeekBar seekbar = getView().findViewById(R.id.seekBar);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student =  new Student();
                try{
                    Log.d("demo", "MainActivity : onClick: ");
                    if(etName.getText().toString().length()>0){
                        student.name =etName.getText().toString();
                    }else{
                        etName.setError("Required");
                        throw(new Exception("Required Field not Found"));
                    }

                    if(etEmail.getText().toString().length()>0){
                        String email = etEmail.getText().toString();

                        if(isValidEmail(email)) {
                            student.email =email;
                        }else{
                            etEmail.setError("Valid Email Required");
                            throw(new Exception("Valid Email not Found"));
                        }
                    }else{
                        etEmail.setError("Required");
                        throw(new Exception("Required Field not Found"));
                    }

                    if(seekbar.getProgress()>0){
                        student.mood = seekbar.getProgress()+" % Positive";
                    } else if(seekbar.getProgress()==0){
                        student.mood = "0 % Positive";
                    }

                    student.dept=getDepartment(rgDepartment);

                    /*Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                    intent.putExtra(STUDENT_KEY,student);
                    startActivity(intent);*/
                    mlistner.onFill(student);
                }catch(Exception e){
                    Log.d("Catch",e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    String getDepartment(RadioGroup rgDepartment){
        String selectedDept="";
        switch(rgDepartment.getCheckedRadioButtonId()){
            case (R.id.rbSIS):
                Log.d("getDepartment","Checked the SIS Radio Button");
                selectedDept = "SIS";
                break;
            case (R.id.rbCS):
                Log.d("getDepartment","Checked the CS Radio Button");
                selectedDept = "CS";
                break;
            case (R.id.rbBio):
                Log.d("getDepartment","Checked the BIO Radio Button");
                selectedDept = "BIO";
                break;
            case (R.id.rbOthers):
                Log.d("getDepartment","Checked the Others Radio Button");
                selectedDept = "OTHERS";
                break;
            case (-1):
                Log.d("getDepartment","Error in radio button");
                selectedDept = "";
                break;
        }
        return selectedDept;
    }

    interface FragmentInterface{
        public void onFill(Student s);
    }
}
