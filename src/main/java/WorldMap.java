import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@XmlRootElement(name = "originalWorldMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorldMap {

    public HashMap<String,Country> map;
    @XmlElement(name = "America")
    private ArrayList<Country> northAmericaContient;
    @XmlElement(name = "SouthAmerica")
    private ArrayList<Country> southAmericaContient;
    @XmlElement(name = "Europe")
    private ArrayList<Country> europeContient;
    @XmlElement(name = "Africa")
    private ArrayList<Country> africaContient;
    @XmlElement(name = "Asia")
    private ArrayList<Country> asiaContient;
    @XmlElement(name = "Australia")
    private ArrayList<Country> australiaContient;


    public HashMap<String, Country> getMap() {
        return map;
    }

    public WorldMap() //constructor for star map
    {
        northAmericaContient = new ArrayList<>();
        southAmericaContient = new ArrayList<>();
        europeContient = new ArrayList<>();
        africaContient = new ArrayList<>();
        asiaContient = new ArrayList<>();
        australiaContient = new ArrayList<>();

        map = new HashMap<>();
        Country Russia = new Country("Russia");
        Russia.addAdjacentCountry("france");
        Russia.addAdjacentCountry("switzerland");
        map.put("russia",Russia);

        Country France = new Country("France");
        France.addAdjacentCountry("russia");
        France.addAdjacentCountry("german");
        France.addAdjacentCountry("belgium");
        map.put("france",France);


        Country German = new Country("German");
        German.addAdjacentCountry("france");
        German.addAdjacentCountry("poland");
        map.put("german",German);


        Country Switzerland = new Country("Switzerland");
        Switzerland.addAdjacentCountry("russia");
        Switzerland.addAdjacentCountry("belgium");
        Switzerland.addAdjacentCountry("sweden");
        map.put("switzerland",Switzerland);

        Country Belgium = new Country("Belgium");
        Belgium.addAdjacentCountry("france");
        Belgium.addAdjacentCountry("switzerland");
        Belgium.addAdjacentCountry("poland");
        Belgium.addAdjacentCountry("norway");
        Belgium.addAdjacentCountry("denmark");
        map.put("belgium",Belgium);


        Country Poland = new Country("Poland");
        Poland.addAdjacentCountry("german");
        Poland.addAdjacentCountry("belgium");
        Poland.addAdjacentCountry("italy");
        map.put("poland",Poland);

        Country Sweden = new Country("Sweden");
        Sweden.addAdjacentCountry("switzerland");
        Sweden.addAdjacentCountry("norway");
        map.put("sweden",Sweden);

        Country Norway = new Country("Norway");
        Norway.addAdjacentCountry("sweden");
        Norway.addAdjacentCountry("belgium");
        Norway.addAdjacentCountry("finland");
        map.put("norway",Norway);


        Country Denmark = new Country("Denmark");
        Denmark.addAdjacentCountry("belgium");
        Denmark.addAdjacentCountry("italy");
        Denmark.addAdjacentCountry("finland");
        map.put("denmark",Denmark);


        Country Italy = new Country("Italy");
        Italy.addAdjacentCountry("poland");
        Italy.addAdjacentCountry("denmark");
        map.put("italy",Italy);

        Country Finland = new Country("Finland");
        Finland.addAdjacentCountry("norway");
        Finland.addAdjacentCountry("denmark");
        map.put("finland",Finland);


    }
/**
    public WorldMap(){ // constructor for original map

        northAmericaContient = new ArrayList<>();
        southAmericaContient = new ArrayList<>();
        europeContient = new ArrayList<>();
        africaContient = new ArrayList<>();
        asiaContient = new ArrayList<>();
        australiaContient = new ArrayList<>();


        map = new HashMap<>();
        //North America
        Country Alaska = new Country("Alaska");
        Country Alberta = new Country("Alberta");
        Country CentralAmerica = new Country("Central America");
        Country EasternUnitedStates = new Country("Eastern United States");
        Country Greenland = new Country("Greenland");
        Country NorthwestTerritory = new Country("Northwest Territory");
        Country Ontario = new Country("Ontario");
        Country Quebec = new Country("Quebec");
        Country WesternUnitedStates = new Country("Western United States");


        northAmericaContient.add(Alaska);
        northAmericaContient.add(Alberta);
        northAmericaContient.add(CentralAmerica);
        northAmericaContient.add(EasternUnitedStates);
        northAmericaContient.add(NorthwestTerritory);
        northAmericaContient.add(Greenland);
        northAmericaContient.add(Ontario);
        northAmericaContient.add(Quebec);
        northAmericaContient.add(WesternUnitedStates);




        //South America
        Country Argentina = new Country("Argentina");
        Country Brazil = new Country("Brazil");
        Country Peru = new Country("Peru");
        Country Venezuela = new Country("Venezuela");

        southAmericaContient.add(Argentina);
        southAmericaContient.add(Brazil);
        southAmericaContient.add(Peru);
        southAmericaContient.add(Venezuela);


        //Europe
        Country GreatBritain = new Country("Great Britain");
        Country Iceland = new Country("Iceland");
        Country NorthernEurope = new Country("Northern Europe");
        Country Scandinavia = new Country("Scandinavia");
        Country SouthernEurope = new Country("Southern Europe");
        Country Ukraine = new Country("Ukraine");
        Country WesternEurope = new Country("Western Europe");

        europeContient.add(GreatBritain);
        europeContient.add(Iceland);
        europeContient.add(NorthernEurope);
        europeContient.add(Scandinavia);
        europeContient.add(SouthernEurope);
        europeContient.add(Ukraine);
        europeContient.add(WesternEurope);



        //Africa
        Country Congo = new Country("Congo");
        Country EastAfrica = new Country("East Africa");
        Country Egypt = new Country("Egypt");
        Country Madagascar = new Country("Madagascar");
        Country NorthAfrica = new Country("North Africa");
        Country SouthAfrica = new Country("South Africa");

        africaContient.add(Congo);
        africaContient.add(EastAfrica);
        africaContient.add(Egypt);
        africaContient.add(Madagascar);
        africaContient.add(NorthAfrica);
        africaContient.add(SouthAfrica);

        //Asia
        Country Afghanistan = new Country("Afghanistan");
        Country China = new Country("China");
        Country India = new Country("India");
        Country Irkutsk = new Country("Irkutsk");
        Country Japan = new Country("Japan");
        Country Kamchatka = new Country("Kamchatka");
        Country MiddleEast = new Country("Middle East");
        Country Mongolia = new Country("Mongolia");
        Country Siam = new Country("Siam");
        Country Siberia = new Country("Siberia");
        Country Ural = new Country("Ural");
        Country Yakutsk = new Country("Yakutsk");

        asiaContient.add(Afghanistan);
        asiaContient.add(China);
        asiaContient.add(India);
        asiaContient.add(Irkutsk);
        asiaContient.add(Japan);
        asiaContient.add(Kamchatka);
        asiaContient.add(MiddleEast);
        asiaContient.add(Mongolia);
        asiaContient.add(Siam);
        asiaContient.add(Siberia);
        asiaContient.add(Ural);
        asiaContient.add(Yakutsk);




        //Australia
        Country EasternAustralia = new Country("Eastern Australia");
        Country Indonesia = new Country("Indonesia");
        Country NewGuinea = new Country("New Guinea");
        Country WesternAustralia = new Country("Western Australia");

        australiaContient.add(EasternAustralia);
        australiaContient.add(Indonesia);
        australiaContient.add(NewGuinea);
        australiaContient.add(WesternAustralia);


        //Add adjacent Countries
        //North America
        ///1
        Alaska.addAdjacentCountry("Kamchatka".toLowerCase());
        Alaska.addAdjacentCountry("Alberta".toLowerCase());
        Alaska.addAdjacentCountry("NorthwestTerritory".toLowerCase());
        //2
        Alberta.addAdjacentCountry("Alaska".toLowerCase());
        Alberta.addAdjacentCountry("NorthwestTerritory".toLowerCase());
        Alberta.addAdjacentCountry("ontario");
        Alberta.addAdjacentCountry("westernunitedstates");
        CentralAmerica.addAdjacentCountry("easternunitedstates");
        CentralAmerica.addAdjacentCountry("westernunitedstates");
        CentralAmerica.addAdjacentCountry("venezuela");
        EasternUnitedStates.addAdjacentCountry("centralamerica");
        EasternUnitedStates.addAdjacentCountry("ontario");
        EasternUnitedStates.addAdjacentCountry("quebec");
        EasternUnitedStates.addAdjacentCountry("westernunitedstates");
        EasternUnitedStates.addAdjacentCountry("venezuela");
        Greenland.addAdjacentCountry("northwestterritory");
        Greenland.addAdjacentCountry("ontario");
        Greenland.addAdjacentCountry("quebec");
        Greenland.addAdjacentCountry("iceland");
        NorthwestTerritory.addAdjacentCountry("alaska");
        NorthwestTerritory.addAdjacentCountry("alberta");
        NorthwestTerritory.addAdjacentCountry("ontario");
        NorthwestTerritory.addAdjacentCountry("greenland");
        Ontario.addAdjacentCountry("alberta");
        Ontario.addAdjacentCountry("easternunitedstates");
        Ontario.addAdjacentCountry("greenland");
        Ontario.addAdjacentCountry("northwestterritory");
        Ontario.addAdjacentCountry("quebec");
        Ontario.addAdjacentCountry("westernunitedstates");
        Quebec.addAdjacentCountry("easternunitedstates");
        Quebec.addAdjacentCountry("greenland");
        Quebec.addAdjacentCountry("ontario");
        WesternUnitedStates.addAdjacentCountry("alberta");
        WesternUnitedStates.addAdjacentCountry("centralamerica");
        WesternUnitedStates.addAdjacentCountry("easternunitedstates");
        WesternUnitedStates.addAdjacentCountry("ontario");

        Argentina.addAdjacentCountry("brazil");
        Argentina.addAdjacentCountry("peru");
        Brazil.addAdjacentCountry("argentina");
        Brazil.addAdjacentCountry("peru");
        Brazil.addAdjacentCountry("venezuela");
        Brazil.addAdjacentCountry("northafrica");
        Peru.addAdjacentCountry("argentina");
        Peru.addAdjacentCountry("brazil");
        Peru.addAdjacentCountry("venezuela");
        Venezuela.addAdjacentCountry("brazil");
        Venezuela.addAdjacentCountry("peru");
        Venezuela.addAdjacentCountry("centralamerica");

        GreatBritain.addAdjacentCountry("iceland");
        GreatBritain.addAdjacentCountry("northerneurope");
        GreatBritain.addAdjacentCountry("scandinavia");
        GreatBritain.addAdjacentCountry("westerneurope");
        Iceland.addAdjacentCountry("greatbritain");
        Iceland.addAdjacentCountry("scandinavia");
        Iceland.addAdjacentCountry("greenland");
        NorthernEurope.addAdjacentCountry("greatbritain");
        NorthernEurope.addAdjacentCountry("scandinavia");
        NorthernEurope.addAdjacentCountry("southerneurope");
        NorthernEurope.addAdjacentCountry("ukraine");
        NorthernEurope.addAdjacentCountry("westerneurope");
        Scandinavia.addAdjacentCountry("greatbritain");
        Scandinavia.addAdjacentCountry("iceland");
        Scandinavia.addAdjacentCountry("northerneurope");
        Scandinavia.addAdjacentCountry("ukraine");
        SouthernEurope.addAdjacentCountry("northerneurope");
        SouthernEurope.addAdjacentCountry("ukraine");
        SouthernEurope.addAdjacentCountry("westerneurope");
        SouthernEurope.addAdjacentCountry("egypt");
        SouthernEurope.addAdjacentCountry("northafrica");
        SouthernEurope.addAdjacentCountry("middleeast");
        Ukraine.addAdjacentCountry("northerneurope");
        Ukraine.addAdjacentCountry("scandinavia");
        Ukraine.addAdjacentCountry("southerneurope");
        Ukraine.addAdjacentCountry("afghanistan");
        Ukraine.addAdjacentCountry("middleeast");
        Ukraine.addAdjacentCountry("ural");
        WesternEurope.addAdjacentCountry("greatbritain");
        WesternEurope.addAdjacentCountry("northerneurope");
        WesternEurope.addAdjacentCountry("southerneurope");
        WesternEurope.addAdjacentCountry("northafrica");

        Congo.addAdjacentCountry("eastafrica");
        Congo.addAdjacentCountry("northafrica");
        Congo.addAdjacentCountry("southafrica");
        EastAfrica.addAdjacentCountry("congo");
        EastAfrica.addAdjacentCountry("egypt");
        EastAfrica.addAdjacentCountry("madagascar");
        EastAfrica.addAdjacentCountry("northafrica");
        EastAfrica.addAdjacentCountry("southafrica");
        EastAfrica.addAdjacentCountry("middleeast");
        Egypt.addAdjacentCountry("eastafrica");
        Egypt.addAdjacentCountry("northafrica");
        Egypt.addAdjacentCountry("southerneurope");
        Egypt.addAdjacentCountry("middleeast");
        Madagascar.addAdjacentCountry("eastafrica");
        Madagascar.addAdjacentCountry("southafrica");
        NorthAfrica.addAdjacentCountry("congo");
        NorthAfrica.addAdjacentCountry("eastafrica");
        NorthAfrica.addAdjacentCountry("egypt");
        NorthAfrica.addAdjacentCountry("southerneurope");
        NorthAfrica.addAdjacentCountry("westerneurope");
        NorthAfrica.addAdjacentCountry("brazil");
        SouthAfrica.addAdjacentCountry("congo");
        SouthAfrica.addAdjacentCountry("eastafrica");
        SouthAfrica.addAdjacentCountry("madagascar");

        Afghanistan.addAdjacentCountry("china");
        Afghanistan.addAdjacentCountry("india");
        Afghanistan.addAdjacentCountry("middleeast");
        Afghanistan.addAdjacentCountry("ural");
        Afghanistan.addAdjacentCountry("ukraine");
        China.addAdjacentCountry("afghanistan");
        China.addAdjacentCountry("india");
        China.addAdjacentCountry("mongolia");
        China.addAdjacentCountry("siam");
        China.addAdjacentCountry("siberia");
        China.addAdjacentCountry("ural");
        India.addAdjacentCountry("afghanistan");
        India.addAdjacentCountry("china");
        India.addAdjacentCountry("middleeast");
        India.addAdjacentCountry("siam");
        Irkutsk.addAdjacentCountry("kamchatka");
        Irkutsk.addAdjacentCountry("mongolia");
        Irkutsk.addAdjacentCountry("siberia");
        Irkutsk.addAdjacentCountry("yakutsk");
        Japan.addAdjacentCountry("kamchatka");
        Japan.addAdjacentCountry("mongolia");
        Kamchatka.addAdjacentCountry("irkutsk");
        Kamchatka.addAdjacentCountry("japan");
        Kamchatka.addAdjacentCountry("mongolia");
        Kamchatka.addAdjacentCountry("yakutsk");
        Kamchatka.addAdjacentCountry("alaska");
        MiddleEast.addAdjacentCountry("afghanistan");
        MiddleEast.addAdjacentCountry("india");
        MiddleEast.addAdjacentCountry("southerneurope");
        MiddleEast.addAdjacentCountry("ukraine");
        MiddleEast.addAdjacentCountry("eastafrica");
        MiddleEast.addAdjacentCountry("egypt");
        Mongolia.addAdjacentCountry("china");
        Mongolia.addAdjacentCountry("irkutsk");
        Mongolia.addAdjacentCountry("japan");
        Mongolia.addAdjacentCountry("kamchatka");
        Mongolia.addAdjacentCountry("siberia");
        Siam.addAdjacentCountry("china");
        Siam.addAdjacentCountry("india");
        Siam.addAdjacentCountry("indonesia");
        Siberia.addAdjacentCountry("china");
        Siberia.addAdjacentCountry("irkutsk");
        Siberia.addAdjacentCountry("mongolia");
        Siberia.addAdjacentCountry("ural");
        Siberia.addAdjacentCountry("yakutsk");
        Ural.addAdjacentCountry("yakutsk");
        Ural.addAdjacentCountry("siberia");
        Ural.addAdjacentCountry("ukraine");
        Ural.addAdjacentCountry("china");
        Yakutsk.addAdjacentCountry("irkutsk");
        Yakutsk.addAdjacentCountry("kamchatka");
        Yakutsk.addAdjacentCountry("siberia");

        EasternAustralia.addAdjacentCountry("newguinea");
        EasternAustralia.addAdjacentCountry("westernaustralia");
        Indonesia.addAdjacentCountry("newguinea");
        Indonesia.addAdjacentCountry("westernaustralia");
        Indonesia.addAdjacentCountry("siam");
        NewGuinea.addAdjacentCountry("easternaustralia");
        NewGuinea.addAdjacentCountry("indonesia");
        NewGuinea.addAdjacentCountry("westernaustralia");
        WesternAustralia.addAdjacentCountry("easternaustralia");
        WesternAustralia.addAdjacentCountry("indonesia");
        WesternAustralia.addAdjacentCountry("newguinea");


        map.put("alaska", Alaska);
        map.put("alberta", Alberta);
        map.put("centralamerica", CentralAmerica);
        map.put("easternunitedstates", EasternUnitedStates);
        map.put("greenland", Greenland);
        map.put("northwestterritory", NorthwestTerritory);
        map.put("ontario", Ontario);
        map.put("quebec", Quebec);
        map.put("westernunitedstates", WesternUnitedStates);

        map.put("argentina", Argentina);
        map.put("brazil", Brazil);
        map.put("peru", Peru);
        map.put("venezuela", Venezuela);

        map.put("greatbritain", GreatBritain);
        map.put("iceland", Iceland);
        map.put("northerneurope", NorthernEurope);
        map.put("scandinavia", Scandinavia);
        map.put("southerneurope", SouthernEurope);
        map.put("ukraine", Ukraine);
        map.put("westerneurope", WesternEurope);

        map.put("congo", Congo);
        map.put("eastafrica", EastAfrica);
        map.put("egypt", Egypt);
        map.put("madagascar", Madagascar);
        map.put("northafrica", NorthAfrica);
        map.put("southafrica", SouthAfrica);

        map.put("afghanistan", Afghanistan);
        map.put("china", China);
        map.put("india", India);
        map.put("irkutsk", Irkutsk);
        map.put("japan", Japan);
        map.put("kamchatka", Kamchatka);
        map.put("middleeast", MiddleEast);
        map.put("mongolia", Mongolia);
        map.put("siam", Siam);
        map.put("siberia", Siberia);
        map.put("ural", Ural);
        map.put("yakutsk", Yakutsk);

        map.put("easternaustralia", EasternAustralia);
        map.put("indonesia", Indonesia);
        map.put("newguinea", NewGuinea);
        map.put("westernaustralia", WesternAustralia);

    }**/

    public void saveMapToXML(String fileName)
    {
        try {

            //File file = new File("D:/work/3110/Risk-Game-Project/src/main/java/OriginalMap.xml");
            File file = new File (fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(WorldMap.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(this, file);
            jaxbMarshaller.marshal(this, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static WorldMap loadMapFromXML(String fileName)
    {
        WorldMap map = null;
        try {

            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(WorldMap.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            map = (WorldMap) jaxbUnmarshaller.unmarshal(file);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Country getCountry(String key)
    {
        return map.get(key);
    }

    /**
     * check if the countryown contains all the countries in north america .
     * @param countryown
     * @return number of army
     */
    private int northAmericaRecruit(ArrayList<Country> countryown)
    {

        boolean containAll = northAmericaContient.stream().allMatch(country -> countryown.indexOf(country)>-1);

        return containAll? 5:0;
    }

    /**
     * check if the countryown contains all the countries in south america .
     * @param countryown
     * @return number of army
     */
    private int southAmericaRecruit(ArrayList<Country> countryown)
    {

        boolean containAll = southAmericaContient.stream().allMatch(country -> countryown.indexOf(country)>-1);

        return containAll? 2:0;
    }

    /**
     * check if the countryown contains all the countries in Europe .
     * @param countryown
     * @return number of army
     */
    private int europeRecruit(ArrayList<Country> countryown)
    {

        boolean containAll = europeContient.stream().allMatch(country -> countryown.indexOf(country)>-1);

        return containAll? 5:0;
    }

    /**
     * check if the countryown contains all the countries in Africa.
     * @param countryown
     * @return number of army
     */
    private int africaRecruit(ArrayList<Country> countryown)
    {

        boolean containAll = africaContient.stream().allMatch(country -> countryown.indexOf(country)>-1);

        return containAll? 3:0;
    }

    /**
     * check if the countryown contains all the countries in Asia.
     * @param countryown
     * @return number of army
     */
    private int asiaRecruit(ArrayList<Country> countryown)
    {
        boolean containAll = asiaContient.stream().allMatch(country -> countryown.indexOf(country)>-1);
        return containAll? 7:0;
    }

    /**
     * check if the countryown contains all the countries in Australia.
     * @param countryown
     * @return number of army
     */
    private int australiaRecruit(ArrayList<Country> countryown)
    {
        boolean containAll = australiaContient.stream().allMatch(country -> countryown.indexOf(country)>-1);
        return containAll? 2:0;
    }

    /**
     * algorithm for calculating how many army should give to the play depending on number of conutry he owns.
     * @param player
     * @return number of army
     */
    private int getNewArmyByNumOfCountry(Player player)
    {
        int numOfCountryOwn = player.getCountriesOwn().size();
        int numOfArmy = numOfCountryOwn/3;
        return numOfArmy;
    }

    /**
     * algorithm for calculating how many army should give to the play depending on the continents he owns.
     * @param player
     * @return number of army
     */
    private int getNewArmyByContient(Player player)
    {
        ArrayList<Country> CountryOwns = (ArrayList) player.getCountriesOwn();
        return northAmericaRecruit(CountryOwns)+southAmericaRecruit(CountryOwns)+europeRecruit(CountryOwns)+africaRecruit(CountryOwns)+asiaRecruit(CountryOwns)+australiaRecruit(CountryOwns);
    }

    /**
     * calcute the total new army the player should receive in the next round.
     * @param player
     * @return number of army
     */
    public int getNumOfNewArmy(Player player,int mode)
    {
        int numOfArmy=0;
        if(mode == RiskController.ORIGINAL) {
             numOfArmy = getNewArmyByContient(player) + getNewArmyByNumOfCountry(player);
        }
        else
        {
            numOfArmy =  getNewArmyByNumOfCountry(player)*2;
        }
        return Math.max(numOfArmy, 3);
    }
    public void toStrings()
    {
        HashSet<String> keys = (HashSet)map.keySet();
        for(String key:keys)
        {
            System.out.println(map.get(key).getCountryName());
        }
    }

    public static void main(String[] args) {
        WorldMap map = new WorldMap();
        map.saveMapToXML("starMap.xml");
        WorldMap map2 = WorldMap.loadMapFromXML("starMap.xml");
        System.out.println(map2.getMap().size());
    }
}
