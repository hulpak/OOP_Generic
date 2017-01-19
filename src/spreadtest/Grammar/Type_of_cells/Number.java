package spreadtest.Grammar.Type_of_cells;

import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.sheet.SheetContext;

public class Number implements Expression {
    private float number;

    public Number(float number) {
        this.number = number;
    }
    @Override
    public String process(SheetContext context) {
        return Float.toString(number);
    }
}
