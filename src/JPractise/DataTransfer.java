package JPractise;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class DataTransfer {
    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;
    private static Document document = null;
    private static List<Shape> Shapes = null;
    static{
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            db = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public  String ShapetoJson(List<Shape> sList) {
        JSONArray array = new JSONArray();
        for (Shape s : sList) {
            JSONObject shape = new JSONObject();
            shape.put("id", s.getId());
            shape.put("shapetype", s.getShapetype());
            shape.put("number1", s.getNumber1());
            shape.put("number2", s.getNumber2());
            shape.put("length", s.getLength());
            shape.put("area", s.getArea());
            array.add(shape);
        }
        return array.toString();
    }
    public  List<Shape> getShapes(String fileName) throws Exception {

        document = db.parse(fileName);
        NodeList ShapeList = document.getElementsByTagName("shape");
        Shapes = new ArrayList<Shape>();
        for (int i = 0; i < ShapeList.getLength(); i++) {
            Shape Shape = new Shape();
            org.w3c.dom.Node node = ShapeList.item(i);
            NamedNodeMap namedNodeMap = node.getAttributes();
            String id = namedNodeMap.getNamedItem("id").getTextContent();
            Shape.setId(Integer.parseInt(id));
            NodeList cList = node.getChildNodes();
            ArrayList<String> contents = new ArrayList<>();
            for (int j = 1; j < cList.getLength(); j += 2) {
                org.w3c.dom.Node cNode = cList.item(j);
                String content = cNode.getFirstChild().getTextContent();
                contents.add(content);
            }
            int flg = contents.size();

            if(flg > 4){
                Shape.setArea(Double.parseDouble(contents.get(4)));
            }
            if(flg > 3){
                Shape.setLength(Double.parseDouble(contents.get(3)));
            }
            if(flg > 2){
                Shape.setNumber2(Double.parseDouble(contents.get(2)));
            }
            if(flg > 1){
                Shape.setNumber1(Double.parseDouble(contents.get(1)));
            }
            if(flg > 0){
                Shape.setShapetype((contents.get(0)));
            }
            Shapes.add(Shape);
        }
        return Shapes;
    }

    public List<Shape> ComputeOutput(List<Shape> sList) {

        for (Shape s : sList) {
            String shapetype = s.getShapetype();
            Creator creator = new PatternCreator();
            Double number1 = s.getNumber1();
            Double number2 = s.getNumber2();
            Pattern pattern;
            if (shapetype.equals("RECT")){
                pattern = creator.createPattern(Rectangle.class);

            }else if(shapetype.equals("CIRC")){
                pattern = creator.createPattern(Circular.class);

            }else if(shapetype.equals("SQUA")){
                pattern = creator.createPattern(Square.class);

            }else {
                break;
            }
            pattern.serNumber(number1,number2);
            s.setLength(pattern.getLength());
            s.setArea(pattern.getArea());
        }
        return sList;
    }
        public static void main(String[] args){

            try{
                DataTransfer t = new DataTransfer();
                List<Shape> sList = t.getShapes("C:\\Users\\Lenovo\\IdeaProjects\\JavaPractise2\\out\\artifacts\\JavaPractise2_war_exploded\\WEB-INF\\upload\\shape.xml");
                System.out.println(t.ShapetoJson(sList));
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

