public class Round {
    public enum Phase { PLAYER_TURN, WAITING_FOR_CLICK, ENDED }

    private final User player;
    private final Entity enemy;
    private final BasicAttack attack = new BasicAttack();
    private final Heal heal = new Heal();

    private Phase phase = Phase.PLAYER_TURN;

    // UI is attached after construction
    private CombatUI combatUI; // may be null until GameFrame calls attachUI()

    public Round(User player, Entity enemy, CombatUI combatUI) {
        this.player = player;
        this.enemy  = enemy;
        this.combatUI = combatUI; // can be null initially
    }

    /** Attach the UI once it has been constructed so Round can push updates safely. */
    public void attachUI(CombatUI ui) {
        this.combatUI = ui;
    }

    public boolean isOver() {
        return phase == Phase.ENDED || player.getHealth() <= 0 || enemy.getHealth() <= 0;
    }

    public Phase getPhase() {
        return phase;
    }

    public void onPlayerClickAttack() {
        // quick debug (optional)
        // System.out.println("[Round] isOver=" + isOver() + " P=" + player.getHealth() + " E=" + enemy.getHealth() + " phase=" + phase);

        if (isOver() || phase != Phase.PLAYER_TURN) return;

        int damageToEnemy = attack.execute(player, enemy);
        pushEnemyHP();
        pushMessage(player.getName() + " used Basic Attack on " + enemy.getName() + " for " + damageToEnemy + " damage!");

        if (enemy.getHealth() <= 0) {
            phase = Phase.ENDED;
            pushMessage(enemy.getName() + " is defeated!");
            return;
        }

        phase = Phase.WAITING_FOR_CLICK;
        enemyAutoAttack();

        if (player.getHealth() <= 0) {
            phase = Phase.ENDED;
            pushMessage(player.getName() + " is defeated!");
            return;
        }

        phase = Phase.PLAYER_TURN;
    }

    public void onPlayerClickHeal() {
        if (isOver() || phase != Phase.PLAYER_TURN) return;

        heal.execute(player);
        pushPlayerHP();
        pushMessage(player.getName() + " healed!");

        phase = Phase.WAITING_FOR_CLICK;
        enemyAutoAttack();

        if (player.getHealth() <= 0) {
            phase = Phase.ENDED;
            pushMessage(player.getName() + " is defeated!");
            return;
        }

        phase = Phase.PLAYER_TURN;
    }

    private void enemyAutoAttack() {
        int damageToPlayer = attack.execute(enemy, player);
        pushPlayerHP();
        pushMessage(enemy.getName() + " attacked for " + damageToPlayer + " damage!");
    }

    // ---- Push helpers (no-ops if UI not attached yet) ----
    private void pushPlayerHP() {
        if (combatUI != null) combatUI.setPlayerHP(player.getHealth());
    }

    private void pushEnemyHP() {
        if (combatUI != null) combatUI.setEnemyHP(enemy.getHealth());
    }

    private void pushMessage(String text) {
        if (combatUI != null) combatUI.setMessage(text);
    }

    // ---- Expose info to UI if needed ----
    public User getPlayer()  { return player; }
    public Entity getEnemy() { return enemy; }
    public String getPlayerName() { return player.getName(); }
    public String getEnemyName()  { return enemy.getName(); }
    public int getPlayerHp()      { return player.getHealth(); }
    public int getEnemyHp()       { return enemy.getHealth(); }
}
