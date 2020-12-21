import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.Object;
import org.pcap4j.packet.*;

public class Testing {
    InetAddress addr = InetAddress.getByName("192.168.10.100");
    PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);

    int snapLen = 65536;
    PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
    int timeout = 10;
    PcapHandle handle = nif.openLive(snapLen, mode, timeout);

    Packet packet = handle.getNextPacketEx();
    handle.close();

    IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
    Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
    System.out.println(srcAddr);
}