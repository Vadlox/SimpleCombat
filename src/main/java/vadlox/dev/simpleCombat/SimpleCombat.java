package vadlox.dev.simpleCombat;

import org.bukkit.plugin.java.JavaPlugin;
import vadlox.dev.simpleCombat.combat.CombatManager;
import vadlox.dev.simpleCombat.listeners.CombatListener;
import vadlox.dev.simpleCombat.listeners.CommandListener;
import vadlox.dev.simpleCombat.scoreboard.ScoreboardManager;

public final class SimpleCombat extends JavaPlugin {
    private CombatManager combatManager;
    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        // Save default config if it doesn't exist
        saveDefaultConfig();

        // Initialize managers
        combatManager = new CombatManager(this);
        scoreboardManager = new ScoreboardManager(this);

        // Register event listeners
        getServer().getPluginManager().registerEvents(new CombatListener(this), this);
        getServer().getPluginManager().registerEvents(new CombatListener(this, combatManager), this);
        getServer().getPluginManager().registerEvents(new CommandListener(this, combatManager), this);

        getLogger().info("SimpleCombat has been enabled!");
    }

    @Override
    public void onDisable() {
        // Cleanup managers
        if (combatManager != null) {
            combatManager.shutdown();
        }
        if (scoreboardManager != null) {
            scoreboardManager.shutdown();
        }

        getLogger().info("SimpleCombat has been disabled!");
    }

    public CombatManager getCombatManager() {
        return combatManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
}
