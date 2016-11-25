package components;

import components.interfaces.ExpressionNode;
import components.interfaces.StatementNode;
import parser.Token;
import visitors.Visitor;

public class DeclarationStatementNode extends StatementNode {
    public final Token name;
    public final ExpressionNode expression;

    public DeclarationStatementNode(Token name, ExpressionNode expression) {
        super(name);
        this.name = name;
        this.expression = expression;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
