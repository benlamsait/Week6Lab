/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ben Lam
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get Htttp session variable
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String username = (String) session.getAttribute("username");
        // shopping list ArrayList

        // no username in session
        if (username == null || username.equals("")) {
            // send user to register page
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
            // logout action requested
        } else if (action != null && action.equals("logout")) {
            // invalidate the session and send to register page
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
            // if they have a user name, send them to the shopping list page
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String usernameSSN = (String) session.getAttribute("username");
        String usernameLocal = request.getParameter("username");

        ArrayList<String> shoppingList = (ArrayList<String>) session.getAttribute("shoppingList");
        String item = "";
        String deleteThis = "";

        System.out.println("usernameLocal" + usernameLocal + "**");
        System.out.println("usernameSession:" + usernameSSN + "**");

        // for user registerting
        if (action.equals("register") && usernameLocal != null && !usernameLocal.equals("")) {
            //fetch the username from the form
            System.out.println("made it to the right part!!!");

            session.setAttribute("username", usernameLocal);
            System.out.println("the current session variable!: " + session.getAttribute("username"));
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
           // if no registration of valid user session
        } else if (usernameSSN == null || usernameSSN.equals("")) {
            // send user to registerpage
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
            // if user trying to add an item
        } else if (action.equals("add") && action != null & !action.equals("")) {

            // singleton pattern
            if (shoppingList == null) {
                shoppingList = new ArrayList<>();
            }

            System.out.println("in the block before the parap gets added");
            // add item to the list
            item = request.getParameter("item");
            if (action.equals("add") && item != null && !item.equals("")) {

                shoppingList.add(item);
            }
            System.out.println("the array list is " + shoppingList.size() + " long");
            session.setAttribute("shoppingList", shoppingList);
            
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else if(action.equals("delete") && action != null & !action.equals("")){
            deleteThis = request.getParameter("theList");
            
            shoppingList.remove(deleteThis);
            
            session.setAttribute("shoppingList", shoppingList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
            
        }
    }
}