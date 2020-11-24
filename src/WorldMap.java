import java.util.ArrayList;
import java.util.HashMap;

public class WorldMap {

    public HashMap<String,Country> map;
    private ArrayList<Country> northAmericaContient;
    private ArrayList<Country> southAmericaContient;
    private ArrayList<Country> europeContient;
    private ArrayList<Country> africaContient;
    private ArrayList<Country> asiaContient;
    private ArrayList<Country> australiaContient;


    public WorldMap(){

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
        Alaska.addAdjacentCountry(Kamchatka);
        Alaska.addAdjacentCountry(Alberta);
        Alaska.addAdjacentCountry(NorthwestTerritory);
        //2
        Alberta.addAdjacentCountry(Alaska);
        Alberta.addAdjacentCountry(NorthwestTerritory);
        Alberta.addAdjacentCountry(Ontario);
        Alberta.addAdjacentCountry(WesternUnitedStates);
        //3
        CentralAmerica.addAdjacentCountry(EasternUnitedStates);
        CentralAmerica.addAdjacentCountry(WesternUnitedStates);
        CentralAmerica.addAdjacentCountry(Venezuela);
        //4 add Venezuela
        EasternUnitedStates.addAdjacentCountry(CentralAmerica);
        EasternUnitedStates.addAdjacentCountry(Ontario);
        EasternUnitedStates.addAdjacentCountry(Quebec);
        EasternUnitedStates.addAdjacentCountry(WesternUnitedStates);
        EasternUnitedStates.addAdjacentCountry(Venezuela);
        //5
        Greenland.addAdjacentCountry(NorthwestTerritory);
        Greenland.addAdjacentCountry(Ontario);
        Greenland.addAdjacentCountry(Quebec);
        Greenland.addAdjacentCountry(Iceland);
        //6
        NorthwestTerritory.addAdjacentCountry(Alaska);
        NorthwestTerritory.addAdjacentCountry(Alberta);
        NorthwestTerritory.addAdjacentCountry(Ontario);
        NorthwestTerritory.addAdjacentCountry(Greenland);
        //7
        Ontario.addAdjacentCountry(CentralAmerica);
        Ontario.addAdjacentCountry(EasternUnitedStates);
        Ontario.addAdjacentCountry(Greenland);
        Ontario.addAdjacentCountry(NorthwestTerritory);
        Ontario.addAdjacentCountry(Quebec);
        Ontario.addAdjacentCountry(WesternUnitedStates);
        //8
        Quebec.addAdjacentCountry(EasternUnitedStates);
        Quebec.addAdjacentCountry(Greenland);
        Quebec.addAdjacentCountry(Ontario);
        //9
        WesternUnitedStates.addAdjacentCountry(Alberta);
        WesternUnitedStates.addAdjacentCountry(CentralAmerica);
        WesternUnitedStates.addAdjacentCountry(EasternUnitedStates);
        WesternUnitedStates.addAdjacentCountry(Ontario);

        //South America
        //1
        Argentina.addAdjacentCountry(Brazil);
        Argentina.addAdjacentCountry(Peru);
        //2
        Brazil.addAdjacentCountry(Argentina);
        Brazil.addAdjacentCountry(Peru);
        Brazil.addAdjacentCountry(Venezuela);
        Brazil.addAdjacentCountry(NorthAfrica);
        //3
        Peru.addAdjacentCountry(Argentina);
        Peru.addAdjacentCountry(Brazil);
        Peru.addAdjacentCountry(Venezuela);
        //4
        Venezuela.addAdjacentCountry(Brazil);
        Venezuela.addAdjacentCountry(Peru);
        Venezuela.addAdjacentCountry(CentralAmerica);

        //Europe
        //1, 2347
        GreatBritain.addAdjacentCountry(Iceland);
        GreatBritain.addAdjacentCountry(NorthernEurope);
        GreatBritain.addAdjacentCountry(Scandinavia);
        GreatBritain.addAdjacentCountry(WesternEurope);
        //2, 14  5
        Iceland.addAdjacentCountry(GreatBritain);
        Iceland.addAdjacentCountry(Scandinavia);
        Iceland.addAdjacentCountry(Greenland);
        //3, 14567
        NorthernEurope.addAdjacentCountry(GreatBritain);
        NorthernEurope.addAdjacentCountry(Scandinavia);
        NorthernEurope.addAdjacentCountry(SouthernEurope);
        NorthernEurope.addAdjacentCountry(Ukraine);
        NorthernEurope.addAdjacentCountry(WesternEurope);
        //4, 1236
        Scandinavia.addAdjacentCountry(GreatBritain);
        Scandinavia.addAdjacentCountry(Iceland);
        Scandinavia.addAdjacentCountry(NorthernEurope);
        Scandinavia.addAdjacentCountry(Ukraine);
        //5, 367 35 7
        SouthernEurope.addAdjacentCountry(NorthernEurope);
        SouthernEurope.addAdjacentCountry(Ukraine);
        SouthernEurope.addAdjacentCountry(WesternEurope);
        SouthernEurope.addAdjacentCountry(Egypt);
        SouthernEurope.addAdjacentCountry(NorthAfrica);
        SouthernEurope.addAdjacentCountry(MiddleEast);
        //6, 345  17 11
        Ukraine.addAdjacentCountry(NorthernEurope);
        Ukraine.addAdjacentCountry(Scandinavia);
        Ukraine.addAdjacentCountry(SouthernEurope);
        Ukraine.addAdjacentCountry(Afghanistan);
        Ukraine.addAdjacentCountry(MiddleEast);
        Ukraine.addAdjacentCountry(Ural);
        //7, 135 5
        WesternEurope.addAdjacentCountry(GreatBritain);
        WesternEurope.addAdjacentCountry(NorthernEurope);
        WesternEurope.addAdjacentCountry(SouthernEurope);
        WesternEurope.addAdjacentCountry(NorthAfrica);

        //Africa
        //1, 256
        Congo.addAdjacentCountry(EastAfrica);
        Congo.addAdjacentCountry(NorthAfrica);
        Congo.addAdjacentCountry(SouthAfrica);
        //2, 13456 7
        EastAfrica.addAdjacentCountry(Congo);
        EastAfrica.addAdjacentCountry(Egypt);
        EastAfrica.addAdjacentCountry(Madagascar);
        EastAfrica.addAdjacentCountry(NorthAfrica);
        EastAfrica.addAdjacentCountry(SouthAfrica);
        EastAfrica.addAdjacentCountry(MiddleEast);
        //3, 25 6 7  Ukranie change to SouthernEurope
        Egypt.addAdjacentCountry(EastAfrica);
        Egypt.addAdjacentCountry(NorthAfrica);
        Egypt.addAdjacentCountry(SouthernEurope);
        Egypt.addAdjacentCountry(MiddleEast);
        //4, 26
        Madagascar.addAdjacentCountry(EastAfrica);
        Madagascar.addAdjacentCountry(SouthAfrica);
        //5, 123 67 2  Ukranie change to SouthernEurope
        NorthAfrica.addAdjacentCountry(Congo);
        NorthAfrica.addAdjacentCountry(EastAfrica);
        NorthAfrica.addAdjacentCountry(Egypt);
        NorthAfrica.addAdjacentCountry(SouthernEurope);
        NorthAfrica.addAdjacentCountry(WesternEurope);
        NorthAfrica.addAdjacentCountry(Brazil);
        //6, 124
        SouthAfrica.addAdjacentCountry(Congo);
        SouthAfrica.addAdjacentCountry(EastAfrica);
        SouthAfrica.addAdjacentCountry(Madagascar);

        //Asia
        //1, 237 11 6
        Afghanistan.addAdjacentCountry(China);
        Afghanistan.addAdjacentCountry(India);
        Afghanistan.addAdjacentCountry(MiddleEast);
        Afghanistan.addAdjacentCountry(Ural);
        Afghanistan.addAdjacentCountry(Ukraine);
        //2, 1389 10 11
        China.addAdjacentCountry(Afghanistan);
        China.addAdjacentCountry(India);
        China.addAdjacentCountry(Mongolia);
        China.addAdjacentCountry(Siam);
        China.addAdjacentCountry(Siberia);
        China.addAdjacentCountry(Ural);
        //3, 1279
        India.addAdjacentCountry(Afghanistan);
        India.addAdjacentCountry(China);
        India.addAdjacentCountry(MiddleEast);
        India.addAdjacentCountry(Siam);
        //4, 68 10 12
        Irkutsk.addAdjacentCountry(Kamchatka);
        Irkutsk.addAdjacentCountry(Mongolia);
        Irkutsk.addAdjacentCountry(Siberia);
        Irkutsk.addAdjacentCountry(Yakutsk);
        //5, 68
        Japan.addAdjacentCountry(Kamchatka);
        Japan.addAdjacentCountry(Mongolia);
        //6, 458 12 1
        Kamchatka.addAdjacentCountry(Irkutsk);
        Kamchatka.addAdjacentCountry(Japan);
        Kamchatka.addAdjacentCountry(Mongolia);
        Kamchatka.addAdjacentCountry(Yakutsk);
        Kamchatka.addAdjacentCountry(Alaska);
        //7, 13 56 23
        MiddleEast.addAdjacentCountry(Afghanistan);
        MiddleEast.addAdjacentCountry(India);
        MiddleEast.addAdjacentCountry(SouthernEurope);
        MiddleEast.addAdjacentCountry(Ukraine);
        MiddleEast.addAdjacentCountry(EastAfrica);
        MiddleEast.addAdjacentCountry(Egypt);
        //8, 2456 10
        Mongolia.addAdjacentCountry(China);
        Mongolia.addAdjacentCountry(Irkutsk);
        Mongolia.addAdjacentCountry(Japan);
        Mongolia.addAdjacentCountry(Kamchatka);
        Mongolia.addAdjacentCountry(Siberia);
        //9, 23 2
        Siam.addAdjacentCountry(China);
        Siam.addAdjacentCountry(India);
        Siam.addAdjacentCountry(Indonesia);
        //10, 248 11 12
        Siberia.addAdjacentCountry(China);
        Siberia.addAdjacentCountry(Irkutsk);
        Siberia.addAdjacentCountry(Mongolia);
        Siberia.addAdjacentCountry(Ural);
        Siberia.addAdjacentCountry(Yakutsk);
        //11, 12 10 6 add China
        Ural.addAdjacentCountry(Yakutsk);
        Ural.addAdjacentCountry(Siberia);
        Ural.addAdjacentCountry(Ukraine);
        Ural.addAdjacentCountry(China);
        //12, 46 10
        Yakutsk.addAdjacentCountry(Irkutsk);
        Yakutsk.addAdjacentCountry(Kamchatka);
        Yakutsk.addAdjacentCountry(Siberia);

        //Australia
        //1, 34
        EasternAustralia.addAdjacentCountry(NewGuinea);
        EasternAustralia.addAdjacentCountry(WesternAustralia);
        //2, 34 9
        Indonesia.addAdjacentCountry(NewGuinea);
        Indonesia.addAdjacentCountry(WesternAustralia);
        Indonesia.addAdjacentCountry(Siam);
        //3, 124
        NewGuinea.addAdjacentCountry(EasternAustralia);
        NewGuinea.addAdjacentCountry(Indonesia);
        NewGuinea.addAdjacentCountry(WesternAustralia);
        //4, 12 add New Guinea
        WesternAustralia.addAdjacentCountry(EasternAustralia);
        WesternAustralia.addAdjacentCountry(Indonesia);
        WesternAustralia.addAdjacentCountry(NewGuinea);

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
    public int getNumOfNewArmy(Player player)
    {
        int numOfArmy = getNewArmyByContient(player)+getNewArmyByNumOfCountry(player);
        return (numOfArmy>=3)? numOfArmy:3;
    }


}