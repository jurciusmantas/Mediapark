package jurcius.mantas.mediapark;

import java.io.Serializable;

public class Car implements Serializable {

    public final String id;
    public final String plateNumber;
    public final Location location;
    public final Model model;
    public final String batteryPercentage;
    public final String batteryEstimatedDistance;
    public final String isCharging;

    public Car(String id, String plateNumber, String locationID, String latitude, String longitude,
               String address, String modelID, String title, String photoUrl, String batteryPercentage,
               String batteryEstimatedDistance, String isCharging){
        this.id = id;
        this.plateNumber = plateNumber;
        location = new Location(locationID, latitude, longitude, address);
        model = new Model(modelID, title, photoUrl);
        this.batteryPercentage = batteryPercentage;
        this.batteryEstimatedDistance = batteryEstimatedDistance;
        this.isCharging = isCharging;
    }

    public String getModelTitle(){
        return model.title;
    }

    public class Location implements Serializable{
        public final String id;
        public final String latitude;
        public final String longitude;
        public final String address;

        private Location(String id, String latitude, String longitude, String address){
            this.id = id;
            this.latitude = latitude;
            this.longitude = longitude;
            this.address = address;
        }
    }

    public class Model implements Serializable{
        public final String id;
        public final String title;
        public final String photoUrl;

        private Model(String id, String title, String photoUrl){
            this.id = id;
            this.title = title;
            this.photoUrl = photoUrl;
        }
    }

    //Used for filter
    @Override
    public String toString(){
        return this.plateNumber;
    }
}
