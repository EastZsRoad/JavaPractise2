<%@ page import="java.util.ArrayList" %>
<%@ page import="JPractise.Shape" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/8/2
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@page contentType="text/html; charset=GB2312"%>
<html>
<head>
    <title>
        index jsp file
    </title>
</head>


<h2 style="font-family:arial;color:#000000;font-size:25px;text-align:center">计算周长和面积</h2>



<form action="ComputeServlet" method="post" align="center" target=_blank>
    <div id="div0">

    </div>
    <input type="submit" name="submit" value="计算" >
    <input type="reset"  name="reset" value="重置" onclick="resetdiv();">
    <input type="button" name="button" value="添加" onclick="addfunc();"><br/><br/>

    <div class="input-chunk">
    <input type="file" value="上传文件" id="upload-file"align="center">

    <a id="start-upload" href="javascript:void(0);" onclick="uploadFile();">开始上传</a><br/>
    下载：<a href="DownloadHandleServlet?filename=shape.xml">shape.xml</a>
    </div>

    <div id="out1" align="center">

        <c:forEach items="${sessionScope.sList2}" var="s" >
        <table><table border='1'>
            <tr><td>${s.getId()}</td>
                <td>${s.getShapetype()}</td>
                <td>${s.getNumber1()}</td>
                <td>${s.getNumber2()}</td>
                <td>${s.getLength()}</td>
                <td>${s.getArea()}</td></tr>
        </table>
            </c:forEach>
    </div>

</form>

<form action="ReadxmlByDomServlet" method="post" align="center" target=_blank>

    <input type="submit" name="submit" value="导入XML" >
    <div id="out2" align="center">

        <c:forEach items="${sessionScope.sList}" var="s" >
        <%--<script type="text/javascript">--%>
            <%--addfunc(s.getShapetype(),s.getNumber1(),s.getNumber2() );--%>
        <%--</script>--%>
        <table><table border='1'>
            <tr><td>${s.getId()}</td>
                <td>${s.getShapetype()}</td>
                <td>${s.getNumber1()}</td>
                <td>${s.getNumber2()}</td>
                <td>${s.getLength()}</td>
                <td>${s.getArea()}<a onclick="addfunc('${s.getShapetype()}','${s.getNumber1()}','${s.getNumber2()}')">导入</a></td></tr>
        </table>
            </c:forEach>
    </div>
</form>


</html>


<script>

    function resetdiv() {
        var out1 = document.getElementById("out1");
        out1.remove();
        var out2 = document.getElementById("out2");
        out2.remove();

    }

    function uploadFile(){
        var fileObj = document.getElementById("upload-file").files[0];
        var FileController = "UploadHandleServlet";
        if(fileObj){
            alert(fileObj);
            var form = new FormData();
            form.append("file", fileObj);
            var xhr = new XMLHttpRequest();
            xhr.open("post", FileController, true);
            xhr.onload = function () {
                alert(xhr.responseText);
            };
            xhr.send(form);
        }else{
            alert("未选择文件");
        }
    }


    function checkid()
    {
        var check = document.getElementById("ckid");
        if(check.checked ){
            check.value = "1";
        }else{
            check.value = "0";
        }

    }

    var countflg = 1;
    var currentdiv = document.getElementById("div0");

    function addfunc(a, b, c) {
        var textNode1 = document.createTextNode("形状：");
        var textNode2 = document.createTextNode("矩形");
        var textNode3 = document.createTextNode("圆形");
        var textNode4 = document.createTextNode("正方形");
        var textNode5 = document.createTextNode("长：");
        var textNode6 = document.createTextNode("宽：");
        var textNode7 = document.createElement("半径");
        var textNode8 = document.createElement("边长");

        var divid = "div" + countflg.toString();
        var radio1 = "1b" + countflg.toString();
        var radio2 = "2b" + countflg.toString();
        var radio3 = "3b" + countflg.toString();
        var shapename = "shape" + countflg.toString();
        var textid1 = "1t" + countflg.toString();
        var textid2 = "2t" + countflg.toString();
        var ipt1 = document.createElement("input");
        ipt1.setAttribute("id", radio1);
        ipt1.setAttribute("type", "radio");
        ipt1.setAttribute("name", shapename);
        ipt1.setAttribute("value", "RECT");
        var ipt2 = document.createElement("input");
        ipt2.setAttribute("id", radio2);
        ipt2.setAttribute("type", "radio");
        ipt2.setAttribute("name", shapename);
        ipt2.setAttribute("value", "CIRC");
        var ipt3 = document.createElement("input");
        ipt3.setAttribute("id", radio3);
        ipt3.setAttribute("type", "radio");
        ipt3.setAttribute("name", shapename);
        ipt3.setAttribute("value", "SQUA");
        var ipt4 = document.createElement("input");
        ipt4.setAttribute("id", textid1);
        ipt4.setAttribute("type", "number");
        ipt4.setAttribute("name", "longth");
        ipt4.setAttribute("size", "20");
        ipt4.setAttribute("value", b);
        ipt4.setAttribute("maxlength", "20");
        var ipt5 = document.createElement("input");
        ipt5.setAttribute("id", textid2);
        ipt5.setAttribute("type", "number");
        ipt5.setAttribute("name", "width");
        ipt5.setAttribute("size", "20");
        ipt5.setAttribute("value", c);
        ipt5.setAttribute("maxlength", "20");

        if (countflg < 20) {
            var div = document.createElement("div");
            div.id = divid;
            currentdiv.appendChild(div);
            div.appendChild(textNode1);

            ipt1.onclick = function () {
                textNode5.textContent = "长";
                textNode6.textContent = "宽";
                ipt5.style.visibility = "visible";
            } ;
            div.appendChild(ipt1);
            div.appendChild(textNode2);

            ipt2.onclick = function () {
                textNode5.textContent = "半径";
                textNode6.textContent = null;
                ipt5.style.visibility = "hidden";
            } ;
            div.appendChild(ipt2);
            div.appendChild(textNode3);

            ipt3.onclick = function () {
                textNode5.textContent = "边长";
                textNode6.textContent = null;
                ipt5.style.visibility = "hidden";
            } ;

            div.appendChild(ipt3);
            div.appendChild(textNode4);
            div.appendChild(document.createElement("br"));
            switch (a) {
                case "RECT":
                    alert("长方形");
                    ipt1.setAttribute("checked", "true");
                    textNode5.textContent = "长";
                    textNode6.textContent = "宽";
                    ipt5.style.visibility = "visible";
                    break;
                case "CIRC":
                    alert("圆形");
                    ipt2.setAttribute("checked", "true");
                    textNode5.textContent = "半径";
                    textNode6.textContent = null;
                    ipt5.style.visibility = "hidden";
                    break;
                case "SQUA":
                    alert("正方形");
                    ipt3.setAttribute("checked", "true");
                    textNode5.textContent = "边长";
                    textNode6.textContent = null;
                    ipt5.style.visibility = "hidden";
                    break;

            }
            div.appendChild(textNode5);
            div.appendChild(ipt4);
            div.appendChild(document.createElement("br"));
            div.appendChild(textNode6);

            div.appendChild(ipt5);
            div.appendChild(document.createElement("br"));
            countflg++;

        }
    }


</script>