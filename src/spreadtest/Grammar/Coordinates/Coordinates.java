package spreadtest.Grammar.Coordinates;
import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.sheet.SheetContext;

public class Coordinates implements Expression {
    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    private String column;
    private int row;

    public String getName() {
        return name;
    }

    private String name;

    public Coordinates(String column, int row) {
        this.column = column;
        this.row = row;
        this.name = column+row;
    }

    public static Coordinates Parse(String name) throws CoordinateFormatException {
        if (name.length() > 1) {
            String column = "" + name.charAt(0);
            int row = 0;
            try {
                row = Integer.parseInt(name.substring(1));
            } catch (NumberFormatException nfe) {
                throw new CoordinateFormatException(nfe);
            }
            return new Coordinates(column, row);
        }
        throw new CoordinateFormatException("Length doesn't fit to spreadsheet cell coordinate");
    }

    public static boolean tryParse(String name)
    {
          if (name.length() <= 1)
              return false;
        if (!name.matches("\\w*\\d+"))
                  return false;
        return  true  ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        if (row != that.row) return false;
        if (!column.equals(that.column)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = column.hashCode();
        result = 31 * result + row;
        return result;
    }

    public String process(SheetContext context) {
        if  (context.contains(this))
        {
            return context.get(this).process(context);
        }
        return "";
    }

}
