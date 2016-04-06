package edu.uark.ndavies.blackout;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ViewFlipper viewFlipper;// = (ViewFlipper) findViewById(R.id.viewFlipper);
    private float lastX;
    private EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        user = (EditText)findViewById(R.id.user_email);


    }
    //Handles swipes for changing views
    public boolean onTouchEvent(MotionEvent touchevent){

        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX)
                {
                    viewFlipper.setDisplayedChild(1);
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    viewFlipper.setDisplayedChild(0);
                }

            }
        }

        return false;
    }
    //Returns to default view on back pressed. If already on default view exits activity.
    @Override
    public void onBackPressed()
    {
        if(viewFlipper.getDisplayedChild() == 0){
            super.onBackPressed();
        }
        else {
            viewFlipper.setDisplayedChild(0);
            return;
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Fayetteville = new LatLng(36.0655409, -94.1731129);
        mMap.addMarker(new MarkerOptions().position(Fayetteville).title("Marker in Fayettevile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Fayetteville, 15));
    }
}
