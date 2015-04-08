package notification.apitest;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import manager.RegionManager;
import mybatis.model.complex.Region;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Irrielde on 8.3.2015.
 */
public class GoogleTest {

    @Autowired
    private RegionManager regionManager;

    private static String googleApiKey = "AIzaSyDz3QGCq7BTAmkJNKKEKmOrfw5NR1O4USo";

    public String test() {


        GeoApiContext context = new GeoApiContext().setApiKey(googleApiKey);


        GeocodingResult[] results = new GeocodingResult[0];

        try {

            //LatLng latLng = new LatLng(50.0,46.0);

            //GeocodingApiRequest result = GeocodingApi.reverseGeocode(context, latLng);

            results = GeocodingApi.geocode(context, "plzensky kraj").await();

            GeocodingResult res = results[0];

            System.out.println();

            AddressType[] ats = res.types;

            System.out.println("Cykluju vysledek dotazu: " + "AddressTypeSize: " + ats.length + "ResultSize: " + results.length);
            for (AddressType at : ats) {

                System.out.println("AT: " + at.toString());

                if (at.toString().equals("administrative_area_level_1")) {

                    System.out.println("yes it equals");
                    AddressComponent[] comps = res.addressComponents;

                    for (AddressComponent comp : comps) {

                        System.out.println("Komponenta:");
                        for (AddressComponentType type : comp.types) {
                            System.out.println("Typ: " + type.toString());

                            if (type.toString().equalsIgnoreCase("ADMINISTRATIVE_AREA_LEVEL_1")) {

                                System.out.println("Long name:" + comp.longName);
                                System.out.println("Short name: " + comp.shortName);

                                System.out.println("Tohle ukladam do db: " + comp.longName);
                                return "Tohle jde do db: " + comp.longName;
                            }


                        }


                    }

                    System.out.println("done equaling");

                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(results[0].formattedAddress);

        return results[0].formattedAddress;

    }

    public void testRegionu() {

        System.out.println(regionManager == null);

        List<Region> regionList = regionManager.getAllRegions();

        System.out.println("pocet regionu: ");
        System.out.println(regionList.size());

        for (int i = 0; i < 100; i++) {
            Region reg = regionList.get(i);
            if (reg.getArea1() != null) {
                System.out.println(reg.getId() + " /name: " + reg.getName() + " /google_name: " + reg.getArea1());
            } else {
                System.out.println(reg.getId() + " /name: " + reg.getName() + " /google_name: NULL");
            }

            System.out.println();
        }


    }

    public void cycleGoogle() {

        GeoApiContext context = new GeoApiContext().setApiKey(googleApiKey);


        try {

            //   LatLng latLng = new LatLng(50.0,46.0);
            //  GeocodingApiRequest result = GeocodingApi.reverseGeocode(context, latLng);


            GeocodingResult[] results = GeocodingApi.geocode(context, "plzensky kraj").await();

            GeocodingResult res = results[0];

            System.out.println();

            AddressType[] ats = res.types;

            System.out.println("Cykluju vysledek dotazu: " + "AddressTypeSize: " + ats.length + "ResultSize: " + results.length);
            for (AddressType at : ats) {

                System.out.println("AT: " + at.toString());

                if (at.toString().equals("administrative_area_level_1")) {

                    System.out.println("yes it equals");
                    AddressComponent[] comps = res.addressComponents;

                    for (AddressComponent comp : comps) {

                        System.out.println("Komponenta:");
                        for (AddressComponentType type : comp.types) {
                            System.out.println("Typ: " + type.toString());

                            if (type.toString().equalsIgnoreCase("ADMINISTRATIVE_AREA_LEVEL_1")) {

                                System.out.println("Long name:" + comp.longName);
                                System.out.println("Short name: " + comp.shortName);

                                System.out.println("Tohle ukladam do db: " + comp.longName);
                                //db save
                            }


                        }


                    }

                    System.out.println("done equaling");

                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getGoogleNameForRegion(String name) {

        GeoApiContext context = new GeoApiContext().setApiKey(googleApiKey);


        GeocodingResult[] results = null;


        try {

            //   LatLng latLng = new LatLng(50.0,46.0);
            //  GeocodingApiRequest result = GeocodingApi.reverseGeocode(context, latLng);

            results = GeocodingApi.geocode(context, name).await();



            if(results==null){
                System.out.println("Nic nenasel; Results = null");
                return null;
            }

            if(results.length == 0){
                System.out.println("Nic nenasel; Results = 0");
                return null;
            }

            GeocodingResult res = results[0];

            if(res==null){
                System.out.println("Nic nenasel; GeocodingResult = null");
                return null;
            }


         //   System.out.println("Result to string: ");


          //  System.out.println(res.formattedAddress);

            //  AddressType[] ats = res.types;


            AddressComponent[] tes = res.addressComponents;

            if(tes==null){
                System.out.println("Nic nenasel; AddressComponent[] = null");
                return null;
            }

           // System.out.println("Cykluju vysledek dotazu: " + "AddressComponentsSize: " + tes.length + " ResultSize: " + results.length);


         //   System.out.println("Pocet komponent " + tes.length);


            for (AddressComponent compa : tes) {

              //  System.out.println(" /------------/");


                for (AddressComponentType typ : compa.types) {
                //    System.out.println(typ.toString());


                    if (typ.toString().equalsIgnoreCase("administrative_area_level_1")) {

                  //      System.out.println("yes it equals, returning: " + compa.longName);

                        return compa.longName;

                        // System.out.println("done equaling");

                    }


                }

                //nema to area 1

                for (AddressComponentType typ : compa.types) {
                    //    System.out.println(typ.toString());


                    if (typ.toString().equalsIgnoreCase("administrative_area_level_2")) {

                        //      System.out.println("yes it equals, returning: " + compa.longName);

                        return compa.longName;

                        // System.out.println("done equaling");

                    }


                }

                for (AddressComponentType typ : compa.types) {
                    //    System.out.println(typ.toString());


                    if (typ.toString().equalsIgnoreCase("country")) {

                        //      System.out.println("yes it equals, returning: " + compa.longName);

                        return compa.longName;

                        // System.out.println("done equaling");

                    }


                }



                System.out.println(compa.longName);
                System.out.println(compa.shortName);
            }

/*
            for (AddressType at : ats) {

                System.out.println("AT: " + at.toString() + " ------------ ");

                if (at.toString().equalsIgnoreCase("administrative_area_level_1")) {

                    System.out.println("yes it equals");
                    AddressComponent[] comps = res.addressComponents;

                    for (AddressComponent comp : comps) {

                        System.out.println("Komponenta:" + comp.toString());
                        for (AddressComponentType type : comp.types) {
                          //  System.out.println("Typ: " + type.toString());

                            if(type.toString().equalsIgnoreCase("ADMINISTRATIVE_AREA_LEVEL_1")){

                                System.out.println("Long name:" + comp.longName);
                                System.out.println("Short name: " + comp.shortName);

                               // System.out.println("Tohle ukladam do db: " + comp.longName);

                                return comp.longName;
                            }


                        }


                    }

                    System.out.println("done equaling");

                }else{
                    AddressComponent[] comps = res.addressComponents;
                    System.out.println(" Probing :D ");
                    for (AddressComponent comp : comps) {

                        System.out.println("Komponenta:");
                        for (AddressComponentType type : comp.types) {
                            //  System.out.println("Typ: " + type.toString());

                            if(type.toString().equalsIgnoreCase("ADMINISTRATIVE_AREA_LEVEL_1")){

                                System.out.println("Long name:" + comp.longName);
                                System.out.println("Short name: " + comp.shortName);

                                // System.out.println("Tohle ukladam do db: " + comp.longName);


                            }


                        }


                    }
                }




            }
            */

        } catch (Exception e) {
            System.out.println("Exception in GoogleClass");
            e.printStackTrace();
        }

        // System.out.println(results[0].formattedAddress);

        // return results[0].formattedAddress;
        return null;


    }
}
