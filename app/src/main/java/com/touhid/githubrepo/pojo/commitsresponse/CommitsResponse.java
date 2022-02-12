
package com.touhid.githubrepo.pojo.commitsresponse;

import java.util.List;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CommitsResponse implements Parcelable {

    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("node_id")
    @Expose
    private String nodeId;
    @SerializedName("commit")
    @Expose
    private Commit commit;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;
    @SerializedName("author")
    @Expose
    private Author__1 author;
    @SerializedName("committer")
    @Expose
    private Committer__1 committer;
    @SerializedName("parents")
    @Expose
    private List<Parent> parents = null;

    public final static Creator<CommitsResponse> CREATOR = new Creator<CommitsResponse>() {


        @Override
        public CommitsResponse createFromParcel(android.os.Parcel in) {
            return new CommitsResponse(in);
        }

        @Override
        public CommitsResponse[] newArray(int size) {
            return (new CommitsResponse[size]);
        }

    };

    protected CommitsResponse(android.os.Parcel in) {
        this.sha = ((String) in.readValue((String.class.getClassLoader())));
        this.nodeId = ((String) in.readValue((String.class.getClassLoader())));
        this.commit = ((Commit) in.readValue((Commit.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.htmlUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.commentsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.author = ((Author__1) in.readValue((Author__1.class.getClassLoader())));
        this.committer = ((Committer__1) in.readValue((Committer__1.class.getClassLoader())));
        in.readList(this.parents, (com.touhid.githubrepo.pojo.commitsresponse.Parent.class.getClassLoader()));
    }

    public CommitsResponse() {
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public Author__1 getAuthor() {
        return author;
    }

    public void setAuthor(Author__1 author) {
        this.author = author;
    }

    public Committer__1 getCommitter() {
        return committer;
    }

    public void setCommitter(Committer__1 committer) {
        this.committer = committer;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(sha);
        dest.writeValue(nodeId);
        dest.writeValue(commit);
        dest.writeValue(url);
        dest.writeValue(htmlUrl);
        dest.writeValue(commentsUrl);
        dest.writeValue(author);
        dest.writeValue(committer);
        dest.writeList(parents);
    }

    public int describeContents() {
        return 0;
    }

}
