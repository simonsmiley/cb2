package components.interfaces;

import components.ExpressionNode;
import parser.Token;
import visitors.ASTVisitor;

public abstract class BinaryExpressionNode extends ExpressionNode {
    public ExpressionNode first;
    public ExpressionNode second;
    public Token operator;
    public void balance(){
        
    }
    
    public abstract Integer precedence();
        
    
    public BinaryExpressionNode(Token operator) {
        this.operator = operator;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visitPre(this);
        visitor.visit(this);
        visitor.visitAfter(this);
    }
}
