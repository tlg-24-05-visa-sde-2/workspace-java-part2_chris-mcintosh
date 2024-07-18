package com.entertainment;

import java.util.Objects;
/*
 *Natural order is defined by 'brand' (String).
 *
 * to be "consistent with equals, " whatever fields are used for "equals"
 * MUST ALSO BE USED for "compareTo".
 */
public class Television implements Comparable<Television> {
    //Fields
    private String brand;
    private int volume;

    //Television HAS-A Tuner
    private Tuner tuner = new Tuner();  //initiated internally, not exposed


    //Constructors
    public Television() {

    }

    public Television(String brand, int volume) {
        setBrand(brand);
        setVolume(volume);
    }
    //Business methods
    public int getCurrentChannel() {
        return tuner.getChannel();
    }

    public void changeChannel(int channel) {
        tuner.setChannel(channel);
    }

    //Accessor methods
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Television that = (Television) obj;
        return getVolume()== that.getVolume() && Objects.equals(getBrand(), that.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getVolume());
    }

    /*
    @Override
    public int hashCode(){

        // a poorly written hash function, it easily results in "hash collisions,"
        //i.e., "different" objects can easily yield the same hashcode
        //return getBrand().length() + getVolume();

        //instead, we use Objects.hash() to give us a "scientifically correct" hash function
        //it will minimize the probability of hash collisions
        return Objects.hash(getBrand(), getVolume());
    }
    */

    /*
    @Override
    public boolean equals(Object obj){
        boolean result = false;

        //proceed only if obj IS-A television\
        if(obj instanceof Television){
            //downcast obj to more specific reference type Television, to call getBrand(), etc.
            Television other = (Television)obj;

            //do the checks - sameness is defined as same-brand AND same-volume
            result = Objects.equals(this.getBrand(), other.getBrand()) &&
                     this.getVolume() == other.getVolume();  //primitive's can't be null
        }
        return result;
    }
    */

    /*
     * Natural order is defined by brand (String), and secondarily byu "volume" (int)
     * when tied on brand
     */

    @Override
    public int compareTo(Television other) {
        int result = this.getBrand().compareTo(other.getBrand());
        if (result == 0) {
            result = Integer.compare(this.getVolume(), other.getVolume());
        }
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "Television {" +
                "brand='" + getBrand() + '\'' +
                ", volume=" + getVolume() +
                ", current Channel=" + getCurrentChannel() +
                '}';
    }
}