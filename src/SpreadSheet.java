
import spreadtest.Grammar.sheet.Sheet;
import spreadtest.Grammar.sheet.Standart_In_Out;


public class SpreadSheet {
    public static void main(String[] args) throws Exception  {

        Standart_In_Out stand = new Standart_In_Out();
        Sheet st =Standart_In_Out.Write();
        //row,col
        st.write(stand.getMaxX(),stand.getMaxY());

    }


}

