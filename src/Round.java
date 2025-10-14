public class Round {
    public enum Phase { PLAYER_TURN, WAITING_FOR_CLICK, ENDED } // possible situations

    private final User player; // declares player from user.java
    private final Entity enemy; // declares enemy from entity.java
    private final BasicAttack attack = new BasicAttack(); //initialises basic attack;
    private final Heal heal = new Heal();
    private Phase phase = Phase.PLAYER_TURN; //sets initial phase of round to be the user's turn

    public Round(User player, Entity enemy) { // round constructor 
        this.player = player;
        this.enemy = enemy;
    }

    public boolean isOver() { //determines whether round is over (true) or still going (false)
        return phase == Phase.ENDED || player.getHealth() <= 0 || enemy.getHealth() <= 0;
    }

    public Phase getPhase() { //returns phase 
        return phase;
    }

    public void onPlayerClickAttack() { //attack method when attack button clicked
        if (isOver()) return; //if the round is over, attacking does not execute
        if (phase != Phase.PLAYER_TURN) return;  //if it is not the player's turn, attack does not execute

        attack.execute(player, enemy); //executes attack, ending the round if the enemies health reaches 0
        if (enemy.getHealth() <= 0) {
            phase = Phase.ENDED;
            return;
        }

        phase = Phase.WAITING_FOR_CLICK; //it becomes the enemies turn, same logic as above
        enemyAutoAttack(); 
        if (player.getHealth() <= 0) {
            phase = Phase.ENDED;
            return;
        }

        phase = Phase.PLAYER_TURN; //it becomes the user's turn again
    }

    public void onPlayerClickHeal(){
        if (isOver()) return; //if the round is over, healing does not execute
        if (phase != Phase.PLAYER_TURN) return;  //if it is not the player's turn, healing does not execute

        heal.execute(player, enemy); //executes attack, ending the round if the enemies health reaches 0
        if (enemy.getHealth() <= 0) {
            return;
        }

        phase = Phase.WAITING_FOR_CLICK; //it becomes the enemies turn, same logic as above
        enemyAutoAttack(); 
        if (player.getHealth() <= 0) {
            phase = Phase.ENDED;
            return;
        }

        phase = Phase.PLAYER_TURN; //it becomes the user's turn again
    
    }


    private void enemyAutoAttack() { //attack executed by the enemy
        attack.execute(enemy, player);
    }

    //getters for entities

    public int getPlayerHp() { return player.getHealth(); }
    public int getEnemyHp()  { return enemy.getHealth(); }
    public String getPlayerName() { return player.getName(); }
    public String getEnemyName()  { return enemy.getName(); }
}
