package fri.uniza.sk.fragmentlecture;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {
    private ButtonClicks buttonClicks;

    public void setButtonClicks(ButtonClicks buttonClicks) {
        this.buttonClicks = buttonClicks;
    }

    public BottomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            buttonClicks = (ButtonClicks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicks.addButtonClick();
            }
        });
        view.findViewById(R.id.delBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicks.deleteButtonClick();
            }
        });
    }

    public interface ButtonClicks {
        void addButtonClick();

        void deleteButtonClick();
    }

}
