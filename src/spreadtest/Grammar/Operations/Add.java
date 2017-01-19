package spreadtest.Grammar.Operations;
import spreadtest.Grammar.Error.Error;
import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.sheet.SheetContext;

public class Add implements Expression {
    private Expression first;
    private Expression second;

    public Add(Expression left, Expression right) {
        first = left;
        second = right;
    }
    @Override
    public String process(SheetContext context) {
        try {
            return Float.toString(Float.parseFloat(first.process(context)) + Float.parseFloat(second.process(context)));
        } catch (NumberFormatException nfe) {
            return new Error("Not a number").process(context);
        }
    }
}
