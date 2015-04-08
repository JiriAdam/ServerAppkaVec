package util;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Irrielde on 3.4.2015.
 */
public class ListPrinter {

    public static void printList(List list, String listName){

        Iterator it = list.iterator();

        if(listName !=null) {
            System.out.println("");
        }else{
            System.out.println("List");
        }

        System.out.println("_Size: " + list.size());

        while(it.hasNext()){
            Object object = it.next();
            System.out.println(" " + object.toString());

        }

    }


}
