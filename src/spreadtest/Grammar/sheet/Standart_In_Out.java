package spreadtest.Grammar.sheet;

import spreadtest.Grammar.Evaluator;
import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.Type_of_cells.Number;
import spreadtest.Grammar.Type_of_cells.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Standart_In_Out {


    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

   static int maxY = 0;
    static int maxX = 0;

    public static Sheet Write() {
        boolean f = true;
        Scanner sc = new Scanner(System.in);
        String line;
        int lineNum = 1;
        int rowNumCode;

        Map<String, Expression> filledCells = new HashMap<String, Expression>();
        String reader;
        while(f) {
            reader = sc.nextLine();
            line = reader;
            if (line.length()!=0) {
                String splitArray[] = line.split(" ");
                if (splitArray.length > maxX) {
                    maxX = splitArray.length;
                }
                maxY++;
                rowNumCode = (int) 'A';
                for (String item : splitArray) {

                    if (item.startsWith("=")) {
                        filledCells.put("" + (char) rowNumCode + lineNum, new Evaluator("" + (char) rowNumCode + lineNum, item.substring(1, item.length())));
                    } else if (SheetUtils.tryParseInt(item)) {
                        filledCells.put("" + (char) rowNumCode + lineNum, new Number(Integer.parseInt(item)));
                    } else if (item.startsWith("'")) {
                        filledCells.put("" + (char) rowNumCode + lineNum, new Text(item.substring(1, item.length())));
                    }

                    if (rowNumCode < (int) 'Z')
                        rowNumCode += 1;
                    else
                        throw new IndexOutOfBoundsException("Sheet is too wide");  //todo: extend these bounds
                }

                if (maxY > lineNum) maxY = lineNum;

                lineNum += 1;
                if (lineNum >= 41) {
                    throw new IndexOutOfBoundsException("Sheet is too high"); // todo: extend these bounds
                }
            }else{
                f=false;
            }

        }
//for(Map.Entry<String,Expression> m :filledCells.entrySet())
//{
//    System.out.println(m.getKey()+" "+m.getValue());
//}
        return new Sheet(new SheetContext(maxX, maxY, filledCells));

    }
}
