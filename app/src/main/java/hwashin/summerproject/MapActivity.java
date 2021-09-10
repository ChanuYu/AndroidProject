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
    public double longitude;
    public double latitude;
    public String currentLocation;
    private MapView mapView;

    String[] names = {
            "국제휘트니스", "컨디션피트니스", "지젤피트니스클럽", "벨로시티",
            "지 스포츠 컴플렉스"
    };

    double[] lats = {
        35.237212, 35.22067, 35.236374, 35.23721, 35.22948,
    };
    double[] lons = {
        129.009564, 129.04031, 129.011210, 129.01130, 129.01287,
    };
    MapPOIItem[] markers = new MapPOIItem[5];
    Building[] buildings = new Building[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //action.setDisplayHomeAsUpEnabled(false);
        //action.setDisplayShowHomeEnabled(false);
        //action.setDisplayShowTitleEnabled(false);

        //text1 = (TextView)findViewById(R.id.onquery);

        mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup)findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        ThreadClass thread = new ThreadClass();
        thread.start();

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(35.233691,129.013264);
        //getMyLocation();  GPS기반 현위치로 화면 전환
        mapView.setMapCenterPoint(mapPoint,true);

        MapPOIItem marker = new MapPOIItem();

        marker.setItemName("Current Location");
        marker.setTag(0);
        //marker.setMapPoint(MapPoint.mapPointWithCONGCoord(latitude,longtitude));
        marker.setMapPoint(mapPoint);
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
    class ThreadClass extends Thread{
        @Override
        public void run() {
            for(int i=0;i<5;i++){
                buildings[i] = new Building(names[i],lats[i],lons[i]);
                markers[i] = new MapPOIItem();
                markers[i].setItemName(buildings[i].name);
                markers[i].setTag(i+1);
                markers[i].setMarkerType(MapPOIItem.MarkerType.BluePin);
                markers[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                Log.d("test",markers[i].getItemName());
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<5;i++){
                        try{
                            mapView.addPOIItem(markers[i]);
                            Log.d("test",markers[i].getItemName());
                        } catch (Exception e){
                            Log.d("test","failed to add marker"+i);
                        }
                    }
                }
            });
        }
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

    class Building{
        String name;
        double latitude;
        double longitude;

        public Building(){}
        public Building(String name, double lat, double lon){
            this.name = name;
            this.longitude = lon;
            this.latitude = lat;
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
                longitude = location.getLongitude();

                setDaumMapCurrentLocation(latitude,longitude);
                locationManager.removeUpdates(this);
            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                100,1,mLocationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                100, 1,mLocationListener);

    }
    public void setDaumMapCurrentLocation(double latitude, double longitude){
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude,
                longitude), true);
        mapView.setZoomLevel(4,true);
        mapView.zoomIn(true);

        //setDaumMapCurrentMarker();
    }
    public void setDaumMapCurrentMarker(){
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("현재 위치");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithCONGCoord(latitude,longitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(marker);
    }

}