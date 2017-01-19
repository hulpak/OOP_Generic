package spreadtest.Grammar.Operations;
import spreadtest.Grammar.Error.Error;
import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.sheet.SheetContext;
public class Mul implements Expression {
    private Expression first;
    private Expression second;

    public Mul(Expression second, Expression first) {
        this.second = second;
        this.first = first;
    }
    @Override
    public String process(SheetContext context) {
        try {
            return Float.toString(Float.parseFloat(second.process(context)) * Float.parseFloat(first.process(context)));
        } catch (NumberFormatException pe) {
            return new Error("Not a number").process(context);
        }
    }
}
