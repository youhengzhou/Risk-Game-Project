import java.io.FileNotFoundException;

public class MapListener extends CustomPanel implements RiskModelListener {

    private String gameMapImagePath;

    /**
     * Constructor of MapListener
     * set the game map to a custom png image
     */
    public MapListener() throws FileNotFoundException {
        super();
    }

    /**
     * upload the situation of the adjacent countries at the text area
     */
    @Override
    public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {
        RiskModel model = (RiskModel)updateEvent.getSource();
        this.gameMapImagePath = model.getGameMapImagePath();
    }
}
