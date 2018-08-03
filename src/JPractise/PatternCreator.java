package JPractise;

public class PatternCreator extends Creator {

    public  <T extends Pattern> T createPattern(Class<T> c){
        Pattern pattern = null;
        try {

            pattern = (Pattern) Class.forName(c.getName()).newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) pattern;

}

    public static void main(String[] args){
        Pattern pattern;
        Creator creator = new PatternCreator();
        pattern = creator.createPattern(Square.class);
        pattern.serNumber(5,6);
    }
}
