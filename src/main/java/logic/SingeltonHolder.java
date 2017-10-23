package logic;

public class SingeltonHolder {
    public static SingeltonHolder holder = new SingeltonHolder();

    private static String bestQuote=null;

    private SingeltonHolder(){

    }

    public static String getBestQuote(){
        if (bestQuote!=null)
        {
            String temp=bestQuote;
            bestQuote=null;
            return temp;
        }
        return bestQuote;
    }

    public static void setBestQuote(String best) {
        bestQuote = best;
    }
}
