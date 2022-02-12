
package com.touhid.githubrepo.pojo.commitsresponse;


import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Verification implements Parcelable {

    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("signature")
    @Expose
    private String signature;
    @SerializedName("payload")
    @Expose
    private String payload;

    public final static Creator<Verification> CREATOR = new Creator<Verification>() {


        @Override
        public Verification createFromParcel(android.os.Parcel in) {
            return new Verification(in);
        }

        @Override
        public Verification[] newArray(int size) {
            return (new Verification[size]);
        }

    };

    protected Verification(android.os.Parcel in) {
        this.verified = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.reason = ((String) in.readValue((String.class.getClassLoader())));
        this.signature = ((String) in.readValue((String.class.getClassLoader())));
        this.payload = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Verification() {
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(verified);
        dest.writeValue(reason);
        dest.writeValue(signature);
        dest.writeValue(payload);
    }

    public int describeContents() {
        return 0;
    }

}
