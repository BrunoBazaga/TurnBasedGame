import javax.swing.SwingUtilities;

public class Round {

    public enum Phase { PLAYER_TURN, WAITING_FOR_CLICK, ENDED } //enums for all possible phases of a round

    public interface Listener {
        void onBattleEnded(boolean playerWon); //listener interface
    }

    private final User player;  //initialisation of entities and abilities
    private final Entity enemy;
    private final BasicAttack attack = new BasicAttack();
    private final Heal heal = new Heal();

    private Phase phase = Phase.PLAYER_TURN; //sets phase to be the players turn.
    private CombatUI combatUI;
    private Listener listener;

    public Round(User player, Entity enemy, CombatUI combatUI) { //round constructor
        this.player = player;
        this.enemy = enemy;
        this.combatUI = combatUI;
    }

    public void attachUI(CombatUI ui) { this.combatUI = ui; } //attaches UI to round
    public void setListener(Listener l) { this.listener = l; } //initialises listener

    public boolean isOver() {
        return phase == Phase.ENDED || player.getHealth() <= 0 || enemy.getHealth() <= 0; //returns isOver as true if player or enemy health is equal to 0 or if phase = ended
    }

    public Phase getPhase() { return phase; } 

    // player actions
    public void onPlayerClickAttack() { //executes when Attack button is clicked
        if (isOver() || phase != Phase.PLAYER_TURN) {
            return; //if the round is over or it is not the players turn, method returns nothing.
        } else {
            combatUI.setButtonsEnabled(false); //disables buttons to prevent multiple clicks
        }

        if (attack.missed()) {
            pushMessage(player.getName() + "'s attack missed!");
        } else {
            int dmg = attack.execute(player, enemy);
            pushEnemyHP();
            pushMessage(player.getName() + " attacked " + enemy.getName() + " for " + dmg + " damage!" + attack.critMesssage());
        }

        new javax.swing.Timer(1500, e -> {
            if (enemy.getHealth() <= 0) {
                phase = Phase.ENDED;
                pushMessage(enemy.getName() + " is defeated!");
                notifyEnded(true);
                combatUI.setButtonsEnabled(true);
                ((javax.swing.Timer) e.getSource()).stop();
                return;
            }

            phase = Phase.WAITING_FOR_CLICK;
            enemyAutoAttack();
            if (player.getHealth() <= 0) {
                phase = Phase.ENDED;
                pushMessage(player.getName() + " is defeated!");
                notifyEnded(false);
                combatUI.setButtonsEnabled(true);
                ((javax.swing.Timer) e.getSource()).stop();
                return;
            }
            phase = Phase.PLAYER_TURN;

            combatUI.setButtonsEnabled(true);
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }

    public void onPlayerClickHeal() { //healing button
        if (isOver() || phase != Phase.PLAYER_TURN) {
            return;
        } else {
            combatUI.setButtonsEnabled(false); //disables buttons to prevent multiple clicks
        }

        heal.execute(player);
        pushPlayerHP();
        pushMessage(player.getName() + " healed!");

        new javax.swing.Timer(1500, ev -> {
            phase = Phase.WAITING_FOR_CLICK;
            enemyAutoAttack();
            if (player.getHealth() <= 0) {
                phase = Phase.ENDED;
                pushMessage(player.getName() + " is defeated!");
                notifyEnded(false);
                combatUI.setButtonsEnabled(true);
                ((javax.swing.Timer) ev.getSource()).stop();
                return;
            }
            phase = Phase.PLAYER_TURN;

            combatUI.setButtonsEnabled(true);
            ((javax.swing.Timer) ev.getSource()).stop();
        }).start();
    }

    private void enemyAutoAttack() { //enemy attack
        if (attack.missed()) {
            pushMessage(enemy.getName() + "'s attack missed!");
        } else {
            int dmg = attack.execute(enemy, player);
            pushPlayerHP();
            pushMessage(enemy.getName() + " attacked for " + dmg + " damage!" + attack.critMesssage());
        }
    }

    private void notifyEnded(boolean playerWon) {
        if (listener != null)
            SwingUtilities.invokeLater(() -> listener.onBattleEnded(playerWon));
    }

    //UI updaters
    private void pushPlayerHP() { //updates Player HP in UI
        if (combatUI == null) return;
        int hp = Math.max(0, player.getHealth());
        SwingUtilities.invokeLater(() -> combatUI.setPlayerHP(hp));
    }

    private void pushEnemyHP() { //updates enemy HP in UI
        if (combatUI == null) return;
        int hp = Math.max(0, enemy.getHealth());
        SwingUtilities.invokeLater(() -> combatUI.setEnemyHP(hp));
    }

    private void pushMessage(String text) { //method to display messages in message box
        if (combatUI == null) return;
        SwingUtilities.invokeLater(() -> combatUI.setMessage(text));
    }

    // getters for entities
    public User getPlayer() { return player; }
    public Entity getEnemy() { return enemy; }
    public String getPlayerName() { return player.getName(); }
    public String getEnemyName() { return enemy.getName(); }
    public int getPlayerHp() { return player.getHealth(); }
    public int getEnemyHp() { return enemy.getHealth(); }
    public String getEnemySpritePath() { return "src/images/" + enemy.getName().toLowerCase() + ".png"; }
}
