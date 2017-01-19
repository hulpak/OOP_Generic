package spreadtest.Grammar.Type_of_cells;

import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.sheet.SheetContext;
public class Nothing implements Expression {
    @Override
    public String process(SheetContext context) {
        return "";
    }
}
