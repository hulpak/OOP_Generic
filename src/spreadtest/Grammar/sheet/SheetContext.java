package spreadtest.Grammar.sheet;
import spreadtest.Grammar.Coordinates.Coordinates;
import spreadtest.Grammar.Interface.Expression;
import spreadtest.Grammar.Type_of_cells.Nothing;
import java.util.Iterator;
import java.util.Map;

public class SheetContext implements Iterator<Expression> {
    private int sheetSizeX;
    private int sheetSizeY;
    private Map<String, Expression> activeCells;
    private SheetContext context;

    private int currentLetter = (int) 'A' - 1;
    private int currentRow = 1;

    public SheetContext(int sizeX, int sizeY, Map<String, Expression> cells) {
        this.sheetSizeX = sizeX + (int) 'A';
        this.sheetSizeY = sizeY;
        this.activeCells = cells;
    }

    public Expression get(Coordinates coordinates) {
        if (coordinates == null) return null;
        if (activeCells.containsKey(coordinates.getName())) {
            return activeCells.get(coordinates.getName());
        } else if ((int) coordinates.getColumn().charAt(0) <= sheetSizeX || coordinates.getRow() <= sheetSizeY) {
            return new Nothing();
        }
        return null;
    }

    public boolean contains(Coordinates coordinates) {
        return (int) coordinates.getColumn().charAt(0) <= sheetSizeX || coordinates.getRow() <= sheetSizeY;
    }
    @Override
    public boolean hasNext() {
        return (currentLetter <= sheetSizeX && currentRow < sheetSizeY);
    }

    @Override
    public Expression next() {
        if (currentLetter + 1 < sheetSizeX) {
            currentLetter++;
        } else if (currentLetter + 1 >= sheetSizeX && currentRow + 1 <= sheetSizeY) {
            currentLetter = (int) 'A';
            currentRow++;
        } else {
            return null;
        }
        return this.get(new Coordinates((char) currentLetter + "", currentRow));
    }
   }
