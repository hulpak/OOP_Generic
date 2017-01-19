package spreadtest.Grammar.Error;


import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.sheet.SheetContext;

public class Error implements Expression {
    /**
     *@param errorMessage
     * this class out errors with #
     */
    private String errorMessage;
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public String process(SheetContext variables) {
        return "#" + errorMessage;
    }
}
