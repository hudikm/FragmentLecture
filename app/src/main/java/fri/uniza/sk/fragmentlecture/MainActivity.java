package fri.uniza.sk.fragmentlecture;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import fri.uniza.sk.fragmentlecture.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener, BottomFragment.ButtonClicks {

    private int i = 0;
    private ItemFragment itemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Main activity");

        itemFragment = ItemFragment.newInstance(1);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentTop, itemFragment);

        BottomFragment bottomFragment = new BottomFragment();
        fragmentTransaction.replace(R.id.fragmentBottom, bottomFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void addButtonClick() {
        Toast.makeText(MainActivity.this, "Add clicked", Toast.LENGTH_SHORT).show();
        ((MyItemRecyclerViewAdapter) itemFragment.getRecyclerView().getAdapter()).getmValues().add(new DummyContent.DummyItem("1", "Test", "Details"));
        itemFragment.getRecyclerView().getAdapter().notifyDataSetChanged();
    }

    @Override
    public void deleteButtonClick() {
        Toast.makeText(getApplicationContext(), "Del clicked", Toast.LENGTH_SHORT).show();

        List<DummyContent.DummyItem> dummyItems = ((MyItemRecyclerViewAdapter) itemFragment.getRecyclerView().getAdapter()).getmValues();
        if (dummyItems.size() > 0) {
            dummyItems.remove(dummyItems.size() - 1);
            itemFragment.getRecyclerView().getAdapter().notifyDataSetChanged();
        }


    }
}
