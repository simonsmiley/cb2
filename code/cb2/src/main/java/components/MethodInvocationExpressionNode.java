package components;

import java.util.ArrayList;

import components.helpers.Position;
import components.interfaces.ExpressionNode;
import components.interfaces.MemberExpressionNode;
import parser.Token;
import visitors.Visitor;

public class MethodInvocationExpressionNode extends MemberExpressionNode {
    public final ArrayList<ExpressionNode> arguments;

    public MethodInvocationExpressionNode(ExpressionNode baseObject, Token identifier,
            ArrayList<ExpressionNode> arguments, Position position) {
        super(baseObject, identifier, position);
        this.arguments = arguments;
    }

    @Override
    public <R, P, E extends Throwable> R accept(Visitor<R, P, E> visitor, P parameter) throws E {
        return visitor.visit(this, parameter);
    }
}