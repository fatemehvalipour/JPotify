package Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * gets the Lyrics of the music from a site
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 * @version 1.0
 */
public class Lyric {

    private final static String songLyricsURL = "http://www.songlyrics.com";

    /**
     *
     * @param band
     * @param songTitle
     * @return lyrics of music
     * @throws IOException
     */
    public static List<String> getSongLyrics(String band, String songTitle) throws IOException {
        List<String> lyrics = new ArrayList<>();

        Document doc = Jsoup.connect(songLyricsURL + "/" + band.replace(" ", "-").toLowerCase() + "/" + songTitle.replace(" ", "-").toLowerCase() + "-lyrics/").get();
        String title = doc.title();
        System.out.println(title);
        Element p = doc.select("p.songLyricsV14").get(0);
        for (Node e : p.childNodes()) {
            if (e instanceof TextNode) {
                lyrics.add(((TextNode) e).getWholeText());
            }
        }
        return lyrics;
    }
}
