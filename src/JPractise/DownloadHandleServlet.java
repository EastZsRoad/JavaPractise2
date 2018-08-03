package JPractise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DownloadHandleServlet ")
public class DownloadHandleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String path = "C:\\Users\\Lenovo\\IdeaProjects\\JavaPractise2\\out\\artifacts\\JavaPractise2_war_exploded\\WEB-INF\\upload\\";
        String filename = request.getParameter("filename");
        File file = new File(path + filename);
        if(file.exists()){
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream ouputStream = response.getOutputStream();
            byte b[] = new byte[1024];
            int n ;
            while((n = inputStream.read(b)) != -1){
                ouputStream.write(b,0,n);
            }
            ouputStream.close();
            inputStream.close();
        }else{
            request.setAttribute("errorResult", "文件不存在,下载失败!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
