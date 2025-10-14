public class Round {
    public enum Phase { PLAYER_TURN, WAITING_FOR_CLICK, ENDED }

    private final User player;
    private final Entity enemy;
    private final BasicAttack attack = new BasicAttack();
    private Phase phase = Phase.PLAYER_TURN;

    public Round(User player, Entity enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public boolean isOver() {
        return phase == Phase.ENDED || player.getHealth() <= 0 || enemy.getHealth() <= 0;
    }

    public Phase getPhase() {
        return phase;
    }

    public void onPlayerClickAttack() {
        if (isOver()) return;
        if (phase != Phase.PLAYER_TURN) return; 

        attack.execute(player, enemy);
        if (enemy.getHealth() <= 0) {
            phase = Phase.ENDED;
            return;
        }

        phase = Phase.WAITING_FOR_CLICK;
        enemyAutoAttack();
        if (player.getHealth() <= 0) {
            phase = Phase.ENDED;
            return;
        }

        phase = Phase.PLAYER_TURN;
    }

    private void enemyAutoAttack() {
        attack.execute(enemy, player);
    }

    public int getPlayerHp() { return player.getHealth(); }
    public int getEnemyHp()  { return enemy.getHealth(); }
    public String getPlayerName() { return player.getName(); }
    public String getEnemyName()  { return enemy.getName(); }
}
