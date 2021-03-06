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
@XmlRootElement(name = "ButtonInfoStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class ButtonInfoStore {

    ArrayList<ButtonInfo> bis;

    /*public ButtonInfoStore()
    {
        bis = new ArrayList<>();
        bis.add(new ButtonInfo("russia",285,245,25,15));
        bis.add(new ButtonInfo("france",450,250,25,15));
        bis.add(new ButtonInfo("german",580,250,25,15));
        bis.add(new ButtonInfo("switzerland",250,350,25,15));
        bis.add(new ButtonInfo("belgium",450,365,25,15));
        bis.add(new ButtonInfo("poland",620,350,25,15));
        bis.add(new ButtonInfo("sweden",220,475,25,15));
        bis.add(new ButtonInfo("norway",340,540,25,15));
        bis.add(new ButtonInfo("denmark",550,540,25,15));
        bis.add(new ButtonInfo("italy",680,475,25,15));
        bis.add(new ButtonInfo("finland",450,626,25,15));

        //bis.add(new ButtonInfo("france",100,210,25,15));
        //bis.add(new ButtonInfo("german",170,315,25,15));
       // bis.add(new ButtonInfo("belgium",187,290,25,15));
    }*/
    /**
    public ButtonInfoStore()
    {
        bis = new ArrayList<>();
        //north america
        bis.add(new ButtonInfo("alaska",23,190,25,15));
        bis.add(new ButtonInfo("alberta",100,210,25,15));
        bis.add(new ButtonInfo("centralamerica",170,315,25,15));
        bis.add(new ButtonInfo("easternunitedstates",187,290,25,15));
        bis.add(new ButtonInfo("greenland",305,145,25,15));
        bis.add(new ButtonInfo("northwestterritory",145,167,25,15));
        bis.add(new ButtonInfo("ontario",187,210,25,15));
        bis.add(new ButtonInfo("quebec",260,240,25,15));
        bis.add(new ButtonInfo("westernunitedstates",100,275,25,15));

        //south america
        bis.add(new ButtonInfo("argentina",193,487,25,15));
        bis.add(new ButtonInfo("brazil",263,481,25,15));
        bis.add(new ButtonInfo("peru",208,440,25,15));
        bis.add(new ButtonInfo("venezuela",230,399,25,15));

        //europe
        bis.add(new ButtonInfo("greatbritain",370,230,25,15));
        bis.add(new ButtonInfo("iceland",365,185,25,15));
        bis.add(new ButtonInfo("northerneurope",480,207,25,15));
        bis.add(new ButtonInfo("scandinavia",440,160,25,15));
        bis.add(new ButtonInfo("southerneurope",510,280,25,15));
        bis.add(new ButtonInfo("ukraine",530,207,25,15));
        bis.add(new ButtonInfo("westerneurope",455,250,25,15));
        //Africa
        bis.add(new ButtonInfo("congo",382,418,25,15));
        bis.add(new ButtonInfo("eastafrica",460,419,25,15));
        bis.add(new ButtonInfo("egypt",450,355,25,15));
        bis.add(new ButtonInfo("madagascar",499,521,25,15));
        bis.add(new ButtonInfo("northafrica",385,338,25,15));
        bis.add(new ButtonInfo("southafrica",380,518,25,15));
        //aisa
        bis.add(new ButtonInfo("afghanistan",599,265,25,15));
        bis.add(new ButtonInfo("china",630,235,25,15));
        bis.add(new ButtonInfo("india",633,326,25,15));
        bis.add(new ButtonInfo("irkutsk",736,202,25,15));
        bis.add(new ButtonInfo("japan",830,284,25,15));
        bis.add(new ButtonInfo("kamchatka",805,185,25,15));
        bis.add(new ButtonInfo("middleeast",583,310,25,15));
        bis.add(new ButtonInfo("mongolia",758,237,25,15));
        bis.add(new ButtonInfo("siam",688,316,25,15));
        bis.add(new ButtonInfo("siberia",640,175,25,15));
        bis.add(new ButtonInfo("ural",608,129,25,15));
        bis.add(new ButtonInfo("yakutsk",717,173,25,15));
        //australia
        bis.add(new ButtonInfo("easternaustralia",810,600,25,15));
        bis.add(new ButtonInfo("indonesia",709,437,25,15));
        bis.add(new ButtonInfo("newguinea",841,447,25,15));
        bis.add(new ButtonInfo("westernaustralia",721,558,25,15));
    }
     **/

    public ButtonInfo getBuddyInfo(int index)
    {
        return bis.get(index);
    }

    public ArrayList<ButtonInfo> getButtonInfoList()
    {
        return bis;
    }

    public void saveToXML(String fileName)
    {
        try {

            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(ButtonInfoStore.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(this, file);
            jaxbMarshaller.marshal(this, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public int size()
    {
        return bis.size();
    }

    public static ButtonInfoStore loadFromXML(String fileName)
    {
        ButtonInfoStore b = null;
        try {

            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(ButtonInfoStore.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            b = (ButtonInfoStore) jaxbUnmarshaller.unmarshal(file);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static void main(String[] args) {
        ButtonInfoStore b = new ButtonInfoStore();
        b.saveToXML("starMapButtons.xml");
        ButtonInfoStore b2 = ButtonInfoStore.loadFromXML("starMapButtons.xml");
        System.out.println(b.getButtonInfoList().size());
        System.out.println(b2.getButtonInfoList().size());
    }
}

