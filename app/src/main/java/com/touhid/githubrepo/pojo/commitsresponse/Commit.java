
package com.touhid.githubrepo.pojo.commitsresponse;


import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Commit implements Parcelable {

    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("committer")
    @Expose
    private Committer committer;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("tree")
    @Expose
    private Tree tree;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("verification")
    @Expose
    private Verification verification;

    public final static Creator<Commit> CREATOR = new Creator<Commit>() {


        @Override
        public Commit createFromParcel(android.os.Parcel in) {
            return new Commit(in);
        }

        @Override
        public Commit[] newArray(int size) {
            return (new Commit[size]);
        }

    };

    protected Commit(android.os.Parcel in) {
        this.author = ((Author) in.readValue((Author.class.getClassLoader())));
        this.committer = ((Committer) in.readValue((Committer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.tree = ((Tree) in.readValue((Tree.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.commentCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.verification = ((Verification) in.readValue((Verification.class.getClassLoader())));
    }

    public Commit() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(author);
        dest.writeValue(committer);
        dest.writeValue(message);
        dest.writeValue(tree);
        dest.writeValue(url);
        dest.writeValue(commentCount);
        dest.writeValue(verification);
    }

    public int describeContents() {
        return 0;
    }

}
