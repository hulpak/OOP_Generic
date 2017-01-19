package spreadtest.Grammar.sheet;

import spreadtest.Grammar.Coordinates.Coordinates;
import spreadtest.Grammar.Interface.Expression;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Sheet implements Iterable<Expression> {

    public Sheet(SheetContext context) {
        this.context = context;
    }

    public SheetContext getContext() {
        return context;
    }

    private SheetContext context;

    public Expression get(Coordinates coordinates) {
        return context.get(coordinates);
    }

    @Override
    public Iterator<Expression> iterator() {
        return (Iterator<Expression>) context;
    }

    public void write(int col,int row) throws IOException {

        String res;
        for (int j=0;j<row;j++) {
            for(int k=0;k<col;k++)
            {
                res = this.iterator().next().process(this.getContext());
                System.out.print(res+"\t");
            }
                System.out.println("");
        }

    }
}
