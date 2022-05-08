package us.teaminceptus.games;

class Commands {

    protected InceptusGames plugin;
    
    public Commands(InceptusGames plugin) {
        this.plugin = plugin;
        plugin.handler.register(this);
    }

}
