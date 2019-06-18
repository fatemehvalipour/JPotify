package Data;

import java.util.ArrayList;

public class IP {
    private static ArrayList<IP> IPlist = new ArrayList<>();

    public IP(String IP) {
        IPlist.add(this);
    }

    public static ArrayList<IP> getIPlist() {
        return IPlist;
    }

    public static void setIPlist(ArrayList<IP> IPlist) {
        IP.IPlist = IPlist;
    }
}
