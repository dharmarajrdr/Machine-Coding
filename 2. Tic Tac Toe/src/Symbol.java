
public enum Symbol {
    X('X'),
    O('O'),
    A('A'),
    B('B'),
    C('C');

    private final char value;

    Symbol(char value) {
        this.value = value;
    }

    // Static method to convert char to enum
    public static Symbol fromChar(char c) {
        for (Symbol e : Symbol.values()) {
            if (e.value == c) {
                return e;
            }
        }
        return null;
    }
}
