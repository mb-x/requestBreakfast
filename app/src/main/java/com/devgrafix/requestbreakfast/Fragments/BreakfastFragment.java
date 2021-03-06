package com.devgrafix.requestbreakfast.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.util.Util;
import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.Utils.Utils;
import com.devgrafix.requestbreakfast.model.Breakfast;
import com.devgrafix.requestbreakfast.model.Food;
import com.devgrafix.requestbreakfast.model.Person;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BreakfastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BreakfastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BreakfastFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PERSON = "personObject";


    private Person mPerson;
    private List<Food> foodList;
    private List<Breakfast> breakfastList = new ArrayList<Breakfast>();

    private View view;
    private TextView textView;
    private TextView txt_total_price;
    private TextView txt_rest;
    private TableLayout tableFood;
    private Spinner spinnerFood;
    private EditText inputQuantity;
    private EditText inputGivenAmmount;
    private Button addFoodItem;


    private OnFragmentInteractionListener mListener;

    public BreakfastFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPerson = (Person) getArguments().getSerializable(ARG_PERSON);
        }

        //Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_breakfast, container, false);
        initViews(view);
        //Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();
        return view;
    }
    private void initViews(View parentView){
        textView = (TextView) parentView.findViewById(R.id.txt_person_name);
        txt_total_price = (TextView) parentView.findViewById(R.id.txt_total_price);
        txt_rest = (TextView) parentView.findViewById(R.id.txt_rest);
        tableFood = (TableLayout) parentView.findViewById(R.id.table_food);
        spinnerFood = (Spinner) parentView.findViewById(R.id.spinner_foods);
        inputQuantity= (EditText) parentView.findViewById(R.id.inputQuantity);
        inputGivenAmmount= (EditText) parentView.findViewById(R.id.inputGivenAmmount);
        addFoodItem = (Button) parentView.findViewById(R.id.btn_add_food_item);

    }

    private void initEvents(){
        addFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Breakfast breakfast = new Breakfast(mPerson,
                        (Food) spinnerFood.getSelectedItem(),
                        Integer.valueOf(inputQuantity.getText().toString()), Breakfast.convertDateToFormatedString(new Date())
                );
                breakfast.save();
                refreshTable();
            }
        });
        inputGivenAmmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Float total = Float.valueOf(0);
                String strGivenAmount = inputGivenAmmount.getText().toString();
                Float givenAmount;
                try {
                    givenAmount = Float.valueOf(inputGivenAmmount.getText().toString());
                }catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    givenAmount = Float.valueOf(0);
                }
                breakfastList = Breakfast.findByPersonAndDate(mPerson, Breakfast.convertDateToFormatedString(new Date()));
                for(int i=0 ; i < breakfastList.size() ; i++){
                    total += breakfastList.get(i).getFood().getFoodPrice() * breakfastList.get(i).getQuantity();
                }
                txt_total_price.setText(String.valueOf(total));
                txt_rest.setText(String.valueOf(Utils.round(givenAmount-total, 2)));
            }
        });
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Toast.makeText(getContext(), "onActivityCreated", Toast.LENGTH_SHORT).show();
        textView.setText(mPerson.getPseudo());
        foodList = Food.getAll();
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, foodList);
        spinnerFood.setAdapter(spinnerArrayAdapter);
        initEvents();
        fillTableFromDatabase();
    }
    public void fillTableFromDatabase(){
        Float total = Float.valueOf(0);
        Float givenAmount = Float.valueOf(inputGivenAmmount.getText().toString());
        breakfastList = Breakfast.findByPersonAndDate(mPerson, Breakfast.convertDateToFormatedString(new Date()));
        for(int i=0 ; i < breakfastList.size() ; i++){
            total += breakfastList.get(i).getFood().getFoodPrice() * breakfastList.get(i).getQuantity();
            addBreakfastToTable(breakfastList.get(i));
        }
        txt_total_price.setText(String.valueOf(total));
        txt_rest.setText(String.valueOf(givenAmount-total));
    }

    public void refreshTable(){

        int rowCount = tableFood.getChildCount();
        for(int i = 0; i < tableFood.getChildCount(); i++){
            if(i > 0) {
                tableFood.removeViewAt(i);
                i--;
            }
        }
        fillTableFromDatabase();
    }

    public void addBreakfastToTable(Breakfast breakfast){
        TableRow tr = new TableRow(getContext());
       // tr.setId(i);
       // tr.setBackgroundColor((i%2 == 0 )? Color.WHITE: Color.LTGRAY);
        //tr.setBackgroundColor(Color.GRAY);
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TextView label_food = new TextView(getContext());
        //label_food.setId(Integer.valueOf(breakfast.getFood().getId()));
        label_food.setText(breakfast.getFood().getFoodName());
        label_food.setTextColor(Color.BLACK);
        label_food.setPadding(15, 15, 15, 15);
        label_food.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1
        ));
        tr.addView(label_food);
        TextView price_food = new TextView(getContext());
       // price_food.setId(breakfast.getFood().getId());
        price_food.setText(String.valueOf(breakfast.getFood().getFoodPrice()));
        price_food.setTextColor(Color.BLACK);
        price_food.setPadding(15, 15, 15, 15);
        price_food.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1
        ));
        tr.addView(price_food);
        TextView q_food = new TextView(getContext());
        //price_food.setId(breakfast.getFood().getId());
        q_food.setText(String.valueOf(breakfast.getQuantity()));
        q_food.setTextColor(Color.BLACK);
        q_food.setPadding(15, 15, 15, 15);
        q_food.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1
        ));
        tr.addView(q_food);
        tableFood.addView(tr, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param person Parameter 1.
     * @return A new instance of fragment BreakfastFragment.
     */
    // TODO: Rename and change types and number of parameters
  public static BreakfastFragment newInstance(Person person) {
        BreakfastFragment fragment = new BreakfastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PERSON, person);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

 /*

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
