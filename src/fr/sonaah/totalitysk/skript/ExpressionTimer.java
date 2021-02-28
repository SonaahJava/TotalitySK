package fr.sonaah.totalitysk.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import java.text.SimpleDateFormat;

public class ExpressionTimer extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExpressionTimer.class, String.class, ExpressionType.SIMPLE,"[totalitysk] %number% timer format");
    }

    private Expression<Number> exprInt;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
        return "number timer format with number " + exprInt.toString(e, debug);
    }

    @Override
    protected String[] get(Event e) {
        Number number = exprInt.getSingle(e);
        if(number == null) return new String[0];
        String timer = (new SimpleDateFormat("mm:ss")).format(number.intValue() * 1000);
        return new String[] { timer };
    }
}
