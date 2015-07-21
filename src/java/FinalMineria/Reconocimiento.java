/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalMineria;

import Herramientas.Grabador;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name = "Reconocimiento", urlPatterns = {"/Reconocimiento"})
public class Reconocimiento extends HttpServlet {

    Grabador grabar;
    long tiempo = 60000;
    String linea;
    String nombre;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        nombre = request.getParameter("nombreSeleccionado");
        linea = "enmanuel,candido";
        String[] nombres = linea.split(",");
        if("Detener".equals(accion))
        {
            grabar.finish();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GrabarAudio.class.getName()).log(Level.SEVERE, null, ex);
            }
            Runtime.getRuntime().exec("cmd /c C:\\Users\\Enmanuel\\openSMILE-2.1.0\\SMILExtract_Release.exe -C C:\\Users\\Enmanuel\\openSMILE-2.1.0\\config\\IS12_speaker_trait.conf -I C:\\Users\\Enmanuel\\grabacion.wav -O C:\\Users\\Enmanuel\\openSMILE-2.1.0\\prueba"+nombre+".arff -instname Persona -classes {"+linea+"} -classlabel "+nombre);
            response.sendRedirect("./index.jsp");
            
        }
        else if("Grabar".equals(accion))
        {
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date();
//            String nombre = request.getParameter("nombreSeleccionado");
            grabar = new Grabador();
            Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(tiempo);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                grabar.finish();
            }
        });
 
        stopper.start();
 
        // start recording
        grabar.start();
        response.sendRedirect("./grabar.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
