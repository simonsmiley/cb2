package components;

import components.interfaces.ExpressionNode;
import parser.Token;
import visitors.ASTVisitor;

public class BinaryExpressionNode extends ExpressionNode {
    public final ExpressionNode first;
    public final ExpressionNode second;
    public final Token operator;

    public BinaryExpressionNode(Token operator, ExpressionNode first, ExpressionNode second) {
        super(operator);
        this.operator = operator;
        this.first = first;
        this.second = second;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
