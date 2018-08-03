package JPractise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ComputeServlet")
public class ComputeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Shape> sList = new ArrayList<Shape>();
        try{
        String[] number1 = request.getParameterValues("longth");
        String[] number2 = request.getParameterValues("width");
        String shapes[];
        String lens[];
        String areas[];
        shapes = new String[number1.length];
        for (int i = 0; i < number1.length; i++) {
            Shape s = new Shape();
            String shape = request.getParameter(String.format("shape" + (i + 1)));
            shapes[i] = shape;
            s.setId(i+1);
            s.setNumber1(Double.valueOf(number1[i]));
            if (number2[i] != "") {
                s.setNumber2(Double.valueOf(number2[i]));
            }
            s.setShapetype(shapes[i]);
            sList.add(s);
        }
        DataTransfer dt = new DataTransfer();
        List<Shape> sListTmp = dt.ComputeOutput(sList);
        DBconnector dbc = new DBconnector();
        for (int i = 0; i < sListTmp.size(); i++) {
            Shape s = sListTmp.get(i);
            dbc.InsertData(s.getId(), s.getShapetype(), s.getNumber1(), s.getNumber2(), s.getLength(), s.getArea());
        }

        List<Shape> sList2 = dbc.SearchData();
        HttpSession session = request.getSession();
        session.setAttribute("sList2", sList2);
        request.getRequestDispatcher("/start.jsp").forward(request, response);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

