package Graphic.Listeners;

import Data.Library;
import Data.Music;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SearchListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Library> searchedMusic = new ArrayList<>();
        String searchedText = ((JTextField)e.getSource()).getText().toUpperCase();
        for (Library music : Music.getMusics()){
            if (((Music)music).getTitle().toUpperCase().contains(searchedText) || ((Music)music).getArtist().toUpperCase().contains(searchedText)){
                searchedMusic.add(music);
            }
        }
        try {
            Library.getGraphic().showLibrary(searchedMusic, false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
