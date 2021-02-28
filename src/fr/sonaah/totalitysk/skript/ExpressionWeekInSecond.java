package fr.sonaah.totalitysk.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

public class ExpressionWeekInSecond extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExpressionWeekInSecond.class, Number.class, ExpressionType.SIMPLE,"[totalitysk] %number% week in second");
    }

    private Expression<Number> exprInt;

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int pattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        exprInt = (Expression<Number>) exprs[0];
        return true;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "number week in second with number " + exprInt.toString(e, debug);
    }

    @Override
    protected Number[] get(Event e) {
        Number number = exprInt.getSingle(e);
        if(number == null) return new Number[0];
        Number result = (((number.intValue() * 7) * 24) * 60) * 60;
        return new Number[] { result };
    }
}