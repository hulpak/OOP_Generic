package spreadtest.Grammar;
import spreadtest.Grammar.Coordinates.CoordinateFormatException;
import spreadtest.Grammar.Coordinates.Coordinates;
import spreadtest.Grammar.Error.Error;
import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.Operations.Add;
import spreadtest.Grammar.Operations.Div;
import spreadtest.Grammar.Operations.Mul;
import spreadtest.Grammar.Operations.Sub;
import spreadtest.Grammar.Type_of_cells.Number;
import spreadtest.Grammar.sheet.SheetContext;
import spreadtest.Grammar.sheet.SheetUtils;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator implements Expression {
    private Expression syntaxTree;

    public Evaluator(String key, String expression)
    {
        String postfix = convert(expression);
        Stack<Expression> stack = new Stack<Expression>();
        for (String token : postfix.split(" ")) {
            if  (token.equals("+")) {
                Expression subExpression = new Add(stack.pop(), stack.pop());
                stack.push( subExpression );
            }
            else if (token.equals("-")) {
                // it's necessary remove first the right operand from the stack
                Expression right = stack.pop();
                // ..and after the left one
                Expression left = stack.pop();
                Expression subExpression = new Sub(left, right);
                stack.push( subExpression );
            }
            else if (token.equals("*"))
            {
                Expression mulExpression = new Mul(stack.pop(), stack.pop());
                stack.push( mulExpression );
            }
            else  if (token.equals("/"))
            {
                Expression right = stack.pop();
                Expression left = stack.pop();
                Expression divExpression = new Div(left,  right);
                stack.push(divExpression);
            }
            else if (SheetUtils.tryParseInt(token)) {
                  int number  = Integer.parseInt(token);
                  stack.push( new Number(number));
            }
            else if (Coordinates.tryParse(token)) // try parse cell reference
            {
                try {
                    Coordinates coordinate = Coordinates.Parse(token);
                    if (coordinate.getName().equals(key)) {
                        syntaxTree = new Error("cross ref");
                        return;
                    }
                    stack.push(Coordinates.Parse(token));
                } catch (CoordinateFormatException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        syntaxTree = stack.pop();
    }
    /**
     * Operators in reverse order of precedence.
     */
    private static final String operators = "-+/*";
    public String convert(String infixExpr) {
        Stack<String> stack = new Stack<String>();
        Pattern pattern = Pattern.compile("((\\w*\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)]))");
        Matcher m = pattern.matcher(infixExpr);
        StringBuilder out = new StringBuilder(infixExpr.length());
        while (m.find()) {
            String c = m.group();
            if (isOperator(c)) {
                while (!stack.isEmpty()) {
                    out.append(stack.pop());
                    out.append(" ");
                }
                stack.push(c);
            }
            else {
                out.append(c);
                out.append(" ");
            }
        }
        while (!stack.empty()) {
            out.append(stack.pop());
            out.append(" ");
        }
        return out.toString();
    }
    private boolean isOperator(String val) {
        return operators.indexOf(val) >= 0;
    }

    @Override
    public String process(SheetContext context) {
        return syntaxTree.process(context);
    }
}
