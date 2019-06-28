package network;

import java.io.Serializable;

public class SendingMusic implements Serializable {
    String musicTitle;
    byte[] sendingFile;

    public SendingMusic(String musicTitle, byte[] sendingFile) {
        this.musicTitle = musicTitle;
        this.sendingFile = sendingFile;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public byte[] getSendingFile() {
        return sendingFile;
    }
}
