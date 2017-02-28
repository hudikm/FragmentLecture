package fri.uniza.sk.fragmentlecture;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fri.uniza.sk.fragmentlecture.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Main activity");

        ItemFragment itemFragment = ItemFragment.newInstance(1);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentTop,itemFragment);
        fragmentTransaction.commit();



    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
