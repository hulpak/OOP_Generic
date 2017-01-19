package spreadtest.Grammar.Operations;
import spreadtest.Grammar.Error.Error;
import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.sheet.SheetContext;
public class Div implements Expression {
    private Expression first;
    private Expression second;

    public Div(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public String process(SheetContext context) {
        try {
            return Float.toString(Float.parseFloat(first.process(context)) / Float.parseFloat(second.process(context)));
        } catch (NumberFormatException e) {
            return new Error("Not a number").process(context);
        }
    }
}
