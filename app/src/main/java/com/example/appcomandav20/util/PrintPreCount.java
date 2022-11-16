package com.example.appcomandav20.util;

import android.annotation.SuppressLint;

import com.dantsu.escposprinter.EscPosPrinter;
import com.dantsu.escposprinter.connection.tcp.TcpConnection;

public class PrintPreCount {
    public void printTcp(String ip, int port, com.example.appcomandav20.domain.model.PreCount item ) {
        new Thread(new Runnable() {
            @SuppressLint("NewApi")
            public void run() {
                try {
                    EscPosPrinter printer = new EscPosPrinter(new TcpConnection(ip, port), 203, 65f, 42);

                    final StringBuilder builder = new StringBuilder();
                    item.getDetalle().forEach((val)->{
                        builder.append("[L]<font size='tall'>"+val.getCantidad()+" "+val.getNombre() +"[R]"+val.getImporte()+"</font>\n");
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
