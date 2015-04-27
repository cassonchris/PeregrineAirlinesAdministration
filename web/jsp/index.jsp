<%-- 
    Document   : index
    Created on : Apr 26, 2015, 2:41:46 PM
    Author     : Chris
--%>

<%@page import="com.peregrineairlines.entities.PlaneModel"%>
<%@page import="com.peregrineairlines.entities.Airport"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Collection<Airport> airports = (Collection) request.getAttribute("airports");
    Collection<PlaneModel> planeModels = (Collection) request.getAttribute("planeModels");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adminstration</title>
        <link rel="stylesheet" href="css/jquery-ui.css" />
        <script src="js/jquery-1.9.1.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script>
            $(function () {
                $(".datepicker").datepicker({minDate: 0});
            });
        </script>
    </head>
    <body>
        <h1>Schedule Flights</h1>
        <%
            if (airports != null && planeModels != null) {
        %>
        <form action="/PeregrineAirlinesAdministration/ScheduleFlights">
            <table>
                <tr>
                    <td>From:</td>
                    <td>
                        <select name="from">
                            <%
                                for (Airport airport : airports) {
                            %>
                            <option value="<%= airport.getAirportId()%>"><%= airport.getCity()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>To:</td>
                    <td>
                        <select name="to">
                            <%
                                for (Airport airport : airports) {
                            %>
                            <option value="<%= airport.getAirportId()%>"><%= airport.getCity()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Plane Model:</td>
                    <td>
                        <select name="planeModel">
                            <%
                                for (PlaneModel planeModel : planeModels) {
                            %>
                            <option value="<%= planeModel.getPlaneModelId()%>"><%= planeModel.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Depart Datetime:</td>
                    <td>
                        <input type="date" class="datepicker" name="departDate" />
                        <select name="hours">
                            <% for (int i = 0; i < 24; i++) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% }%>
                        </select>
                        <select name="minutes">
                            <% for (int i = 0; i < 60; i+=5) {%>
                            <option value="<%= i%>"><%= i%></option>
                            <% }%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Schedule Flight" />
                    </td>
                </tr>
            </table>
        </form>
        <%
            }
        %>
    </body>
</html>
