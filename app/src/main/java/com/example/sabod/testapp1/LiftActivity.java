package com.example.sabod.testapp1;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by sabod on 10/30/2015.
 */
public class LiftActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift);
//        onPause();
/*        FragmentLift1 frag = new FragmentLift1();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.lift_id, frag, "LiftFragment1");
        transaction.commit();
*/

    }

/*    @Override
    public void respond(String data) {
        FragmentManager manager = getFragmentManager();
        FragmentLift2 f2 = (FragmentLift2) manager.findFragmentById(R.id.fragmentLift2);
        f2.changeText(data);
    }*/

/*    @Override
    public void respond(String data) {
        FragmentManager manager=getFragmentManager();
        FragmentLift2 f2 = (FragmentLift2) manager.findFragmentById(R.id.fragmentLift2);
        f2.changeText(data);
    }
*/
}
