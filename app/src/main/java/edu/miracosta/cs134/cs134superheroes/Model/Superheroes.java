package edu.miracosta.cs134.cs134superheroes.Model;

import java.util.Objects;

public class Superheroes {

    private String mName;
    private String mFileName;
    private String mOneThing;
    private String mSuperpower;


    public Superheroes(String mName, String mOneThing, String mSuperpower, String mFileName) {
        this.mSuperpower = mSuperpower;
        this.mName = mName;
        this.mOneThing = mOneThing;
        this.mFileName = mFileName;
    }

    public String get_mFileName() {
        return mFileName;
    }

    public void set_mFileName(String mFileName) {
        this.mFileName = mFileName;
    }



    public String get_mOneThing() {
        return mOneThing;
    }

    public void set_mOneThing(String mOneThing) {
        this.mOneThing = mOneThing;
    }

    public String get_mSuperpower() {
        return mSuperpower;
    }

    public void set_mSuperpower(String mSuperpower) {
        this.mSuperpower = mSuperpower;
    }

    public String get_mName() {
        return mName;
    }

    public void set_mName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return "Superheroes{" +
                "mName='" + mName + '\'' +
                ", mFileName='" + mFileName + '\'' +
                ", mOneThing='" + mOneThing + '\'' +
                ", mSuperpower='" + mSuperpower + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Superheroes that = (Superheroes) o;
        return Objects.equals(mName, that.mName) &&
                Objects.equals(mFileName, that.mFileName) &&
                Objects.equals(mOneThing, that.mOneThing) &&
                Objects.equals(mSuperpower, that.mSuperpower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, mFileName, mOneThing, mSuperpower);
    }

}
