package com.example.mac_address;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String host = "192.168.1.21";
    private String mac;
    private String port = "5000";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webview);
        try {
            List<NetworkInterface> networkInterfaceList = Collections.list(NetworkInterface.getNetworkInterfaces());

            StringBuilder stringMac = new StringBuilder();

            for (NetworkInterface networkInterface:networkInterfaceList){
                if (networkInterface.getName().equalsIgnoreCase("wlan0")){
                    for (int i=0; i<networkInterface.getHardwareAddress().length;i++){
                        String stringMacByte = Integer.toHexString(networkInterface.getHardwareAddress()[i] & 0xFF);

                        if (stringMacByte.length() == 1){
                            stringMacByte = "0" + stringMacByte;
                        }

                        stringMac.append(stringMacByte.toUpperCase()).append(":");
                    }
                    break;
                }
            }

            mac = stringMac.substring(0,stringMac.length()-1);
            webView.loadUrl("http://"+ host +":"+ port +"/mac_address/"+ mac);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}