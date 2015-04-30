/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.administration.servlets;

import com.peregrineairlines.model.PAModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris
 */
public class ScheduleFlights extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String nextUrl = "/Home";

        String fromString = request.getParameter("from");
        String toString = request.getParameter("to");
        String planeModelString = request.getParameter("planeModel");
        String departDateString = request.getParameter("departDate");
        String hoursString = request.getParameter("hours");
        String minutesString = request.getParameter("minutes");

        Integer from = null;
        Integer to = null;
        Integer planeModel = null;
        Date departDate = null;
        Integer hours;
        Integer minutes;

        if (fromString != null) {
            from = Integer.parseInt(fromString);
        }

        if (toString != null) {
            to = Integer.parseInt(toString);
        }

        if (planeModelString != null) {
            planeModel = Integer.parseInt(planeModelString);
        }

        if (departDateString != null) {
            try {
                departDate = new SimpleDateFormat("M/d/y").parse(departDateString);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(departDate);
                
                if (hoursString != null) {
                    hours = Integer.parseInt(hoursString);
                    calendar.add(Calendar.HOUR_OF_DAY, hours);
                }

                if (minutesString != null) {
                    minutes = Integer.parseInt(minutesString);
                    calendar.add(Calendar.MINUTE, minutes);
                }
                
                departDate = calendar.getTime();
            } catch (ParseException ex) {
                Logger.getLogger(ScheduleFlights.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (from != null && to != null && planeModel != null && departDate != null) {
            PAModel.scheduleFlight(from, to, planeModel, departDate);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextUrl);
        dispatcher.forward(request, response);
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
