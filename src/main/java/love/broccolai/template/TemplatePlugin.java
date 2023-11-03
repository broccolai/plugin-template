package love.broccolai.template;

import org.bukkit.plugin.java.JavaPlugin;

public final class TemplatePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getDataFolder().mkdirs();
    }

}
