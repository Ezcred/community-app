/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */

package com.mifos.objects.noncore;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * Created by ishankhanna on 02/07/14.
 */
public class Document implements Parcelable {

    private int id;
    private String parentEntityType;
    private int parentEntityId;
    private String name;
    private String fileName;
    private long size;
    private String type;
    private String description;
    private String comments;
    private Verified verified;
    private String location;
    private String metadata;

    public String getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParentEntityType() {
        return parentEntityType;
    }

    public void setParentEntityType(String parentEntityType) {
        this.parentEntityType = parentEntityType;
    }

    public int getParentEntityId() {
        return parentEntityId;
    }

    public void setParentEntityId(int parentEntityId) {
        this.parentEntityId = parentEntityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Verified getVerified() {
        return verified;
    }

    public void setVerified(Verified verified) {
        this.verified = verified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.parentEntityType);
        dest.writeInt(this.parentEntityId);
        dest.writeString(this.name);
        dest.writeString(this.fileName);
        dest.writeLong(this.size);
        dest.writeString(this.type);
        dest.writeString(this.description);
    }

    public Document() {
    }

    protected Document(Parcel in) {
        this.id = in.readInt();
        this.parentEntityType = in.readString();
        this.parentEntityId = in.readInt();
        this.name = in.readString();
        this.fileName = in.readString();
        this.size = in.readLong();
        this.type = in.readString();
        this.description = in.readString();
    }

    @Data
    public static class Verified {
        private final long id;
        private final String code;
        private final String value;
    }

    public static final Parcelable.Creator<Document> CREATOR = new Parcelable.Creator<Document>() {
        @Override
        public Document createFromParcel(Parcel source) {
            return new Document(source);
        }

        @Override
        public Document[] newArray(int size) {
            return new Document[size];
        }
    };

    public enum DocumentVerificationStatusEnum {
        NOT_VERIFIED,
        VERIFIED,
        REJECTED,
        PASSWORD_PENDING;
    }

    @Data
    @Builder
    public static class DocumentMetadata {

        private static final String LAT = "lat";
        private static final String LONG = "long";

        @SerializedName(LAT)
        private final String latitude;

        @SerializedName(LONG)
        private final String longitude;

        private final String timestamp;
        private final String timeFormat;
    }
}
