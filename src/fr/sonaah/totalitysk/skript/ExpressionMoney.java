package fr.sonaah.totalitysk.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;

public class ExpressionMoney extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExpressionMoney.class, String.class, ExpressionType.SIMPLE,"[totalitysk] %number% money format");
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
        return "number money format with number " + exprInt.toString(e, debug);
    }

    @Override
    protected String[] get(Event e) {
        Number number = exprInt.getSingle(e);
        if(number == null) return new String[0]; //number.intValue()
        if(number.intValue() < 1000) {
            return new String[] { String.valueOf(number) };
        } else {
            String t = String.valueOf(number.intValue());
            int size = t.length();
            List<String> list = new ArrayList<>();
            for(int index = size - 1; index >= 0; index--) {
                list.add(String.valueOf(t.charAt(index)));
            }
            if(number.intValue() >= 1000 && number.intValue() < 1000000) {
                int var = 2;
                while(var > 0) {
                    list.remove(0);
                    var = var - 1;
                }
                list.set(0, "." + list.get(0));
                String text = "";
                for(int a = list.size() - 1; a >= 0; a--) {
                    text = text + list.get(a);
                }
                return new String[] { text + "k" };
            } else {
                int var = 5;
                while(var > 0) {
                    list.remove(0);
                    var = var - 1;
                }
                list.set(0, "." + list.get(0));
                String text = "";
                for(int a = list.size() - 1; a >= 0; a--) {
                    text = text + list.get(a);
                }
                System.out.println(text);
                return new String[] { text + "m" };
            }
        }
    }
}