
package operation;

import java.io.Serializable;

public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    int a;
    int b;
    char c;

    public Operation(int a, int b, char c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int calculer() {

        int res = 0;

        switch (c) {

            case '+':
                res = a + b;
                break;

            case '-':
                res = a - b;
                break;

            case '*':
                res = a * b;
                break;

            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division par zéro impossible");
                }
                res = a / b;
                break;

            default:
                throw new IllegalArgumentException("Opérateur invalide");
        }

        return res;
    }
}
