import javax.swing.*;
import java.util.List;

public class Risk_Controller {
    private Risk_Model model;
    private RiskView view;
    private Player playerOnGoing;
    private List<Player> players;
    private int playerIndex;
    private int numOfPlayer;
    private boolean finished;

    public Risk_Controller(Risk_Model model, RiskView view){
        this.model = model;
        this.view = view;
        numOfPlayer = model.getNumOfPlayer();
        playerIndex = 0;
        finished = false;
        model.updateList(playerOnGoing);
        view.getCountriesOwnList().setModel(model.getList());

        view.getAdjacentCountriesList().setModel(model.getList());
        view.getCountriesOwnText().setText(model.getPlayerOnGoing().getName()+" Owns these Countries: ");
    }

    public void play(){
        createPlayer();
        playerOnGoing = players.get(playerIndex % model.getNumOfPlayer());
        while(!hasWinner() || !finished){

        }
    }

    /**
     * create instances of all Player and add them into Arraylist players.
     */
    private void createPlayer() {
        for (int i = 0; i < model.getNumOfPlayer(); i++) {
            players.add(new Player("Player" + i));
        }
    }

    /**
     * Method remove player with no more country from the players arraylist
     */
    public void removePlayerWithNoCountry() {
        Player beRemovedPlayer = new Player("impossible");
        for (Player p : players) {
            try {

                if (p.getCountriesOwn().isEmpty()) {

                    numOfPlayer--;
                    beRemovedPlayer = p;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        players.remove(beRemovedPlayer);
        if (players.size() == 1) {
            System.out.println("The winner is " + players.get(0).getName());
        }
    }

    /**
     *Method tells us if the game has a winner
     * @return boolean return true if there's winner in the game, false if there are no winner yet
     */
    public boolean hasWinner() {
        return players.size() <= 1;
    }

    public static void main(String[] args){
        Risk_Model riskModel = new Risk_Model();
        RiskView view = new RiskView();


        Risk_Controller Risk_Controller = new Risk_Controller(riskModel, view);

    }
}
