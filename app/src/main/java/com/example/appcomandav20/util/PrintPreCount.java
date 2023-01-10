package com.example.appcomandav20.util;

import android.content.Context;
import android.widget.Toast;

import com.emh.thermalprinter.EscPosPrinter;
import com.emh.thermalprinter.connection.tcp.TcpConnection;
import com.example.appcomandav20.features.orders.domain.model.PreCount;

public class PrintPreCount {
    public void printTcp(String ip, int port, PreCount item, Context context ) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    EscPosPrinter printer = new EscPosPrinter(new TcpConnection(ip, port), 203, 65f, 42);

                    final StringBuilder builder = new StringBuilder();
                    item.getDetalle().forEach((val)->{
                        builder.append("[L]<font size='tall'>").append(val.getCantidad()).append(" ").append(val.getNombre()).append("[R]").append(val.getImporte()).append("</font>\n");
                    });

                    // imprimes
                    printer.printFormattedTextAndCut(
                            "[C]<b><u><font size='big'>PRE-CUENTA</font></u></b>\n[L]\n" +
                                    "[L]<b><font size='tall'> PEDIDO: "+item.getNumerO_PEDIDO()+"</font></b>[L]\n"+
                                    "[L]<font size='tall'> ZONA: "+item.getZona()+"</font>[L]\n"+
                                    "[L]<font size='tall'> MESA: "+item.getMesa()+"</font>[L]\n"+
                                    "[L]<font size='tall'> MOZO: "+item.getMesero()+"</font>[L]\n"+
                                    "[L] _______________\n" +
                                    "[L]<font size='tall'> Descripcion [R]Importe \n</font>[L]\n" +
                                    builder+
                                    "[L] _______________\n" +
                                    "[L]<font size='tall'> SUBTOTAL [R]"+item.getSubtotal()+"\n" +
                                    "[L]<font size='tall'> IGV [R]"+item.getIgv()+"\n" +
                                    "[L]<font size='tall'> IMPORTE TOTAL [R]"+item.getTotal()+"\n" +
                                    "[L]\n" +
                                    "[L] _______________\n" +
                                    "[L]\n"
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
