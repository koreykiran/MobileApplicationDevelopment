package com.mad.practice.inclass08;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListenerEdit} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;
    private Student student;
    RadioGroup rgDepartment;
    EditText etName;
    EditText etEmail;
    SeekBar seekbar;

    private OnFragmentInteractionListenerEdit mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, int param2,Student s) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putSerializable(ARG_PARAM3,s);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            student = (Student) getArguments().getSerializable(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etMail);
        rgDepartment = view.findViewById(R.id.rgDepartment);
        seekbar = view.findViewById(R.id.seekBar);
        TextView tvDept = view.findViewById(R.id.tvDept);
        TextView tvMoodText = view.findViewById(R.id.tvMoodText);

        etName.setVisibility(View.INVISIBLE);
        etEmail.setVisibility(View.INVISIBLE);
        seekbar.setVisibility(View.INVISIBLE);
        rgDepartment.setVisibility(View.INVISIBLE);
        tvDept.setVisibility(View.INVISIBLE);
        tvMoodText.setVisibility(View.INVISIBLE);


        final Button btnSave = view.findViewById(R.id.btnSave);

        //String which =getIntent().getExtras().getString(DisplayActivity.WHICH);

        switch(mParam2){
            case (1):
                String name =student.name;
                etName.setText(name);
                etName.setVisibility(View.VISIBLE);
                break;
            case (2):
                String email =student.email;
                etEmail.setText(email);
                etEmail.setVisibility(View.VISIBLE);
                break;
            case (3):
                String dept =student.dept;
                switch (dept){
                    case ("SIS"):
                        rgDepartment.check(R.id.rbSIS);
                        break;
                    case ("CS"):
                        rgDepartment.check(R.id.rbCS);
                        break;
                    case ("BIO"):
                        rgDepartment.check(R.id.rbBio);
                        break;
                    case ("OTHERS"):
                        rgDepartment.check(R.id.rbOthers);
                        break;
                }
                tvDept.setVisibility(View.VISIBLE);
                rgDepartment.setVisibility(View.VISIBLE);
                break;

            case (4):
                String progress =student.mood;
                progress=progress.replace(" % Positive","");
                seekbar.setProgress(Integer.parseInt(progress));
                tvMoodText.setVisibility(View.VISIBLE);
                seekbar.setVisibility(View.VISIBLE);
                break;

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            Intent i = new Intent();
            @Override
            public void onClick(View v) {
                boolean finish =false;
                //int which =mParam2;
                switch(mParam2){
                    case (1):
                        String value=etName.getText().toString();
                        if(value!=null && value.length()>0){
                            student.name = etName.getText().toString();
                            finish=true;
                        }else{
                            etName.setError("Required");
                        }
                        break;
                    case (2):
                        String email=etEmail.getText().toString();
                        if(email!=null && email.length()>0){
                            if(MainActivity.isValidEmail(email)) {
                                student.email = etEmail.getText().toString();
                                finish=true;
                            }else{
                                etEmail.setError("Valid Email Required");
                            }
                        }else{
                            etEmail.setError("Required");

                        }
                        break;
                    case (3):
                        String dept=getDepartment(rgDepartment);
                        if(dept!=null && dept.length()>0){
                            student.dept = dept;
                            finish=true;
                        }else{

                        }
                        break;
                    case (4):
                        String mood=seekbar.getProgress()+" % Positive";
                        if(mood!=null && mood.length()>0){
                            student.mood = mood;
                            finish=true;
                        }else{

                        }
                        break;
                }
                if(finish){
                    //TODO:
                    mListener.onFragmentInteractionEdit(student);
                }
            }
        });


        return view ;
    }

 /*   // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteractionEdit(uri);
        }
    }*/

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListenerEdit) {
            mListener = (OnFragmentInteractionListenerEdit) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListenerEdit {
        // TODO: Update argument type and name
        void onFragmentInteractionEdit(Student s);
    }
}
