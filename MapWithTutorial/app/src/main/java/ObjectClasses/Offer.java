package ObjectClasses;

import android.graphics.Bitmap;

import StaticFunctionality.Cropper;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by Nick on 10.11.2017.
 */

/**
 *   Доступ не Карта - маркер, а Карта - список оферов - офер - маркер
 *   Приспособленца бы сюды
 *   Догрузка missdata (список и его прогрузка?)
 *
 */

public class Offer {
    public int id;
    public Marker marker;                                                                           //for map
    public int level;                                                                               //for map
    public String currency;                                                                         //everywhere
    public String price;                                                                            //everywhere
    public String description;                                                                      //for catalog
    public Bitmap photo;                                                                            //everywhere
    public float mark;                                                                              //for catalog
    public String type;                                                                             //mostly for catalog


    /**
     *
     * @param _level level of marker on map; helps in displaying
     * @param _photo picture of offer
     * @param _marker marker link
     * @param _type type of offer
     */
    public Offer(int _id, String _currency, String _price, int _level, Bitmap _photo, Marker _marker, String _type){
        id = _id;
        currency = _currency;
        price = _price;
        marker = _marker;
        level = _level;
        type = _type;
        photo = _photo;
        marker.setIcon(Cropper.cropCircleBitmapDescriptor(_photo));
    }

    /**
     *constructor for
     * @param _currency currency name
     * @param _price offer price(if exist)
     * @param _photo picture of offer
     * @param _mark mark of offer
     * @param _type type of offer
     */
    public Offer(int _id, String _currency, String _price, Bitmap _photo, float _mark, String _type){
        id = _id;
        currency = _currency;
        mark = _mark;
        price = _price;
        photo = _photo;
        type = _type;
    }


    /**
     * makes stored marker visible on map
     */
    public void MakeVisible(){
        marker.setVisible(true);
    }

    /**
     * makes stored marker unvisible on map
     */
    public void MakeUnvisible(){
        marker.setVisible(false);
    }

}
