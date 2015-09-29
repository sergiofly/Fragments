package it.to.mello.testing.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

// communicate with main activity
import android.app.Activity;

// manage UI input
import android.widget.Button;
import android.widget.EditText;

// need to inherit from the Fragment class (this is a fragment)
public class TopSectionFragment extends Fragment {

    // define vars
    private static EditText topTextInput;
    private static EditText bottomTextInput;
    private static Activity a;

    // create interface that main activity implements to ensure that createMeme method is implemented
    TopSectionListener activityCommander;

    // I promise I'll create the createMeme method
    public interface TopSectionListener {
        public void createMeme (String top, String bottom);
    }

    // whenever fragment is attached to activity..
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof Activity) {
                a = (Activity) context;
            }
            activityCommander = (TopSectionListener) a;
        } catch (ClassCastException e) {
            throw new ClassCastException(a.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_section_fragment, container, false);

        // reference everything
        topTextInput = (EditText) view.findViewById(R.id.topTextInput);
        bottomTextInput = (EditText) view.findViewById(R.id.bottomTextInput);
        final Button button = (Button) view.findViewById(R.id.button);

        // action
        button.setOnClickListener (
            new View.OnClickListener() {
                public void onClick(View v) {
                    // create new method that passes the v view
                    buttonClicked(v);
                }
            }
        );

        return view;
    }

    // define that method - calls this when button is clicked
    public void buttonClicked(View view) {
        activityCommander.createMeme(topTextInput.getText().toString(), bottomTextInput.getText().toString());
    }
}

