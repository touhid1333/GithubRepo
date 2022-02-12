
package com.touhid.githubrepo.pojo.commitsresponse;


import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Committer implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("date")
    @Expose
    private String date;
    public final static Creator<Committer> CREATOR = new Creator<Committer>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Committer createFromParcel(android.os.Parcel in) {
            return new Committer(in);
        }

        public Committer[] newArray(int size) {
            return (new Committer[size]);
        }

    }
    ;

    protected Committer(android.os.Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Committer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(date);
    }

    public int describeContents() {
        return  0;
    }

}
