package spreadtest.Grammar.Type_of_cells;
import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.sheet.SheetContext;

import java.util.Arrays;

/**
 * @param value - for  class Text
 * method process which return value
 */
public class Text implements Expression {
    private String value;


    public Text(String value) {
        this.value = value;
    }

    @Override
    public String process(SheetContext variables)
    {
        char []arr = value.toCharArray();
        char[] result = new char[arr.length-1];
        char symbol = 39;// ascii code  = " ' "
        //if first element is '
        if(arr[0]==symbol){
            result = Arrays.copyOfRange(arr,1,arr.length);
        }
        // without prefix
        else{
            result = arr;
        }
        this.value = String.valueOf(result);
        return value;
    }
}
