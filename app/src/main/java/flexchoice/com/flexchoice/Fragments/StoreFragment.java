package flexchoice.com.flexchoice.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

import flexchoice.com.flexchoice.R;

public class StoreFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mgoogleMap;
    MapView mmapView;
    ImageButton email;
    ImageButton mphone;
    private static final int REQUEST_CALL = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
      View  view= inflater.inflate(R.layout.fragment_store, container, false);

        mphone = (ImageButton)view.findViewById(R.id.pphonemap);
        email = (ImageButton)view.findViewById(R.id.mapemail);

        mphone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        return view;
    }

    private void makePhoneCall() {



//        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
//                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:7259812420")));
            }
//        } else {
//            Toast.makeText(getActivity(), "Enter Phone NUmber", Toast.LENGTH_SHORT).show();
//
//        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();

            }
        }
        }

    protected void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"cflexflexchoice@gmail.com"});
//        emailIntent.setData(Uri.parse("mailto:cflexflexchoice@gmail.com"));
        emailIntent.setType("text/plain");
//        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
//        email.putExtra(Intent.EXTRA_EMAIL,"cflexflexchoice@gmail.com");
////        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
////        email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
        startActivity(Intent.createChooser(emailIntent, "Choose an email client from..."));
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        mmapView = (MapView) view.findViewById(R.id.map);
        if(mmapView != null)
        {
            mmapView.onCreate(null);
            mmapView.onResume();
            mmapView.getMapAsync(this);
        }


    }

    public void onMapReady(GoogleMap googleMap)
    {
        MapsInitializer.initialize((getContext()));

        mgoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(12.9933,77.5878)).title("store").snippet("Vasanth Nagar Palace Road banaglore -52"));
        CameraPosition Liberty = CameraPosition.builder().target(new LatLng(12.9933,77.5878)).zoom(16).bearing(0).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
    }

}
