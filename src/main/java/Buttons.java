import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

@XmlRootElement(name = "buttons")

public class Buttons implements RiskModelListener {

    @XmlElement(type = Object.class)
    private ArrayList<JButton> buttonList;

    /**
     * Constructor of buttons
     *
     * @add all the countries' buttons on the map
     */
    public Buttons() {
        buttonList = new ArrayList<>();
    }

    /**
     * public Buttons(JPanel imagePanel){// constructor for the original world map
     * /**buttonList = new ArrayList<>();
     * //North America buttons
     * JButton alaskaButton = new JButton("");
     * imagePanel.add(alaskaButton);
     * alaskaButton.setBounds(23,190,25,15);
     * alaskaButton.setActionCommand("alaska");
     * buttonList.add(alaskaButton);
     * <p>
     * JButton albertaButton = new JButton("");
     * imagePanel.add(albertaButton);
     * albertaButton.setBounds(100,210,25,15);
     * albertaButton.setActionCommand("alberta");
     * buttonList.add(albertaButton);
     * <p>
     * JButton centralamericaButton = new JButton("");
     * imagePanel.add(centralamericaButton);
     * centralamericaButton.setBounds(170,315,25,15);
     * centralamericaButton.setActionCommand("centralamerica");
     * buttonList.add(centralamericaButton);
     * <p>
     * JButton easternunitedstatesButton = new JButton("");
     * imagePanel.add(easternunitedstatesButton);
     * easternunitedstatesButton.setBounds(187,290,25,15);
     * easternunitedstatesButton.setActionCommand("easternunitedstates");
     * buttonList.add(easternunitedstatesButton);
     * <p>
     * JButton greenlandButton = new JButton("");
     * imagePanel.add(greenlandButton);
     * greenlandButton.setBounds(305,145,25,15);
     * greenlandButton.setActionCommand("greenland");
     * buttonList.add(greenlandButton);
     * <p>
     * JButton northwestterritoryButton = new JButton("");
     * imagePanel.add(northwestterritoryButton);
     * northwestterritoryButton.setBounds(145,167,25,15);
     * northwestterritoryButton.setActionCommand("northwestterritory");
     * buttonList.add(northwestterritoryButton);
     * <p>
     * JButton ontarioButton = new JButton("");
     * imagePanel.add(ontarioButton);
     * ontarioButton.setBounds(187,210,25,15);
     * ontarioButton.setActionCommand("ontario");
     * buttonList.add(ontarioButton);
     * <p>
     * JButton quebecButton = new JButton("");
     * imagePanel.add(quebecButton);
     * quebecButton.setBounds(260,240,25,15);
     * quebecButton.setActionCommand("quebec");
     * buttonList.add(quebecButton);
     * <p>
     * JButton westernunitedstatesButton = new JButton("");
     * imagePanel.add(westernunitedstatesButton);
     * westernunitedstatesButton.setBounds(100,275,25,15);
     * westernunitedstatesButton.setActionCommand("westernunitedstates");
     * buttonList.add(westernunitedstatesButton);
     * <p>
     * //South America Buttons
     * JButton argentinaButton = new JButton("");
     * imagePanel.add(argentinaButton);
     * argentinaButton.setBounds(193,487,25,15);
     * argentinaButton.setActionCommand("argentina");
     * buttonList.add(argentinaButton);
     * <p>
     * JButton brazilButton = new JButton("");
     * imagePanel.add(brazilButton);
     * brazilButton.setBounds(263,481,25,15);
     * brazilButton.setActionCommand("brazil");
     * buttonList.add(brazilButton);
     * <p>
     * JButton peruButton = new JButton("");
     * imagePanel.add(peruButton);
     * peruButton.setBounds(208,440,25,15);
     * peruButton.setActionCommand("peru");
     * buttonList.add(peruButton);
     * <p>
     * JButton venezuelaButton = new JButton("");
     * imagePanel.add(venezuelaButton);
     * venezuelaButton.setBounds(230,399,25,15);
     * venezuelaButton.setActionCommand("venezuela");
     * buttonList.add(venezuelaButton);
     * <p>
     * //Europe Buttons
     * JButton greatbritainButton = new JButton("");
     * imagePanel.add(greatbritainButton);
     * greatbritainButton.setBounds(370,230,25,15);
     * greatbritainButton.setActionCommand("greatbritain");
     * buttonList.add(greatbritainButton);
     * <p>
     * JButton icelandButton = new JButton("");
     * imagePanel.add(icelandButton);
     * icelandButton.setBounds(365,185,25,15);
     * icelandButton.setActionCommand("iceland");
     * buttonList.add(icelandButton);
     * <p>
     * JButton northerneuropeButton = new JButton("");
     * imagePanel.add(northerneuropeButton);
     * northerneuropeButton.setBounds(480,207,25,15);
     * northerneuropeButton.setActionCommand("northerneurope");
     * buttonList.add(northerneuropeButton);
     * <p>
     * JButton scandinaviaButton = new JButton("");
     * imagePanel.add(scandinaviaButton);
     * scandinaviaButton.setBounds(440,160,25,15);
     * scandinaviaButton.setActionCommand("scandinavia");
     * buttonList.add(scandinaviaButton);
     * <p>
     * JButton southerneuropeButton = new JButton("");
     * imagePanel.add(southerneuropeButton);
     * southerneuropeButton.setBounds(510,280,25,15);
     * southerneuropeButton.setActionCommand("southerneurope");
     * buttonList.add(southerneuropeButton);
     * <p>
     * JButton ukraineButton = new JButton("");
     * imagePanel.add(ukraineButton);
     * ukraineButton.setBounds(530,207,25,15);
     * ukraineButton.setActionCommand("ukraine");
     * buttonList.add(ukraineButton);
     * <p>
     * JButton westerneuropeButton = new JButton("");
     * imagePanel.add(westerneuropeButton);
     * westerneuropeButton.setBounds(455,250,25,15);
     * westerneuropeButton.setActionCommand("westerneurope");
     * buttonList.add(westerneuropeButton);
     * <p>
     * //Africa Buttons
     * JButton congoButton = new JButton("");
     * imagePanel.add(congoButton);
     * congoButton.setBounds(382,418,25,15);
     * congoButton.setActionCommand("congo");
     * buttonList.add(congoButton);
     * <p>
     * JButton eastafricaButton = new JButton("");
     * imagePanel.add(eastafricaButton);
     * eastafricaButton.setBounds(460,419,25,15);
     * eastafricaButton.setActionCommand("eastafrica");
     * buttonList.add(eastafricaButton);
     * <p>
     * JButton egyptButton = new JButton("");
     * imagePanel.add(egyptButton);
     * egyptButton.setBounds(450,355,25,15);
     * egyptButton.setActionCommand("egypt");
     * buttonList.add(egyptButton);
     * <p>
     * JButton madagascarButton = new JButton("");
     * imagePanel.add(madagascarButton);
     * madagascarButton.setBounds(499,521,25,15);
     * madagascarButton.setActionCommand("madagascar");
     * buttonList.add(madagascarButton);
     * <p>
     * JButton northafricaButton = new JButton("");
     * imagePanel.add(northafricaButton);
     * northafricaButton.setBounds(385,338,25,15);
     * northafricaButton.setActionCommand("northafrica");
     * buttonList.add(northafricaButton);
     * <p>
     * JButton southafricaButton = new JButton("");
     * imagePanel.add(southafricaButton);
     * southafricaButton.setBounds(380,518,25,15);
     * southafricaButton.setActionCommand("southafrica");
     * buttonList.add(southafricaButton);
     * <p>
     * //Asia Buttons
     * JButton afghanistanButton = new JButton("");
     * imagePanel.add(afghanistanButton);
     * afghanistanButton.setBounds(599,265,25,15);
     * afghanistanButton.setActionCommand("afghanistan");
     * buttonList.add(afghanistanButton);
     * <p>
     * JButton chinaButton = new JButton("");
     * imagePanel.add(chinaButton);
     * chinaButton.setBounds(630,235,25,15);
     * chinaButton.setActionCommand("china");
     * buttonList.add(chinaButton);
     * <p>
     * JButton indiaButton = new JButton("");
     * imagePanel.add(indiaButton);
     * indiaButton.setBounds(633,326,25,15);
     * indiaButton.setActionCommand("india");
     * buttonList.add(indiaButton);
     * <p>
     * JButton irkutskButton = new JButton("");
     * imagePanel.add(irkutskButton);
     * irkutskButton.setBounds(736,202,25,15);
     * irkutskButton.setActionCommand("irkutsk");
     * buttonList.add(irkutskButton);
     * <p>
     * JButton japanButton = new JButton("");
     * imagePanel.add(japanButton);
     * japanButton.setBounds(830,284,25,15);
     * japanButton.setActionCommand("japan");
     * buttonList.add(japanButton);
     * <p>
     * JButton kamchatkaButton = new JButton("");
     * imagePanel.add(kamchatkaButton);
     * kamchatkaButton.setBounds(805,185,25,15);
     * kamchatkaButton.setActionCommand("kamchatka");
     * buttonList.add(kamchatkaButton);
     * <p>
     * JButton middleeastButton = new JButton("");
     * imagePanel.add(middleeastButton);
     * middleeastButton.setBounds(583,310,25,15);
     * middleeastButton.setActionCommand("middleeast");
     * buttonList.add(middleeastButton);
     * <p>
     * JButton mongoliaButton = new JButton("");
     * imagePanel.add(mongoliaButton);
     * mongoliaButton.setBounds(758,237,25,15);
     * mongoliaButton.setActionCommand("mongolia");
     * buttonList.add(mongoliaButton);
     * <p>
     * JButton siamButton = new JButton("");
     * imagePanel.add(siamButton);
     * siamButton.setBounds(688,316,25,15);
     * siamButton.setActionCommand("siam");
     * buttonList.add(siamButton);
     * <p>
     * JButton siberiaButton = new JButton("");
     * imagePanel.add(siberiaButton);
     * siberiaButton.setBounds(640,175,25,15);
     * siberiaButton.setActionCommand("siberia");
     * buttonList.add(siberiaButton);
     * <p>
     * JButton uralButton = new JButton("");
     * imagePanel.add(uralButton);
     * uralButton.setBounds(608,129,25,15);
     * uralButton.setActionCommand("ural");
     * buttonList.add(uralButton);
     * <p>
     * JButton yakutskButton = new JButton("");
     * imagePanel.add(yakutskButton);
     * yakutskButton.setBounds(717,173,25,15);
     * yakutskButton.setActionCommand("yakutsk");
     * buttonList.add(yakutskButton);
     * <p>
     * //Australia Buttons
     * JButton easternaustraliaButton = new JButton("");
     * imagePanel.add(easternaustraliaButton);
     * easternaustraliaButton.setBounds(810,600,25,15);
     * easternaustraliaButton.setActionCommand("easternaustralia");
     * buttonList.add(easternaustraliaButton);
     * <p>
     * JButton indonesiaButton = new JButton("");
     * imagePanel.add(indonesiaButton);
     * indonesiaButton.setBounds(709,437,25,15);
     * indonesiaButton.setActionCommand("indonesia");
     * buttonList.add(indonesiaButton);
     * <p>
     * JButton newguineaButton = new JButton("");
     * imagePanel.add(newguineaButton);
     * newguineaButton.setBounds(841,447,25,15);
     * newguineaButton.setActionCommand("newguinea");
     * buttonList.add(newguineaButton);
     * <p>
     * JButton westernaustraliaButton = new JButton("");
     * imagePanel.add(westernaustraliaButton);
     * westernaustraliaButton.setBounds(721,558,25,15);
     * westernaustraliaButton.setActionCommand("westernaustralia");
     * buttonList.add(westernaustraliaButton);
     * <p>
     * imagePanel.setPreferredSize(new Dimension(900, 750));
     * <p>
     * }
     **/
    public static Buttons build(ButtonInfoStore bs, JPanel imagePanel) {
        Buttons buttons = new Buttons();
        for (ButtonInfo b : bs.getButtonInfoList()) {

            JButton button = new JButton("");
            imagePanel.add(button);
            buttons.buttonList.add(button);
            button.setActionCommand(b.getActionCommand());
            int[] lo = b.getLocation();
            button.setBounds(lo[0], lo[1], lo[2], lo[3]);
        }
        return buttons;

    }

    /**
     * get buttons from the button list
     */
    public ArrayList<JButton> getList() {
        return this.buttonList;
    }

    /**
     * update countries' situation to the system and show it at the text area
     */
    @Override
    public void handleRiskModelUpdate(RiskModelUpdateEvent updateEvent) {

        RiskModel model = (RiskModel) updateEvent.getSource();

        for (JButton b : buttonList) {
            Country c = model.getCountry(b.getActionCommand());
            b.setBackground(c.getOwner().getColor());
            b.setText(Integer.toString(model.getCountry(b.getActionCommand()).getCountryTroopsNumber()));
        }
    }
}
