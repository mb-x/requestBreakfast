package com.devgrafix.requestbreakfast.personList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.devgrafix.requestbreakfast.R;
import com.devgrafix.requestbreakfast.model.Person;

import java.util.List;

/**
 * Created by PC-MA13 on 10/09/2016.
 */
public class PersonAdapter extends ArrayAdapter<Person> {
    //tweets est la liste des models à afficher
    public PersonAdapter(Context context, List<Person> persons) {
        super(context, 0, persons);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_person_item, parent, false);
        }
        PersonViewHolder personViewHolder = (PersonViewHolder) convertView.getTag();
        if(personViewHolder == null){
            personViewHolder = new PersonViewHolder();
            personViewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            personViewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            personViewHolder.personId = (EditText) convertView.findViewById(R.id.person_id);
            personViewHolder.description= (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(personViewHolder);
        }
        Person person = getItem(position);
        personViewHolder.personId.setText(String.valueOf(person.getId()));
        personViewHolder.pseudo.setText(person.getPseudo());
        personViewHolder.description.setText(person.getDescription());
       // personViewHolder.avatar.setColorFilter(person.getAvatar());
        //viewHolder.avatar.setImageDrawable(new ColorDrawable(tweet.getColor()));

        return convertView;
    }
}
