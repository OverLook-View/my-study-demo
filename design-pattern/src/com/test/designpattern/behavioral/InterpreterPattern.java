package com.test.designpattern.behavioral;

public class InterpreterPattern {
    /**
     * 意图：给定一个语言，定义它的文法表示，并定义一个解释器，这个解释器使用该标识来解释语言中的句子。
     * <p>
     * 主要解决：对于一些固定文法构建一个解释句子的解释器。
     * <p>
     * 何时使用：如果一种特定类型的问题发生的频率足够高，那么可能就值得将该问题的各个实例表述为一个简单语言中的句子。这样就可以构建一个解释器，该解释器通过解释这些句子来解决该问题。
     * <p>
     * 如何解决：构件语法树，定义终结符与非终结符。
     * <p>
     * 关键代码：构件环境类，包含解释器之外的一些全局信息，一般是 HashMap。
     * <p>
     * 应用实例：编译器、运算表达式计算。
     * <p>
     * 优点： 1、可扩展性比较好，灵活。 2、增加了新的解释表达式的方式。 3、易于实现简单文法。
     * <p>
     * 缺点： 1、可利用场景比较少。 2、对于复杂的文法比较难维护。 3、解释器模式会引起类膨胀。 4、解释器模式采用递归调用方法。
     * <p>
     * 使用场景： 1、可以将一个需要解释执行的语言中的句子表示为一个抽象语法树。 2、一些重复出现的问题可以用一种简单的语言来进行表达。 3、一个简单语法需要解释的场景。
     * <p>
     * 注意事项：可利用场景比较少，JAVA 中如果碰到可以用 expression4J 代替。
     */

    interface Expression {
        public boolean interpret(String context);
    }

    static class TerminalExpression implements Expression {

        private String data;

        public TerminalExpression(String data) {
            this.data = data;
        }

        @Override
        public boolean interpret(String context) {
            if (context.contains(data)) {
                return true;
            }
            return false;
        }
    }

    static class OrExpression implements Expression {

        private Expression expression1 = null;
        private Expression expression2 = null;

        public OrExpression(Expression expression1, Expression expression2) {
            this.expression1 = expression1;
            this.expression2 = expression2;
        }

        @Override
        public boolean interpret(String context) {
            return expression1.interpret(context) || expression2.interpret(context);
        }
    }

    static class AndExpression implements Expression {

        private Expression expression1 = null;
        private Expression expression2 = null;

        public AndExpression(Expression expression1, Expression expression2) {
            this.expression1 = expression1;
            this.expression2 = expression2;
        }

        @Override
        public boolean interpret(String context) {
            return expression1.interpret(context) && expression2.interpret(context);
        }
    }

    //规则：Robert 和 John 是男性
    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie 是一个已婚的女性
    public static Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();
        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married woman? " + isMarriedWoman.interpret("Married Julie"));
    }
}
