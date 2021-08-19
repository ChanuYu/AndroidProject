package hwashin.summerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;


import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MapActivity extends AppCompatActivity {

    String search_text;
    TextView text1;
    LocationManager locationManager;
    public double longtitude;
    public double latitude;
    public String currentLocation;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //action.setDisplayHomeAsUpEnabled(false);
        //action.setDisplayShowHomeEnabled(false);
        //action.setDisplayShowTitleEnabled(false);

        //text1 = (TextView)findViewById(R.id.onquery);

        mapView = new MapView(this);
        getMyLocation();
        ViewGroup mapViewContainer = (ViewGroup)findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("현재 위치");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithCONGCoord(latitude,longtitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        final MenuItem search = menu.findItem(R.id.search);
        final SearchView search_view = (SearchView)search.getActionView();
        search_view  .setQueryHint("검색");

        //SearchViewListener listener = new SearchViewListener();
        //search_view.setOnQueryTextListener(listener);

        return true;
    }

    class SearchViewListener implements SearchView.OnQueryTextListener{
        @Override
        public boolean onQueryTextChange(String newText) {
            text1.setText(newText);
            //데이터베이스에서 관련 헬스장 목록 띄우기
            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            search_text = query;
            text1.setText(search_text);
            //데이터베이스에서 해당 헬스장 검색하여 조회
            return false;
        }
    }

    public void getMyLocation(){
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_DENIED) return;
        }
        final LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("test","onLocationChanged, location: "+location);
                latitude = location.getLatitude();
                longtitude = location.getLongitude();

                setDaumMapCurrentLocation(latitude,longtitude);
                locationManager.removeUpdates(this);
            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                100,1,mLocationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                100, 1,mLocationListener);

    }
    public void setDaumMapCurrentLocation(double latitude, double longtitude){
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude,
                longtitude), true);
        mapView.setZoomLevel(4,true);
        mapView.zoomIn(true);

        //setDaumMapCurrentMarker();
    }
    public void setDaumMapCurrentMarker(){
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("현재 위치");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithCONGCoord(latitude,longtitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(marker);
    }

}