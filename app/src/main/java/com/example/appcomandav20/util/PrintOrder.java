package com.example.appcomandav20.util;

import com.emh.thermalprinter.EscPosPrinter;
import com.emh.thermalprinter.connection.tcp.TcpConnection;
import com.example.appcomandav20.features.orders.domain.model.OrderModel;

public class PrintOrder {

    public void printTcp(String ip, int port, OrderModel item) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    EscPosPrinter printer = new EscPosPrinter(new TcpConnection(ip, port), 203, 65f, 42);

                    final StringBuilder builder = new StringBuilder();
                    item.getDetalle().forEach((val)->{
                        builder.append("[L] <font size='tall'>").append(val.getCantidad()).append(" ").append(val.getProducto()).append("</font>[L]\n");
                        if(!val.getObservacion().isEmpty()){
                            builder.append("[L] <font size='tall'> Obs: ").append(val.getObservacion()).append("</font>[L]\n");
                            builder.append("[L] _______________\n");
                        }
                    });

                    printer.printFormattedTextAndCut(
                            "[C]<b><u><font size='big'>"+item.getDestino()+"</font></u></b>\n[L]\n" +
                                    "[L]<b><font size='tall'> ZONA: "+item.getZona()+"</font></b>[L]\n"+
                                    "[L]<b><font size='tall'> MESA: "+item.getMesa()+"</font></b>[L]\n"+
                                    "[L]<b><font size='tall'> MOZO: "+item.getMesero()+"</font></b>[L]\n"+
                                    "[L]<font size='tall'> PEDIDO: "+item.getNumerO_PEDIDO()+"</font>[L]\n"+
                                    "[L]<font size='tall'> FECHAR Y HORA:"+item.getFechayhora()+"</font>[L]\n"+
                                    "[L] \n" +
                                    "[L]<font size='tall'> PRODUCTOS [L]</font>\n" +
                                    builder+
                                    "[L] _______________\n"+
                                    "[L]\n"
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
