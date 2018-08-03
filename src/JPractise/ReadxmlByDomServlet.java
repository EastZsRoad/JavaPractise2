package JPractise;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.PrintWriter;

@WebServlet(name = "ReadxmlByDomServlet")
public class ReadxmlByDomServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String file = "C:\\Users\\Lenovo\\IdeaProjects\\JavaPractise2\\out\\artifacts\\JavaPractise2_war_exploded\\WEB-INF\\upload\\shape.xml";
        try{
            List<Shape> sList ;
            DataTransfer dt = new DataTransfer();
            sList = dt.getShapes(file);
            HttpSession session = request.getSession();
            session.setAttribute("sList", sList);
            request.getRequestDispatcher("/start.jsp").forward(request, response);


        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
