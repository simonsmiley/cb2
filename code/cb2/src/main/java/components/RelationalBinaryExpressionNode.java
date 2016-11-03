package components;

import parser.Token;

public class RelationalBinaryExpressionNode extends BinaryExpressionNode {

    public RelationalBinaryExpressionNode(Token operator) {
        super(operator);
    }

    public Integer precedence() {
        return 4;
    }

}
