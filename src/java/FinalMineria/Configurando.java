/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalMineria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name = "Configurando", urlPatterns = {"/Configurando"})
public class Configurando extends HttpServlet {

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
        String accion = request.getParameter("formCantPersonas");
        int cantPersonas = Integer.valueOf(request.getParameter("cantPersonas"));
        if("formCantPersonas".equals(accion))
        {
            request.setAttribute("cantPersonas", cantPersonas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./nombrePersonas.jsp");
            dispatcher.forward(request, response);          
        }
        if("formCantNombres".equals(accion))
        {
            String nombres = "";
            for(int i = 1; i<=cantPersonas; i++)
            {
                if(i==1)
                    nombres = nombres.concat(request.getParameter("Persona#"+String.valueOf(i)));
                else
                    nombres = nombres.concat(","+request.getParameter("Persona#"+String.valueOf(i)));
            }
            String contenido = "cmd /c C:\\Users\\Enmanuel\\openSMILE-2.1.0\\SMILExtract_Release.exe -C C:\\Users\\Enmanuel\\openSMILE-2.1.0\\config\\MCFF.conf -I C:\\Users\\Enmanuel\\openSMILE-2.1.0\\example-audio\\opensmile.wav -O C:\\Users\\Enmanuel\\openSMILE-2.1.0\\baseDatos.arff -instname Persona -classes {"+nombres+"}";
            File file = new File("C:\\Users\\Enmanuel\\openSMILE-2.1.0\\Confclases.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            String contenido2 = nombres;
            File file2 = new File("C:\\Users\\Enmanuel\\openSMILE-2.1.0\\nombres.txt");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            fw = new FileWriter(file2.getAbsoluteFile());
            BufferedWriter bw2 = new BufferedWriter(fw);
            bw2.write(contenido2);
            bw.close();
            bw2.close();
            response.sendRedirect("./index.jsp");
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