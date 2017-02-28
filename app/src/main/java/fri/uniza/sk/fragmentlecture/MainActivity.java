package fri.uniza.sk.fragmentlecture;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import fri.uniza.sk.fragmentlecture.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private int i = 0;

    private static WeakReference<MainActivity> wrActivity = null;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putChar("test", 's');
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Main activity");
        wrActivity =  new WeakReference<MainActivity>(this);

        ItemFragment itemFragment = ItemFragment.newInstance(1);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentTop, itemFragment);

        if (savedInstanceState != null) {
            //getSupportFragmentManager().popBackStack();
            Log.d("Test", "popbackstack");
        } else {

            BottomFragment bottomFragment = BottomFragment.newInstance(i++, new ButtonClicksImpl(i));
            fragmentTransaction.replace(R.id.fragmentBottom, bottomFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }


    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    private class ButtonClicksImpl implements BottomFragment.ButtonClicks {

        int i;
        @Override
        public void addButtonClick() {
            //Toast.makeText(MainActivity.this,"Add clicked",Toast.LENGTH_SHORT).show();
            final MainActivity activity = wrActivity.get();
            BottomFragment bottomFragment = BottomFragment.newInstance(i++, new ButtonClicksImpl(i));
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentBottom, bottomFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

        @Override
        public void deleteButtonClick() {
            Toast.makeText(getApplicationContext(), "Del clicked", Toast.LENGTH_SHORT).show();

        }

        public ButtonClicksImpl(int i) {

            this.i = i;
        }

        protected ButtonClicksImpl(Parcel in) {
            i = in.readInt();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(i);
        }

        @SuppressWarnings("unused")
        public final Parcelable.Creator<ButtonClicksImpl> CREATOR = new Parcelable.Creator<ButtonClicksImpl>() {
            @Override
            public ButtonClicksImpl createFromParcel(Parcel in) {
                return new ButtonClicksImpl(in);
            }

            @Override
            public ButtonClicksImpl[] newArray(int size) {
                return new ButtonClicksImpl[size];
            }
        };
    }
}
