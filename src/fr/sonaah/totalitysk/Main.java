package fr.sonaah.totalitysk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        SkriptAddon addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("fr.sonaah.totalitysk.skript");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
