package com.Steven.movieApplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Steven on 6/2/2018.
 * model for the cinema information
 * use parcel
 */

public class CinemaLocation implements Parcelable {

    private static final String TAG = "CinemaLocation";

    public static final LatLng CBD = new LatLng(-37.909771, 145.132103);
    public static final LatLng CHADSTONE = new LatLng(-37.876999, 145.044236);
    public static final LatLng CAUFIELD = new LatLng(-38.153160, 145.134976);

    private String mName;
    private String mStreet;
    private String mSuburb;
    private String mState;
    private String mPostcode;
    private String mCountry;
    private String mImageURL;

    public CinemaLocation() {
        // A blank constructor is required for firebase connectivity
    }


    // Most of this was generated based on the variables
    public CinemaLocation(String name, String street, String suburb, String state, String postcode, String country, String imageURL) {
        mName = name;
        mStreet = street;
        mSuburb = suburb;
        mState = state;
        mPostcode = postcode;
        mCountry = country;
        mImageURL = imageURL;
    }

    protected CinemaLocation(Parcel in) {
        mName = in.readString();
        mStreet = in.readString();
        mSuburb = in.readString();
        mState = in.readString();
        mPostcode = in.readString();
        mCountry = in.readString();
        mImageURL = in.readString();
    }

    public static final Creator<CinemaLocation> CREATOR = new Creator<CinemaLocation>() {
        @Override
        public CinemaLocation createFromParcel(Parcel in) {
            return new CinemaLocation(in);
        }

        @Override
        public CinemaLocation[] newArray(int size) {
            return new CinemaLocation[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mStreet);
        dest.writeString(mSuburb);
        dest.writeString(mState);
        dest.writeString(mPostcode);
        dest.writeString(mCountry);
        dest.writeString(mImageURL);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStreet() {
        return mStreet;
    }

    public void setStreet(String street) {
        mStreet = street;
    }

    public String getSuburb() {
        return mSuburb;
    }

    public void setSuburb(String suburb) {
        mSuburb = suburb;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getPostcode() {
        return mPostcode;
    }

    public void setPostcode(String postcode) {
        mPostcode = postcode;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        mImageURL = imageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
