package Model;

import java.util.ArrayList;
import java.util.List;

public class ImagePaths {

    private List<String> listOfImagePaths;


    public ImagePaths() {
        listOfImagePaths = new ArrayList<>();
        createImagePathStrings();
    }

    public void createImagePathStrings() {
        for (int i = 2; i < 15; i++) {
            listOfImagePaths.add("src/Cards/Clubs/cardClubs" + i +".png");
        }
        for (int i = 2; i < 15; i++) {
            listOfImagePaths.add("src/Cards/Diamonds/cardDiamonds" + i + ".png");
        }
        for (int i = 2; i < 15; i++) {
            listOfImagePaths.add("src/Cards/Hearts/cardHearts" + i + ".png");
        }
        for (int i = 2; i < 15; i++) {
            listOfImagePaths.add("src/Cards/Spades/cardSpades" + i + ".png");
        }
    }

    public String getImagePath(int index){
        return listOfImagePaths.get(index);
    }
}
