package Stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class IsSymbolsBalanced {
    public DynamicStackArray<Character> openingStack;
    private char[] openingSymbols = {'(', '{', '['};
    private char[] closingSymbols = {')', '}', ']'};
    private Set<Character> openingSymbolsSet;
    private Set<Character> closingSymbolsSet;
    private Hashtable<Character, Character> table;

    public IsSymbolsBalanced () {
        this.openingStack = new DynamicStackArray(4);
        this.openingSymbolsSet = new HashSet<>();
        this.closingSymbolsSet = new HashSet<>();
        this.table = new Hashtable<>();
        for (char c: this.openingSymbols) {
           this.openingSymbolsSet.add(c);
        }
        for (char c: this.closingSymbols) {
            this.closingSymbolsSet.add(c);
        }
        for (int i = 0; i < this.openingSymbols.length; i++) {
            this.table.put(this.closingSymbols[i], this.openingSymbols[i]);
        }
    }

    public boolean isBalanced (String str) {
        for (char c: str.toCharArray()) {
            if (this.openingSymbolsSet.contains(c)) {
                this.openingStack.push(c);
            } else if (this.closingSymbolsSet.contains(c)) {
                if (this.openingStack.isEmpty()) {
                    return false;
                }
                char pair = this.table.get(c);
                if (pair != this.openingStack.pop()) {
                    return false;
                }
            }
        }

        return this.openingStack.isEmpty();
    }
}
