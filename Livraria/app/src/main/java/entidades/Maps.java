package entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Maps implements Parcelable {

    private int id;
    private Double latitude;
    private Double Longitude;

    public Maps(int id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.Longitude = longitude;
    }

    public Maps() {

    }

    protected Maps(Parcel in) {
        id = in.readInt();
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            Longitude = null;
        } else {
            Longitude = in.readDouble();
        }
    }

    public static final Creator<Maps> CREATOR = new Creator<Maps>() {
        @Override
        public Maps createFromParcel(Parcel in) {
            return new Maps(in);
        }

        @Override
        public Maps[] newArray(int size) {
            return new Maps[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (Longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Longitude);
        }
    }
}
